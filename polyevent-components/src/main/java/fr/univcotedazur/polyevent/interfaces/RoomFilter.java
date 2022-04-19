package fr.univcotedazur.polyevent.interfaces;

import fr.univcotedazur.polyevent.entities.Room;
import fr.univcotedazur.polyevent.entities.RoomType;
import fr.univcotedazur.polyevent.entities.TimeSlot;

import java.util.ArrayList;

public interface RoomFilter {

    ArrayList<Room> listAvailableRooms(TimeSlot timeSlot);
    ArrayList<Room> listAvailableRooms(TimeSlot timeSlot, int nbParticipants);
    ArrayList<Room> listAvailableRooms(TimeSlot timeSlot,RoomType roomType);
    ArrayList<Room> listAvailableRooms(TimeSlot timeSlot, int nbParticipants, RoomType roomType);


}
