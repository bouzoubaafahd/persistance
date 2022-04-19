package fr.univcotedazur.polyevent.interfaces.room;

import fr.univcotedazur.polyevent.entities.Room;
import fr.univcotedazur.polyevent.entities.TimeSlot;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public interface RoomAvailabilityExplorator {
    public boolean isRoomAvailable(Long id, Date startDate, Date endDate);
    public ArrayList<Room> listAvailableRooms(Date startDate, Date endDate);
    public Map<Long, ArrayList<TimeSlot>> getReservedRooms();
    public ArrayList<TimeSlot> getReservationDates(Long id);
}
