package fr.univcotedazur.polyevent.interfaces.room;

import fr.univcotedazur.polyevent.entities.Room;
import fr.univcotedazur.polyevent.entities.TimeSlot;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public interface RoomExplorator {
    ArrayList<Room> listAllRooms();
    Room getRoom(Long id);
}
