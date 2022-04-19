package fr.univcotedazur.polyevent.interfaces.scheduler;

import fr.univcotedazur.polyevent.entities.Room;
import fr.univcotedazur.polyevent.entities.TimeSlot;

import java.util.ArrayList;
import java.util.Date;

public interface RoomAvailabilityChecker {
    public boolean checkRoomsAvailabilityOfEvent(Long eventId);
    public boolean isRoomAvailable(Room room, TimeSlot timeSlot);
    public boolean isRoomAvailable(Long id, Date startDate, Date endDate);
    public ArrayList<Room> listUnavailableRooms(TimeSlot timeSlot);
    public ArrayList<Room> listAvailableRooms(TimeSlot timeSlot);
    public ArrayList<Room> listAvailableRooms(Date startDate, Date endDate);
}
