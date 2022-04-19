package fr.univcotedazur.polyevent.interfaces.ticket;

import fr.univcotedazur.polyevent.entities.Ticket;

public interface TicketValidator {
    boolean validateTicket(Ticket ticket);
}
