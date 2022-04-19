package fr.univcotedazur.polyevent.interfaces.event;

public interface EventValidator {
    public boolean validateEvent(Long eventId);
    public boolean rejectEvent(Long eventId);
}
