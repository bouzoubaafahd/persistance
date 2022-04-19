package fr.univcotedazur.polyevent.components;

import fr.univcotedazur.polyevent.entities.Equipment;
import fr.univcotedazur.polyevent.entities.Ticket;
import fr.univcotedazur.polyevent.interfaces.EquipmentReporter;
import fr.univcotedazur.polyevent.interfaces.ticket.TicketFinder;
import fr.univcotedazur.polyevent.interfaces.ticket.TicketModifier;
import fr.univcotedazur.polyevent.interfaces.ticket.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@ComponentScan("fr.univcotedazur")
public class TicketingSystem implements TicketModifier, TicketFinder, TicketValidator {

    @Autowired
    private InMemoryDatabase memory;

    @Autowired
    private EquipmentReporter RoomEquipments;

    @Override
    public List<Ticket> getAllTickets() {
        return memory.listeTikets;
    }

    @Override
    public Ticket getTicket(String title) {
        Predicate<Ticket> byTitle = Ticket -> Ticket.getTitle().equals(title);

        List<Ticket> listTickets = memory.listeTikets.stream().filter(byTitle)
                .collect(Collectors.toList());

        if(listTickets.size() == 0){
            return null;
        }
        else{
            return listTickets.get(0);
        }
    }

    @Override
    public Ticket addNewTicket(Ticket newTicket) {
        Ticket ticket = this.getTicket(newTicket.getTitle());

        if(ticket == null){
            memory.listeTikets.add(newTicket);
            return newTicket;
        }
        else{
            return null;
        }
    }

    @Override
    public void deleteTicket(Ticket ticket) {
        Ticket oldTicket = this.getTicket(ticket.getTitle());
        memory.listeTikets.remove(oldTicket);
    }

    @Override
    public Ticket modifyTicket(Ticket ticket) {
        deleteTicket(ticket);
        Ticket result = addNewTicket(ticket);
        return result;
    }

    @Override
    public boolean addFaultyEquipments(Ticket ticket, Long idEq, Long idRoom) {
        Ticket oldTicket = this.getTicket(ticket.getTitle());
        Equipment equipment = this.memory.getEquipmentById(idEq);
        if(equipment != null){
            ticket.getFaultyEquipment().add(equipment);
            RoomEquipments.addFaultyEquipment(idRoom, idEq);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean validateTicket(Ticket ticket) {
        Ticket oldTicket = this.getTicket(ticket.getTitle());
        oldTicket.setDone(true);
        return oldTicket.isDone();
    }
}
