package fr.univcotedazur.polyevent.components;

import fr.univcotedazur.polyevent.entities.*;
import fr.univcotedazur.polyevent.utils.DateHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Component
@ComponentScan("fr.univcotedazur")
public class InMemoryDatabase {

    // Organizers
    public ArrayList<Organizer> listOrganizers = new ArrayList<>();

    // Suppliers and services
    public ArrayList<Supplier> listSuppliers;
    public ArrayList<Service> listServices;
    public ArrayList<Service> listServicesCLEANING;
    public ArrayList<Service> listServicesSECURITY;
    public ArrayList<Service> listServicesMAINTENANCE;
    public ArrayList<Service> listServicesELECTRICITY;
    public ArrayList<Service> listeServicesGAS;
    public ArrayList<Service> listeServicesWATER;
    public ArrayList<Service> listeServicesHEATING;
    public ArrayList<Service> listeServicesCATERING;
    public ArrayList<Service> listeServicesLIFTING;
    public ArrayList<Service> listeServicesOTHER;
    public ArrayList<Invoice> listeInvoices;
    public List<Ticket> listeTikets;
    public ArrayList<Equipment> listEquipments;

    // Rooms
    public ArrayList<Room> listRooms; // ArrayList is used because rooms every room is unique
    public Map<Long, ArrayList<TimeSlot>> listReservedRooms;
    public Map<Long, Float> listPricePerRoom;

    // Events
    public ArrayList<Event> listEvents;

    public InMemoryDatabase(){
        this.listOrganizers = new ArrayList<>();
        this.listSuppliers = new ArrayList<>();
        this.listServices = new ArrayList<>();
        this.listServicesCLEANING = new ArrayList<>();
        this.listServicesSECURITY = new ArrayList<>();
        this.listServicesMAINTENANCE = new ArrayList<>();
        this.listServicesELECTRICITY = new ArrayList<>();
        this.listeServicesGAS = new ArrayList<>();
        this.listeServicesWATER = new ArrayList<>();
        this.listeServicesHEATING = new ArrayList<>();
        this.listeServicesCATERING = new ArrayList<>();
        this.listeServicesLIFTING = new ArrayList<>();
        this.listeServicesOTHER = new ArrayList<>();
        this.listRooms = new ArrayList<>();
        this.listReservedRooms = new HashMap<>();
        this.listEvents = new ArrayList<>();
        this.listeInvoices = new  ArrayList<>();
        this.listPricePerRoom = new HashMap<>();
        this.listeTikets = new ArrayList<>();
        this.listEquipments = new ArrayList<>();
        fillDB();
    }

    public void fillDB() {
        this.fillSupplierList();
        this.fillServicesCLEANING();
        this.fillServicesSECURITY();
        this.fillServicesMAINTENANCE();
        this.fillServicesElectricity();
        this.fillServicesGAS();
        this.fillServicesWATER();
        this.fillServicesHEATING();
        this.fillServicesCATERING();
        this.fillServicesLIFTING();
        this.fillServicesOTHER();
        this.fillServices();
        this.fillRooms();
        this.fillReservedRooms();
        this.fillSupplierServices();
        this.fillEvents();
        this.fillInvoices();
        this.fillPricePerRoom();
        this.fillOrganizers();
        this.fillEquipments();
        this.fillTickets();
    }

    public void flush(){
        this.listSuppliers.clear();
        this.listServicesCLEANING.clear();
        this.listServicesSECURITY.clear();
        this.listServicesMAINTENANCE.clear();
        this.listServicesELECTRICITY.clear();
        this.listeServicesGAS.clear();
        this.listeServicesWATER.clear();
        this.listeServicesHEATING.clear();
        this.listeServicesCATERING.clear();
        this.listeServicesLIFTING.clear();
        this.listeServicesOTHER.clear();
        this.listRooms.clear();
        this.listReservedRooms.clear();
        this.listEvents.clear();
        this.listOrganizers.clear();
        this.listEquipments.clear();
        this.listeTikets.clear();
    }

    public void init(){
        this.flush();
        this.fillDB();
    }

    public void fillInvoices(){
        Event event = listEvents.get(0);
        Invoice invoice = new Invoice(event.getId());
        invoice.setRooms((ArrayList<RoomsReservation>) event.getRooms());
        ArrayList<SupplierService> services = new ArrayList<>();
        services.add(new SupplierService(listSuppliers.get(0).getId(), listServicesCLEANING.get(0).getId()));
        invoice.setServices(services);
        listeInvoices.add(invoice);
    }

