package fr.univcotedazur.polyevent.controllers;

import fr.univcotedazur.polyevent.components.OrganizerInfo;
import fr.univcotedazur.polyevent.entities.Organizer;
import fr.univcotedazur.polyevent.interfaces.organizer.OrganizerExplorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class OrganizerController {

    public static final String ORGANIZER_URL = "/organizers";

    @Autowired
    private OrganizerExplorator organizerInfo;

    @GetMapping(ORGANIZER_URL)
    public ResponseEntity<ArrayList<Organizer>> getOrganizers() {
        return ResponseEntity.ok(organizerInfo.getOrganizers());
    }


}
