package fr.univcotedazur.polyevent.interfaces.reservation;

import fr.univcotedazur.polyevent.entities.Organizer;
import fr.univcotedazur.polyevent.entities.RoomsReservation;
import fr.univcotedazur.polyevent.entities.Service;
import fr.univcotedazur.polyevent.entities.TimeSlot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ReservationInfoProvider {

    public List<RoomsReservation> getChoosenRooms();

    public ArrayList<Service> getServices();

    public Organizer getOrganizer();

}
