package fr.univcotedazur.polyevent.controllers;


import fr.univcotedazur.polyevent.components.InvoiceHandler;
import fr.univcotedazur.polyevent.entities.Invoice;
import fr.univcotedazur.polyevent.interfaces.invoice.InvoiceInfoProvider;
import fr.univcotedazur.polyevent.interfaces.invoice.InvoiceProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.HashMap;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
public class InvoiceController {

    public static final String BASE_URI = "/invoices";

    @Autowired
    private InvoiceInfoProvider infoProvider;

    @Autowired
    private InvoiceProcessor invoiceHandler;

    @GetMapping(path = InvoiceController.BASE_URI, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Invoice>> listAllInvoices() {
        return ResponseEntity.ok(infoProvider.getInvoices());
    }

    @PostMapping(path = InvoiceController.BASE_URI + "/create/{id}")
    public ResponseEntity<Invoice> createInvoice(@PathVariable("id") Long eventId) {
        return ResponseEntity.ok(invoiceHandler.createInvoice(eventId));
    }

    @GetMapping(path = InvoiceController.BASE_URI + "/suppliersPayment/{id}")
    public ResponseEntity<HashMap<Long, Float>> suppliersPayment(@PathVariable("id") Long invoiceId) {
        return ResponseEntity.ok(infoProvider.getSuppliersPayment(invoiceId));
    }

    @GetMapping(path = InvoiceController.BASE_URI + "/roomsPrice/{id}")
    public ResponseEntity<Float> roomPrice(@PathVariable("id") Long invoiceId) {
        return ResponseEntity.ok(infoProvider.getRoomsPrice(invoiceId));
    }


}