    public void fillTickets(){
        Predicate<Equipment> byFonction = Equipment -> Equipment.isFunctioning();

        List<Equipment> faultyEquipments = this.listEquipments.stream().filter(byFonction)
                .collect(Collectors.toList());

        this.listeTikets.add(new Ticket("Ticket", "Description", faultyEquipments));
        this.listeTikets.add(new Ticket("Ticket 1", "Description 1", faultyEquipments));
        this.listeTikets.add(new Ticket("Ticket 2", "Description 2", faultyEquipments));
        this.listeTikets.add(new Ticket("Ticket 3", "Description 3", faultyEquipments));
        this.listeTikets.add(new Ticket("Ticket 4", "Description 4", faultyEquipments));
        this.listeTikets.add(new Ticket("Ticket 5", "Description 5", faultyEquipments));
    }

    public void fillEquipments(){
        this.listEquipments.add(new Equipment(1l, EquipmentType.LIGHT_BULB, false));
        this.listEquipments.add(new Equipment(2l, EquipmentType.COMPUTER, false));
        this.listEquipments.add(new Equipment(3l, EquipmentType.MICROPHONE, false));
        this.listEquipments.add(new Equipment(4l, EquipmentType.PROJECTOR, false));
        this.listEquipments.add(new Equipment(5l, EquipmentType.WHITEBOARD, false));
        this.listEquipments.add(new Equipment(6l, EquipmentType.SCREEN));
        this.listEquipments.add(new Equipment(7l, EquipmentType.ELECTRICAL_OUTLET));
        this.listEquipments.add(new Equipment(8l, EquipmentType.SPEAKER));
        this.listEquipments.add(new Equipment(9l, EquipmentType.OTHER));
    }

    public void fillSupplierList(){
        this.listSuppliers.add(new Supplier("CLEANING", "address 1", "06000001", "email1@gmail.com"));
        this.listSuppliers.add(new Supplier("SECURITY", "address 2", "06000002", "email2@gmail.com"));
        this.listSuppliers.add(new Supplier("MAINTENANCE", "address 3", "06000003", "email3@gmail.com"));
        this.listSuppliers.add(new Supplier("ELECTRICITY", "address 4", "06000004", "email4@gmail.com"));
        this.listSuppliers.add(new Supplier("GAS", "address 5", "06000005", "email5@gmail.com"));
        this.listSuppliers.add(new Supplier("WATER", "address 6", "06000006", "email6@gmail.com"));
        this.listSuppliers.add(new Supplier("HEATING", "address 7", "06000007", "email7@gmail.com"));
        this.listSuppliers.add(new Supplier("CATERING", "address 8", "06000008", "email8@gmail.com"));
        this.listSuppliers.add(new Supplier("LIFTING", "address 9", "06000009", "email9@gmail.com"));
        this.listSuppliers.add(new Supplier("OTHER", "address 10", "060000010", "email10@gmail.com"));
    }

    public void fillServicesCLEANING(){
        this.listServicesCLEANING.add(new Service(110.0f, ServiceCategory.CLEANING));
        this.listServicesCLEANING.add(new Service(120.0f, ServiceCategory.CLEANING));
        this.listServicesCLEANING.add(new Service(130.0f, ServiceCategory.CLEANING));
        this.listServicesCLEANING.add(new Service(140.0f, ServiceCategory.CLEANING));
        this.listServicesCLEANING.add(new Service(150.0f, ServiceCategory.CLEANING));
        this.listServicesCLEANING.get(0).setId(12l);

    }


    public void fillServicesSECURITY(){
        //for Security
        this.listServicesSECURITY.add(new Service(110.0f, ServiceCategory.SECURITY));
        this.listServicesSECURITY.add(new Service(120.0f, ServiceCategory.SECURITY));
        this.listServicesSECURITY.add(new Service(130.0f, ServiceCategory.SECURITY));
        this.listServicesSECURITY.add(new Service(140.0f, ServiceCategory.SECURITY));
        this.listServicesSECURITY.add(new Service(150.0f, ServiceCategory.SECURITY));
        this.listServicesSECURITY.get(0).setId(9l);


    }

    public void fillServicesMAINTENANCE(){
        this.listServicesMAINTENANCE.add(new Service(110.0f, ServiceCategory.MAINTENANCE));
        this.listServicesMAINTENANCE.add(new Service(120.0f, ServiceCategory.MAINTENANCE));
        this.listServicesMAINTENANCE.add(new Service(130.0f, ServiceCategory.MAINTENANCE));
        this.listServicesMAINTENANCE.add(new Service(140.0f, ServiceCategory.MAINTENANCE));
        this.listServicesMAINTENANCE.add(new Service(150.0f, ServiceCategory.MAINTENANCE));


    }

