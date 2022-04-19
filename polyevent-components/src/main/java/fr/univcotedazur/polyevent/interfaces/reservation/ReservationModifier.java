package fr.univcotedazur.polyevent.interfaces.reservation;

import fr.univcotedazur.polyevent.entities.Service;
import fr.univcotedazur.polyevent.entities.TimeSlot;

import java.util.Date;

public interface ReservationModifier {

    public void updateReservation(Long roomId, Date start, Date end);

    public void addService(Long serviceId);

    public void setOrganizer(Long organizerId);

}
