package fr.univcotedazur.polyevent.interfaces.ticket;

import fr.univcotedazur.polyevent.entities.Ticket;

import java.util.List;

public interface TicketFinder {
    List<Ticket> getAllTickets();
    Ticket getTicket(String title);
}