    public void fillServicesElectricity(){
        //for Security
        this.listServicesELECTRICITY.add(new Service(110.0f, ServiceCategory.ELECTRICITY));
        this.listServicesELECTRICITY.add(new Service(120.0f, ServiceCategory.ELECTRICITY));
        this.listServicesELECTRICITY.add(new Service(130.0f, ServiceCategory.ELECTRICITY));
        this.listServicesELECTRICITY.add(new Service(140.0f, ServiceCategory.ELECTRICITY));
        this.listServicesELECTRICITY.add(new Service(150.0f, ServiceCategory.ELECTRICITY));


    }

    public void fillServicesGAS(){
        //for Security
        this.listeServicesGAS.add(new Service(110.0f, ServiceCategory.GAS));
        this.listeServicesGAS.add(new Service(120.0f, ServiceCategory.GAS));
        this.listeServicesGAS.add(new Service(130.0f, ServiceCategory.GAS));
        this.listeServicesGAS.add(new Service(140.0f, ServiceCategory.GAS));
        this.listeServicesGAS.add(new Service(150.0f, ServiceCategory.GAS));


    }



    public void fillServicesWATER(){
        //for Security
        this.listeServicesWATER.add(new Service(110.0f, ServiceCategory.WATER));
        this.listeServicesWATER.add(new Service(120.0f, ServiceCategory.WATER));
        this.listeServicesWATER.add(new Service(130.0f, ServiceCategory.WATER));
        this.listeServicesWATER.add(new Service(140.0f, ServiceCategory.WATER));
        this.listeServicesWATER.add(new Service(150.0f, ServiceCategory.WATER));


    }

    public void fillServicesHEATING(){
        //for Security
        this.listeServicesHEATING.add(new Service(110.0f, ServiceCategory.HEATING));
        this.listeServicesHEATING.add(new Service(120.0f, ServiceCategory.HEATING));
        this.listeServicesHEATING.add(new Service(130.0f, ServiceCategory.HEATING));
        this.listeServicesHEATING.add(new Service(140.0f, ServiceCategory.HEATING));
        this.listeServicesHEATING.add(new Service(150.0f, ServiceCategory.HEATING));


    }

    public void fillServicesCATERING(){
        //for Security
        this.listeServicesCATERING.add(new Service(110.0f, ServiceCategory.CATERING));
        this.listeServicesCATERING.add(new Service(120.0f, ServiceCategory.CATERING));
        this.listeServicesCATERING.add(new Service(130.0f, ServiceCategory.CATERING));
        this.listeServicesCATERING.add(new Service(140.0f, ServiceCategory.CATERING));
        this.listeServicesCATERING.add(new Service(150.0f, ServiceCategory.CATERING));


    }

    public void fillServicesLIFTING(){
        //for LIFTING
        this.listeServicesLIFTING.add(new Service(110.0f, ServiceCategory.LIFTING));
        this.listeServicesLIFTING.add(new Service(120.0f, ServiceCategory.LIFTING));
        this.listeServicesLIFTING.add(new Service(130.0f, ServiceCategory.LIFTING));
        this.listeServicesLIFTING.add(new Service(140.0f, ServiceCategory.LIFTING));
        this.listeServicesLIFTING.add(new Service(150.0f, ServiceCategory.LIFTING));


    }


    public void fillServicesOTHER(){
        //for OTHER
        this.listeServicesOTHER.add(new Service(110.0f, ServiceCategory.OTHER));
        this.listeServicesOTHER.add(new Service(120.0f, ServiceCategory.OTHER));
        this.listeServicesOTHER.add(new Service(130.0f, ServiceCategory.OTHER));
        this.listeServicesOTHER.add(new Service(140.0f, ServiceCategory.OTHER));
        this.listeServicesOTHER.add(new Service(150.0f, ServiceCategory.OTHER));
        this.listeServicesOTHER.get(0).setId(2l);
    }

    public void fillServices(){
        listServices.addAll(listServicesCLEANING);
        listServices.addAll(listServicesSECURITY);
        listServices.addAll(listServicesMAINTENANCE);
        listServices.addAll(listServicesELECTRICITY);
        listServices.addAll(listeServicesGAS);
        listServices.addAll(listeServicesWATER);
        listServices.addAll(listeServicesHEATING);
        listServices.addAll(listeServicesCATERING);
        listServices.addAll(listeServicesLIFTING);
        listServices.addAll(listeServicesOTHER);
    }


    public void fillRooms() {
        this.listRooms.add(new Room(0l, RoomType.CLASSROOM, 30));
        this.listRooms.add(new Room(1l, RoomType.MEETING_ROOM, 80));
        this.listRooms.add(new Room(2l, RoomType.CONFERENCE_ROOM, 50));
        this.listRooms.add(new Room(3l, RoomType.LECTURE_ROOM, 140));
        this.listRooms.add(new Room(4l, RoomType.LABORATORY_ROOM, 20));
        this.listRooms.add(new Room(5l, RoomType.AUDITORIUM, 200));
        this.listRooms.add(new Room(6l, RoomType.CLASSROOM_HALL, 100));
        this.listRooms.add(new Room(7l, RoomType.LOUNGE, 80));
        this.listRooms.add(new Room(8l, RoomType.STUDY_ROOM, 80));
        this.listRooms.add(new Room(9l, RoomType.CLASSROOM, 50));
        this.listRooms.add(new Room(10l, RoomType.MEETING_ROOM, 120));
        this.listRooms.add(new Room(11l, RoomType.CONFERENCE_ROOM, 70));
    }

