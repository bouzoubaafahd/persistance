package fr.univcotedazur.polyevent.interfaces.payment;

import fr.univcotedazur.polyevent.entities.Organizer;
import fr.univcotedazur.polyevent.entities.Service;
import fr.univcotedazur.polyevent.entities.Supplier;
import fr.univcotedazur.polyevent.exceptions.SupplierNotFoundxException;

import java.util.ArrayList;

public interface Payment {
    boolean payTotalValue(Organizer organizer, float value); // remove that

    public boolean payEvent(Long organizerId, Long eventId);
}
