package fr.univcotedazur.polyevent.interfaces.database;

import fr.univcotedazur.polyevent.entities.*;

import java.util.ArrayList;

public interface Finder {

    public Room findRoomById(Long id);

    public Service findServiceById(Long id);

    public Event findEventById(Long id);

    public ArrayList<Event> findEventByStatus(EventStatus status);

    public Supplier findSupplierById(Long id);

    public Supplier findSupplierOfService(Service service);

    public Invoice findInvoiceById(Long id);

    public float findRoomPrice(Long id);

    public Organizer findOrganizerById(Long id);

    public Organizer findOrganizerOfEvent(Event event);
}
