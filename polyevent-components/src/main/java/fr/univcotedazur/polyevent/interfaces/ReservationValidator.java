package fr.univcotedazur.polyevent.interfaces;

import fr.univcotedazur.polyevent.components.Reservation;
import fr.univcotedazur.polyevent.entities.Event;
import fr.univcotedazur.polyevent.entities.Invoice;
import fr.univcotedazur.polyevent.entities.Room;
import fr.univcotedazur.polyevent.entities.TimeSlot;

import java.util.HashMap;

public interface ReservationValidator {

    Invoice confirm(Reservation reservation , Event event);

    boolean reject(Reservation reservation);

    void reserveRooms(  HashMap<Room, TimeSlot> choosenRooms );


}
