package fr.univcotedazur.polyevent.interfaces.payment;

import fr.univcotedazur.polyevent.entities.Organizer;
import fr.univcotedazur.polyevent.entities.Supplier;

public interface Bank {
    boolean payAllValue(Organizer organizer, double value);
    boolean pay(Long OrganizerId, double value);
}
