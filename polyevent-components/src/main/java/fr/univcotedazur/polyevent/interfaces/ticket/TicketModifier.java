package fr.univcotedazur.polyevent.interfaces.ticket;

import fr.univcotedazur.polyevent.entities.Ticket;

public interface TicketModifier {
    Ticket addNewTicket(Ticket newTicket);
    void deleteTicket(Ticket ticket);
    Ticket modifyTicket(Ticket ticket);
    boolean addFaultyEquipments(Ticket ticket, Long idEq, Long idRoom);
}
