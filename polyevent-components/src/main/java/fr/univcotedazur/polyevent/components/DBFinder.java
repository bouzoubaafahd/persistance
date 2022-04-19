package fr.univcotedazur.polyevent.components;

import fr.univcotedazur.polyevent.entities.*;
import fr.univcotedazur.polyevent.interfaces.database.Finder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@ComponentScan("fr.univcotedazur")
public class DBFinder implements Finder {

    @Autowired
    private InMemoryDatabase database;

    @Override
    public Room findRoomById(Long id) {
        for (Room room : database.listRooms) {
            if (room.getId().equals(id)) {
                return room;
            }
        }
        return null;
    }

    @Override
    public Service findServiceById(Long id) {
        for (Service service : database.listServices) {
            if (service.getId().equals(id)) {
                return service;
            }
        }
        return null;
    }

    @Override
    public Event findEventById(Long id) {
        for (Event event : database.listEvents) {
            if (event.getId().equals(id)) {
                return event;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Event> findEventByStatus(EventStatus status) {
        ArrayList<Event> events = new ArrayList<>();
        for (Event event : database.listEvents) {
            if (event.getEventStatus().equals(status)) {
                events.add(event);
            }
        }
        return events;
    }

    @Override
    public Supplier findSupplierById(Long id) {
        for (Supplier supplier : database.listSuppliers) {
            if (supplier.getId().equals(id)) {
                return supplier;
            }
        }
        return null;
    }

    @Override
    public Supplier findSupplierOfService(Service service){
        for(Supplier supplier : database.listSuppliers){
            if(supplier.getServices().contains(service)){
                return supplier;
            }
        }
        return null;
    }

    @Override
    public Invoice findInvoiceById(Long id) {
        for (Invoice invoice : database.listeInvoices) {
            if (invoice.getId().equals(id)) {
                return invoice;
            }
        }
        return null;
    }

    @Override
    public float findRoomPrice(Long id) {
        if (database.listPricePerRoom.containsKey(id)) {
            return database.listPricePerRoom.get(id);
        }
        return Float.NaN;
    }

    @Override
    public Organizer findOrganizerById(Long id) {
        for (Organizer organizer : database.listOrganizers) {
            if (organizer.getId().equals(id)) {
                return organizer;
            }
        }
        return null;
    }

    @Override
    public Organizer findOrganizerOfEvent(Event event) {
        for (Organizer organizer : database.listOrganizers) {
            if (organizer.getEvents().contains(event)) {
                return organizer;
            }
        }
        return null;
    }
}
