package fr.univcotedazur.polyevent.interfaces.invoice;

import fr.univcotedazur.polyevent.entities.Invoice;

import java.util.ArrayList;

public interface InvoiceProcessor {
    public Invoice createInvoice(Long eventId);
}
