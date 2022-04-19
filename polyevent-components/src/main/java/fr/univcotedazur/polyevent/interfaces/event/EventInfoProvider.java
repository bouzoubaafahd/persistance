package fr.univcotedazur.polyevent.interfaces.event;

import fr.univcotedazur.polyevent.entities.Event;

import java.util.ArrayList;


public interface EventInfoProvider {
    public ArrayList<Event> getEvents();
    public Event getEvent(Long id);
    public ArrayList<Event> getPendingEvents();
    public ArrayList<Event> getValidatedEvents();
}