    public void fillReservedRooms() {

        this.listReservedRooms.put(listRooms.get(0).getId(), new ArrayList<>(Arrays.asList(
                DateHandler.createAFutureTimeSlot(2,2), DateHandler.createAFutureTimeSlot(4, 4)
        )));

        this.listReservedRooms.put(listRooms.get(2).getId(), new ArrayList<>(Arrays.asList(
                DateHandler.createAFutureTimeSlot(1,8)
        )));

        this.listReservedRooms.put(listRooms.get(4).getId(), new ArrayList<>(Arrays.asList(
                DateHandler.createAFutureTimeSlot(7,3), DateHandler.createAFutureTimeSlot(30,2), DateHandler.createAFutureTimeSlot(21,10)
        )));

    }

    public void fillPricePerRoom(){
        for (Room room : listRooms) {
            if (room.getCapacity() <= 20) {
                this.listPricePerRoom.put(room.getId(), 40.0f);
            }
            else if (room.getCapacity() <= 50) {
                this.listPricePerRoom.put(room.getId(), 70.0f);
            }
            else if (room.getCapacity() <= 100) {
                this.listPricePerRoom.put(room.getId(), 100.0f);
            }
            else if (room.getCapacity() <= 200) {
                this.listPricePerRoom.put(room.getId(), 150.0f);
            }
            else if (room.getCapacity() <= 300) {
                this.listPricePerRoom.put(room.getId(), 200.0f);
            }
            else {
                this.listPricePerRoom.put(room.getId(), 250.0f);
            }
        }
    }

    public void addReservedRoom(Long roomId, TimeSlot TimeSlot) {
        if (this.listReservedRooms.containsKey(roomId)) {
            this.listReservedRooms.get(roomId).add(TimeSlot);
        } else {
            this.listReservedRooms.put(roomId, new ArrayList<>(Arrays.asList(TimeSlot)));
        }
    }

    public void fillEvents() {
        for(Long roomId : listReservedRooms.keySet()){
            ArrayList<TimeSlot> timeSlots = listReservedRooms.get(roomId);
            ArrayList<RoomsReservation> rooms = new ArrayList<>();
            for(TimeSlot ts : timeSlots){
                rooms.add( new RoomsReservation(roomId , ts));
            }
            Event event = new Event();
            event.setEventStatus(EventStatus.PENDING);
            event.setPaymentStatus(PaymentStatus.NOT_PAID);
            event.setServices(new ArrayList<>(listServicesCLEANING.subList(0,1)));
            event.setRooms(rooms);
            listEvents.add(event);
        }
    }


    public void fillSupplierServices(){
        int i = 0;
        for(Supplier s : listSuppliers){
            s.getServices().add(listeServicesOTHER.get(i));
            s.getServices().add(listeServicesLIFTING.get(i));
            s.getServices().add(listeServicesCATERING.get(i));
            s.getServices().add(listeServicesHEATING.get(i));
            s.getServices().add(listeServicesWATER.get(i));
            s.getServices().add(listeServicesGAS.get(i));
            s.getServices().add(listServicesELECTRICITY.get(i));
            s.getServices().add(listServicesMAINTENANCE.get(i));
            s.getServices().add(listServicesSECURITY.get(i));
            s.getServices().add(listServicesCLEANING.get(i));
            i++;
            i=i%5;
        }
    }

    public void fillOrganizers() {
        this.listOrganizers.add(new Organizer("Amadeus","8969835591626477"));
        this.listOrganizers.add(new Organizer("Atos","4929442689698393"));
        this.listOrganizers.add(new Organizer("Capgemini","4485782466987970"));
        for (int i = 0; i < listOrganizers.size(); i++) {
            this.listOrganizers.get(i).setEvents(new ArrayList<>( Arrays.asList(listEvents.get(i))));
        }
        listOrganizers.get(0).setId(15l);
    }

    public Ticket getTicketByTitle(Long title){
        for (Ticket t: this.listeTikets) {
            if(t.getTitle().equals(title)){
                return t;
            }
        }
        return null;
    }

    public Equipment getEquipmentById(Long id){
        for (Equipment e: this.listEquipments) {
            if(e.getId().equals(id)){
                return e;
            }
        }
        return null;
    }

}
