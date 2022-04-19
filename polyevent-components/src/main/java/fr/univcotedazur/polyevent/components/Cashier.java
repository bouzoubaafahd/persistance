package fr.univcotedazur.polyevent.components;

import fr.univcotedazur.polyevent.entities.*;
import fr.univcotedazur.polyevent.interfaces.payment.Bank;
import fr.univcotedazur.polyevent.interfaces.payment.Payment;
import fr.univcotedazur.polyevent.interfaces.invoice.InvoiceCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan("fr.univcotedazur")
public class Cashier implements Payment {
    private static final Logger LOG = LoggerFactory.getLogger(Cashier.class);

    @Autowired
    private InMemoryDatabase memory;

    @Autowired
    private InvoiceCalculator invoiceHandler;

    @Autowired
    private Bank bankProxy;


    @Override
    public boolean payTotalValue(Organizer organizer, float value) {
        return bankProxy.payAllValue(organizer ,value);
    }

    @Override
    public boolean payEvent(Long organizerId, Long eventId) {
        LOG.info("POLYEVENT:Cashier-Component: paying event "+ eventId + " for organizer " + organizerId);
        return bankProxy.pay(organizerId, invoiceHandler.getTotalPriceToPay(eventId));
    }
}
