package fr.univcotedazur.polyevent.components;

import fr.univcotedazur.polyevent.entities.Event;
import fr.univcotedazur.polyevent.entities.EventStatus;
import fr.univcotedazur.polyevent.interfaces.database.Finder;
import fr.univcotedazur.polyevent.interfaces.event.EventInfoProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@ComponentScan("fr.univcotedazur")
public class EventInfo implements EventInfoProvider {

    @Autowired
    private InMemoryDatabase memory;

    @Autowired
    private Finder finder;

    @Override
    public ArrayList<Event> getEvents() {
        return memory.listEvents;
    }

    @Override
    public Event getEvent(Long id) {
        return finder.findEventById(id);
    }

    @Override
    public ArrayList<Event> getPendingEvents() {
        return finder.findEventByStatus(EventStatus.PENDING);
    }

    @Override
    public ArrayList<Event> getValidatedEvents() {
        return finder.findEventByStatus(EventStatus.VALIDATED);
    }


}
