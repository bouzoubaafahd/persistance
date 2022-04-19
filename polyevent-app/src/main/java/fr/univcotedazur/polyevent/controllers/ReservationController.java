package fr.univcotedazur.polyevent.controllers;

import fr.univcotedazur.polyevent.components.Reservation;
import fr.univcotedazur.polyevent.entities.*;
import fr.univcotedazur.polyevent.interfaces.ReservationValidator;
import fr.univcotedazur.polyevent.interfaces.reservation.ReservationInfoProvider;
import fr.univcotedazur.polyevent.interfaces.reservation.ReservationModifier;
import fr.univcotedazur.polyevent.interfaces.reservation.ReservationProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class ReservationController {

    public static final String RESERVATION_URI = "/reservation";
    public static final String dateFormat = "yyyy-MM-dd HH:mm";

    @Autowired
    private ReservationInfoProvider info;

    @Autowired
    private ReservationModifier modifier;

    @Autowired
    private ReservationProcessor processor;

    @GetMapping(RESERVATION_URI + "/rooms")
    public ResponseEntity<List<RoomsReservation>> showRooms() {
        return ResponseEntity.ok(info.getChoosenRooms());
    }

    @GetMapping(RESERVATION_URI + "/services")
    public ResponseEntity<ArrayList<Service>> showServices() {
        return ResponseEntity.ok(info.getServices());
    }

    @GetMapping(RESERVATION_URI + "/organizer")
    public ResponseEntity<Organizer> getOrganizer() {
        return ResponseEntity.ok(info.getOrganizer());
    }

    @PostMapping(RESERVATION_URI + "/addRoom/{roomId}/{start}/{end}")
    public ResponseEntity<List<RoomsReservation> > addRoomToReservation(@PathVariable("roomId") Long roomId, @PathVariable("start") @DateTimeFormat(pattern = dateFormat) Date start,@PathVariable("end") @DateTimeFormat(pattern = dateFormat) Date end) {
        modifier.updateReservation(roomId, start, end);
        return ResponseEntity.ok(info.getChoosenRooms());
    }

    @PostMapping(RESERVATION_URI + "/addService/{serviceId}")
    public ResponseEntity<ArrayList<Service>> addServiceToReservation(@PathVariable("serviceId") Long serviceId) {
        modifier.addService(serviceId);
        return ResponseEntity.ok(info.getServices());
    }

    @PostMapping(RESERVATION_URI + "/setOrganizer/{organizerId}")
    public ResponseEntity<Organizer> setOrganizer(@PathVariable("organizerId") Long organizerId) {
        modifier.setOrganizer(organizerId);
        return ResponseEntity.ok(info.getOrganizer());
    }

    @PostMapping(RESERVATION_URI + "/validate")
    public ResponseEntity<Event> validateReservation() {
        return ResponseEntity.ok(processor.validate());
    }

}
