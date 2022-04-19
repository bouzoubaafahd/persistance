package fr.univcotedazur.polyevent.components;

import fr.univcotedazur.polyevent.entities.Room;
import fr.univcotedazur.polyevent.entities.RoomsReservation;
import fr.univcotedazur.polyevent.entities.TimeSlot;
import fr.univcotedazur.polyevent.interfaces.database.Finder;
import fr.univcotedazur.polyevent.interfaces.scheduler.RoomAvailabilityChecker;
import fr.univcotedazur.polyevent.interfaces.scheduler.RoomReserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static fr.univcotedazur.polyevent.utils.DateHandler.areOverlapping;

@Component
@ComponentScan("fr.univcotedazur")
public class Scheduler implements RoomAvailabilityChecker, RoomReserver {

    @Autowired
    private InMemoryDatabase memory;

    @Autowired
    private Finder finder;

    @Override
    public boolean isRoomAvailable(Room room, TimeSlot timeSlot) {
        if(memory.listReservedRooms.containsKey(room.getId())) {
            for(TimeSlot reservedTimeSlot : memory.listReservedRooms.get(room.getId())) {
                if(areOverlapping(reservedTimeSlot, timeSlot)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isRoomAvailable(Long id, Date startDate, Date endDate) {
        return isRoomAvailable(finder.findRoomById(id), new TimeSlot(startDate, endDate));
    }

    @Override
    public ArrayList<Room> listUnavailableRooms(TimeSlot timeSlot) {
        ArrayList<Room> rooms = new ArrayList<>();
        for(Room room : memory.listRooms) {
            if(!isRoomAvailable(room, timeSlot)) {
                rooms.add(room);
            }
        }
        return rooms;
    }

    @Override
    public ArrayList<Room> listAvailableRooms(TimeSlot timeSlot) {
        ArrayList<Room> rooms = new ArrayList<>(memory.listRooms);
        rooms.removeAll(listUnavailableRooms(timeSlot));
        return rooms;
    }

    @Override
    public ArrayList<Room> listAvailableRooms(Date startDate, Date endDate) {
        return listAvailableRooms(new TimeSlot(startDate, endDate));
    }

    @Override
    public boolean checkRoomsAvailabilityOfEvent(Long eventId) {
        ArrayList <RoomsReservation> rooms = (ArrayList<RoomsReservation>) finder.findEventById(eventId).getRooms();
       for (RoomsReservation roomsReservation : rooms){
                if(!isRoomAvailable(roomsReservation.getRoomId(), roomsReservation.getTimeSlot().getStartDate(), roomsReservation.getTimeSlot().getEndDate())) {
                    return false;
                }
            }
        return true;
    }

    @Override
    public void reserveRoomsForEvent(Long eventId) {
        for (RoomsReservation roomsReservation : finder.findEventById(eventId).getRooms()) {

                memory.addReservedRoom(roomsReservation.getRoomId(), roomsReservation.getTimeSlot());
            }
    }



}
