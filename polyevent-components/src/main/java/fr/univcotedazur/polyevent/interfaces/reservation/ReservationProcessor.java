package fr.univcotedazur.polyevent.interfaces.reservation;

import fr.univcotedazur.polyevent.components.Reservation;
import fr.univcotedazur.polyevent.entities.Event;

public interface ReservationProcessor {
    public Event validate();
}
