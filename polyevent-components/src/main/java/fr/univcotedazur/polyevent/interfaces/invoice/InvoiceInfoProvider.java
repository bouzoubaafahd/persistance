package fr.univcotedazur.polyevent.interfaces.invoice;

import fr.univcotedazur.polyevent.entities.Invoice;

import java.util.ArrayList;
import java.util.HashMap;

public interface InvoiceInfoProvider {
    public ArrayList<Invoice> getInvoices();
    public HashMap<Long, Float> getSuppliersPayment(Long invoiceId);
    public float getRoomsPrice(Long invoiceId);
}
