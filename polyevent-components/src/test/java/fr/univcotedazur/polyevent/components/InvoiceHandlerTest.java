package fr.univcotedazur.polyevent.components;

import fr.univcotedazur.polyevent.entities.*;
import fr.univcotedazur.polyevent.interfaces.database.Finder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static fr.univcotedazur.polyevent.utils.DateHandler.createAFutureTimeSlot;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {Reservation.class,InMemoryDatabase.class})
public class InvoiceHandlerTest {

    @Autowired
    Reservation reservation;
    @Autowired
    InvoiceHandler invoiceHandler;
    @Autowired
    InMemoryDatabase memory;

    @Autowired
    Finder finder;

    ArrayList<Service> services;
    ArrayList<Long> servicesId;
    ArrayList <RoomsReservation>   availablerooms;
    Event event;
    Event event2;
    TimeSlot timeSlot;
    TimeSlot timeSlot1;
    TimeSlot timeSlot2;




    @BeforeEach
    public void setUp(){
        memory.init();
        services  = new ArrayList<>();
        servicesId  = new ArrayList<>();
        services.add(memory.listServicesCLEANING.get(1));
        services.add(memory.listeServicesGAS.get(1));
        services.add(memory.listServicesSECURITY.get(1));
        services.add(memory.listServicesELECTRICITY.get(2));
        for (Service service: services) {
            servicesId.add(service.getId());

        }
        event = memory.listEvents.get(0);
        event2 = memory.listEvents.get(1);
        event2.setEventStatus(EventStatus.VALIDATED);
        timeSlot = createAFutureTimeSlot(24,3);
        timeSlot1 = createAFutureTimeSlot(25,3);
        timeSlot2 = createAFutureTimeSlot(26,3);
        ArrayList<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(timeSlot);
        timeSlots.add(timeSlot1);
        timeSlots.add(timeSlot2);
        event2.setServices(services);
        event.setServices(services);
        availablerooms = new ArrayList<>();
        for(TimeSlot ts : timeSlots){
            availablerooms.add(new RoomsReservation( memory.listRooms.get(5).getId(), ts) );
            availablerooms.add(new RoomsReservation( memory.listRooms.get(6).getId(), ts) );
            availablerooms.add(new RoomsReservation( memory.listRooms.get(7).getId(), ts) );
        }
        event.setRooms(availablerooms);


    }


    @Test
    public void priceCalculatorTest(){
        assertEquals(490.0f, invoiceHandler.calculateServicesPrice(servicesId));

    }


    @Test
    public void createInvoiceFromPendingEvent(){
        for (Event event: memory.listEvents) {
            System.out.println(event);
        } // TODO: REMOVE THE LOOP

       assertNull(invoiceHandler.createInvoice(event.getId()));


    }

    @Test
    public void createInvoiceFromValidatedEvent(){
        int sizeOfEventsList = memory.listeInvoices.size();
        assertNotNull(invoiceHandler.createInvoice(event2.getId()));
        assertTrue(memory.listeInvoices.size() > sizeOfEventsList );
    }

    @Test
    public void createInvoiceTest(){
        Invoice invoice = invoiceHandler.createInvoice(event2.getId());
        assertNotNull(invoice.getServices());
        assertNotNull(invoice.getRooms());
    }

    @Test
    public void getSuppliersAndServicesOfEventTest(){
       ArrayList<SupplierService> servicesCompo = invoiceHandler.getSuppliersAndServicesOfEvent(event2.getId());

       for(SupplierService supplierService : servicesCompo){
           assertTrue(finder.findSupplierById(supplierService.getSupplierId()).getServices().contains(finder.findServiceById(supplierService.getServiceId())));
       }
    }

}
