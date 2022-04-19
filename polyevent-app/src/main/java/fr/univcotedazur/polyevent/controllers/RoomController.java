package fr.univcotedazur.polyevent.controllers;

import fr.univcotedazur.polyevent.components.InMemoryDatabase;
import fr.univcotedazur.polyevent.components.RoomInfo;
import fr.univcotedazur.polyevent.entities.Room;
import fr.univcotedazur.polyevent.entities.TimeSlot;
import fr.univcotedazur.polyevent.interfaces.room.RoomAvailabilityExplorator;
import fr.univcotedazur.polyevent.interfaces.room.RoomExplorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class RoomController {

    public static final String ROOM_URI = "/rooms";
    public static final String dateFormat = "yyyy-MM-dd HH:mm";

    @Autowired
    private RoomExplorator roomInfo;

    @Autowired
    private RoomAvailabilityExplorator availabilityInfo;

    @GetMapping(path = ROOM_URI)
    public ResponseEntity<ArrayList<Room>> getAllRooms(){
        return ResponseEntity.ok(roomInfo.listAllRooms());
    }

    @GetMapping(ROOM_URI+"/{id}")
    public ResponseEntity<Room> getRoom(@PathVariable("id") Long id){
        if (roomInfo.getRoom(id) == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(roomInfo.getRoom(id));
    }

    // Exemple de requête : http://localhost:8080/rooms/isAvailable/0/startDate/2022-03-15 17:00/endDate/2022-03-15 19:00
    @GetMapping(ROOM_URI+"/isAvailable/{id}/startDate/{date1}/endDate/{date2}")
    public ResponseEntity<Boolean> isAvailable(@PathVariable("id") Long id, @PathVariable("date1") @DateTimeFormat(pattern = dateFormat) Date date1, @PathVariable("date2") @DateTimeFormat(pattern = dateFormat) Date date2){
        return ResponseEntity.ok(availabilityInfo.isRoomAvailable(id, date1, date2));
    }

    @GetMapping(ROOM_URI+"/availableRooms/{date1}/{date2}")
    public ResponseEntity<ArrayList<Room>> getAvailableRooms(@PathVariable("date1") @DateTimeFormat(pattern = dateFormat) Date date1, @PathVariable("date2") @DateTimeFormat(pattern = dateFormat) Date date2){
        return ResponseEntity.ok(availabilityInfo.listAvailableRooms(date1, date2));
    }

    @GetMapping(ROOM_URI+"/reservations")
    public ResponseEntity<Map<Long, ArrayList<TimeSlot>>> getReservedRooms(){
        return ResponseEntity.ok(availabilityInfo.getReservedRooms());
    }

    @GetMapping(ROOM_URI+"/reservations/{id}")
    public ResponseEntity<ArrayList<TimeSlot>> getRoomReservationDates(@PathVariable("id") Long id){
        return ResponseEntity.ok(availabilityInfo.getReservationDates(id));
    }

}
