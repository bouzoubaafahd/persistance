package fr.univcotedazur.polyevent.components;

import fr.univcotedazur.polyevent.interfaces.database.Finder;
import fr.univcotedazur.polyevent.entities.*;
import fr.univcotedazur.polyevent.interfaces.invoice.InvoiceCalculator;
import fr.univcotedazur.polyevent.interfaces.invoice.InvoiceInfoProvider;
import fr.univcotedazur.polyevent.interfaces.invoice.InvoiceProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static fr.univcotedazur.polyevent.utils.DateHandler.getDurationInHours;

@Component
@ComponentScan("fr.univcotedazur")
public class InvoiceHandler implements InvoiceInfoProvider, InvoiceProcessor, InvoiceCalculator {

    @Autowired
    InMemoryDatabase memory;

    @Autowired
    Finder finder;

    @Override
    public ArrayList<Invoice> getInvoices(){
        return memory.listeInvoices;
    }

    @Override
    public Invoice createInvoice(Long eventId) {
        if (memory.listeInvoices.contains(eventId)) return finder.findInvoiceById(eventId);
        if(finder.findEventById(eventId).getEventStatus() != EventStatus.VALIDATED){
            return  null;
        }
        Invoice invoice = new Invoice(eventId);
        invoice.setRooms((ArrayList<RoomsReservation>) finder.findEventById(eventId).getRooms());
        invoice.setServices(getSuppliersAndServicesOfEvent(eventId));
        memory.listeInvoices.add(invoice);
        return invoice;
    }

    public ArrayList<SupplierService> getSuppliersAndServicesOfEvent(Long eventId){
        Event event = finder.findEventById(eventId);
        ArrayList<SupplierService> services = new ArrayList<>();
        for (Service service : event.getServices()) {
            Supplier supplier = finder.findSupplierOfService(service);
            services.add(new SupplierService(supplier.getId() , service.getId()));
        }
        return services;
    }

    public float calculateServicesPrice(ArrayList<Long> services) {
        float price = 0;
        for (Long serviceId: services) {
            price += finder.findServiceById(serviceId).getPrice();
        }
        return price;
    }

    @Override
    public HashMap<Long, Float> getSuppliersPayment(Long invoiceId){
        HashMap<Long, Float> suppliersPayment = new HashMap<>();
       // HashMap<Long, ArrayList<Long>> services = getSuppliersAndServicesOfEvent(invoiceId);
        ArrayList<SupplierService> services = getSuppliersAndServicesOfEvent(invoiceId);
        for(SupplierService supplierService : services){
            suppliersPayment.put(supplierService.getSupplierId(), calculateServicesPrice(extractServices(services ,supplierService.getSupplierId() )));
        }
        return suppliersPayment;
    }

    private ArrayList<Long> extractServices( ArrayList<SupplierService> services , Long supplierId){
        ArrayList<Long> servicesToExtract = new ArrayList<>();
        for(SupplierService supplierService : services){
         if(supplierService.getSupplierId().equals(supplierId)){
             servicesToExtract.add(supplierService.getServiceId());
         }
        }
        return servicesToExtract;
    }

    @Override
    public float getRoomsPrice(Long invoiceId){
        float price = 0;
        ArrayList<RoomsReservation> rooms = (ArrayList<RoomsReservation>) finder.findEventById(invoiceId).getRooms();
        for (RoomsReservation roomsReservation : rooms) {

                price += finder.findRoomPrice(roomsReservation.getRoomId()) * getDurationInHours(roomsReservation.getTimeSlot());
            }

        return price;
    }

    @Override
    public float getTotalPriceToPay(Long invoiceId){
        float price = getRoomsPrice(invoiceId);
        for (Long supplierId : getSuppliersPayment(invoiceId).keySet()) {
            price += getSuppliersPayment(invoiceId).get(supplierId);
        }
        return price;
    }

}
