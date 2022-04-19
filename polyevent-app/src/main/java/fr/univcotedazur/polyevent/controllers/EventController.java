package fr.univcotedazur.polyevent.controllers;

import fr.univcotedazur.polyevent.components.Events;
import fr.univcotedazur.polyevent.entities.Event;
import fr.univcotedazur.polyevent.interfaces.event.EventAvailabilityChecker;
import fr.univcotedazur.polyevent.interfaces.event.EventInfoProvider;
import fr.univcotedazur.polyevent.interfaces.event.EventValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Controller
public class EventController {

    public static final String EVENT_URL = "/events";

    @Autowired
    private EventInfoProvider infoProvider;

    @Autowired
    private EventAvailabilityChecker checker;

    @Autowired
    private EventValidator validator;

    @GetMapping(EVENT_URL)
    public ResponseEntity<ArrayList<Event>> getEvents() {
        return ResponseEntity.ok(infoProvider.getEvents());
    }

    @GetMapping(EVENT_URL + "/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable("id") Long id) {
        return ResponseEntity.ok(infoProvider.getEvent(id));
    }

    @GetMapping(EVENT_URL + "/checkRoomAvailability/{id}")
    public ResponseEntity<Boolean> getRoomAvailability(@PathVariable("id") Long eventId) {
        return ResponseEntity.ok(checker.checkRoomsAvailabilityOfEvent(eventId));
    }

    @GetMapping(EVENT_URL + "/pending")
    public ResponseEntity<ArrayList<Event>> getPendingEvents() {
        return ResponseEntity.ok(infoProvider.getPendingEvents());
    }

    @GetMapping(EVENT_URL + "/validated")
    public ResponseEntity<ArrayList<Event>> getValidatedEvents() {
        return ResponseEntity.ok(infoProvider.getValidatedEvents());
    }

    @PostMapping(EVENT_URL + "/validate/{id}")
    public ResponseEntity<Boolean> validateEvent(@PathVariable("id") Long id) {
        return ResponseEntity.ok(validator.validateEvent(id));
    }

    @PostMapping(EVENT_URL + "/reject/{id}")
    public ResponseEntity<Boolean> rejectEvent(@PathVariable("id") Long id) {
        return ResponseEntity.ok(validator.rejectEvent(id));
    }


}
