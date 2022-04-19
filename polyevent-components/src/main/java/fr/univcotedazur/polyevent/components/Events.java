 package fr.univcotedazur.polyevent.components;

import fr.univcotedazur.polyevent.entities.Event;
import fr.univcotedazur.polyevent.entities.EventStatus;
import fr.univcotedazur.polyevent.interfaces.database.Finder;
import fr.univcotedazur.polyevent.interfaces.event.EventAvailabilityChecker;
import fr.univcotedazur.polyevent.interfaces.event.EventValidator;
import fr.univcotedazur.polyevent.interfaces.invoice.InvoiceProcessor;
import fr.univcotedazur.polyevent.interfaces.scheduler.RoomAvailabilityChecker;
import fr.univcotedazur.polyevent.interfaces.scheduler.RoomReserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan("fr.univcotedazur")
public class Events implements EventValidator, EventAvailabilityChecker {

    private static final Logger LOG = LoggerFactory.getLogger(Events.class);


    @Autowired
    private InMemoryDatabase database;

    @Autowired
    private Finder finder;

    @Autowired
    private RoomAvailabilityChecker roomAvailabilityChecker;

    @Autowired
    private RoomReserver scheduler;

    @Autowired
    private InvoiceProcessor invoiceHandler;

    @Override
    public boolean checkRoomsAvailabilityOfEvent(Long eventId) {
        return roomAvailabilityChecker.checkRoomsAvailabilityOfEvent(eventId);
    }

    @Override
    public boolean validateEvent(Long eventId) {

        LOG.info("POLYEVENT:Events-Component: validation of event by campus manager " + eventId);

        Event event = finder.findEventById(eventId);
        if (checkRoomsAvailabilityOfEvent(eventId) && event.getEventStatus() == EventStatus.PENDING) {
            event.setEventStatus(EventStatus.VALIDATED);
            reserveRoomsForEvent(eventId);
            invoiceHandler.createInvoice(eventId);
            return true;
        }
        return false;
    }

    @Override
    public boolean rejectEvent(Long eventId) {
        Event event = finder.findEventById(eventId);
        if (finder.findEventById(eventId).getEventStatus() == EventStatus.PENDING) {
            database.listEvents.remove(event);
        }
        return !database.listEvents.contains(event);
    }

    public void reserveRoomsForEvent(Long eventId) {
        scheduler.reserveRoomsForEvent(eventId);
    }
}
