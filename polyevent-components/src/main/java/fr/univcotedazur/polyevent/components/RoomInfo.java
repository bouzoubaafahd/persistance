package fr.univcotedazur.polyevent.components;

import fr.univcotedazur.polyevent.interfaces.database.Finder;
import fr.univcotedazur.polyevent.entities.Room;
import fr.univcotedazur.polyevent.entities.RoomType;
import fr.univcotedazur.polyevent.entities.TimeSlot;
import fr.univcotedazur.polyevent.interfaces.RoomFilter;
import fr.univcotedazur.polyevent.interfaces.room.RoomAvailabilityExplorator;
import fr.univcotedazur.polyevent.interfaces.room.RoomExplorator;
import fr.univcotedazur.polyevent.interfaces.scheduler.RoomAvailabilityChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


@Component
@ComponentScan("fr.univcotedazur")
public class RoomInfo implements RoomExplorator, RoomAvailabilityExplorator {

    @Autowired
    private InMemoryDatabase memory;

    @Autowired
    private Finder finder;

    @Autowired
    private RoomAvailabilityChecker scheduler; //RoomAvailabilityChecker

    @Override
    public ArrayList<Room> listAllRooms() {
        return memory.listRooms;
    }

    @Override
    public Room getRoom(Long id) {
        return finder.findRoomById(id);
    }

    public boolean isRoomAvailable(Room room, TimeSlot timeSlot) {
        return scheduler.isRoomAvailable(room, timeSlot);
    }

    @Override
    public boolean isRoomAvailable(Long id, Date startDate, Date endDate) {
        return scheduler.isRoomAvailable(id, startDate, endDate);
    }

    @Override
    public Map<Long, ArrayList<TimeSlot>> getReservedRooms() {
        return memory.listReservedRooms;
    }

    public ArrayList<Room> listUnavailableRooms(TimeSlot timeSlot) {
        return scheduler.listUnavailableRooms(timeSlot);
    }


    public ArrayList<Room> listAvailableRooms(TimeSlot timeSlot) {
        return scheduler.listAvailableRooms(timeSlot);
    }

    @Override
    public ArrayList<Room> listAvailableRooms(Date startDate, Date endDate) {
        return scheduler.listAvailableRooms(startDate, endDate);
    }

    @Override
    public ArrayList<TimeSlot> getReservationDates(Long id) {
        return memory.listReservedRooms.get(id);
    }
}
