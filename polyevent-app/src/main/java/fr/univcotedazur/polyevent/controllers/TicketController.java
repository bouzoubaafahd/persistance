package fr.univcotedazur.polyevent.controllers;

import fr.univcotedazur.polyevent.entities.Ticket;
import fr.univcotedazur.polyevent.interfaces.ticket.TicketFinder;
import fr.univcotedazur.polyevent.interfaces.ticket.TicketModifier;
import fr.univcotedazur.polyevent.interfaces.ticket.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TicketController {
    public static final String BASE_URI = "/tickets";

    @Autowired
    private TicketModifier ticketingModifier;

    @Autowired
    private TicketFinder ticketingFinder;

    @Autowired
    private TicketValidator ticketingValidator;

    @GetMapping(path = BASE_URI, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Ticket>> listAllTickets() {
        System.out.println("Get all tickets");
        return new ResponseEntity<>(ticketingFinder.getAllTickets(), HttpStatus.OK);
    }

    @GetMapping(path = BASE_URI+"/{title}")
    public ResponseEntity<Ticket> getTicket(@PathVariable("title") String title) throws Exception {
        System.out.println("Get a ticket");
        Ticket ticket = ticketingFinder.getTicket(title);
        if( ticket != null)
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        else
            throw new Exception();
    }

    @PostMapping(path = BASE_URI, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Ticket> addNewTicket(@RequestBody Ticket ticket) {
        System.out.println("Adding new ticket");
        Ticket newTicket = ticketingModifier.addNewTicket(ticket);
        return new ResponseEntity<>(newTicket, HttpStatus.CREATED);
    }

    @PutMapping(path = BASE_URI, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity validateTicket(@RequestBody Ticket ticket) throws Exception {
        Ticket oldTicket = ticketingFinder.getTicket(ticket.getTitle());

        if( oldTicket != null){
            ticketingValidator.validateTicket(oldTicket);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        else
            throw new Exception();
    }

    @PutMapping(path = BASE_URI+"/{idEq}/{idRoom}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity addFaultyEquipments(@PathVariable("idEq") Long idEq, @PathVariable("idRoom") Long idRoom,
                                              @RequestBody Ticket ticket) throws Exception {
        Ticket oldTicket = ticketingFinder.getTicket(ticket.getTitle());

        if( oldTicket != null){
            boolean result = ticketingModifier.addFaultyEquipments(oldTicket, idEq, idRoom);
            if(result)
                return new ResponseEntity(HttpStatus.CREATED);
        }
        throw new Exception();
    }

    @PutMapping(path = BASE_URI+"/{idEq}/{idRoom}/{title}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity addFaultyEquipments2(@PathVariable("idEq") Long idEq, @PathVariable("idRoom") Long idRoom,
                                               @PathVariable("title") String title) throws Exception {
        Ticket oldTicket = ticketingFinder.getTicket(title);

        if( oldTicket != null){
            boolean result = ticketingModifier.addFaultyEquipments(oldTicket, idEq, idRoom);
            if(result)
                return new ResponseEntity(HttpStatus.CREATED);
        }
        throw new Exception();
    }
}
