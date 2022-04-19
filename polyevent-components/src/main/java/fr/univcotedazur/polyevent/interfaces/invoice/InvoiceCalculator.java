package fr.univcotedazur.polyevent.interfaces.invoice;

public interface InvoiceCalculator {
    public float getTotalPriceToPay(Long invoiceId);
}
