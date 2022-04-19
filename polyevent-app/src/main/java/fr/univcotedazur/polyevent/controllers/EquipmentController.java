package fr.univcotedazur.polyevent.controllers;

import fr.univcotedazur.polyevent.entities.Equipment;
import fr.univcotedazur.polyevent.interfaces.RoomEquipmentsVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class EquipmentController {
    public static final String BASE_URI = "/equipments";

    @Autowired
    private RoomEquipmentsVerifier roomEquipments;

    @GetMapping(path = BASE_URI+"/room/0/{idRoom}")
    public ResponseEntity<List<Equipment>> listAllEquipments(@PathVariable Long idRoom) {
        return new ResponseEntity<>(roomEquipments.listAllEquipments(idRoom), HttpStatus.OK);
    }

    @GetMapping(path = BASE_URI+"/room/{idRoom}")
    public ResponseEntity<List<Equipment>> listAllEquipmentsOfRoom(@PathVariable Long idRoom) {
        return new ResponseEntity<>(roomEquipments.listFaultyEquipments(idRoom), HttpStatus.OK);
    }

    @GetMapping(path = BASE_URI+"/ticket/{title}")
    public ResponseEntity<List<Equipment>> listFaultyEquipmentsOfTicket(@PathVariable Long title) {
        return new ResponseEntity<>(roomEquipments.listFaultyEquipmentsOfTicket(title), HttpStatus.OK);
    }
}
