package fr.univcotedazur.polyevent.controllers;

import fr.univcotedazur.polyevent.interfaces.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    public static final String PAYMENT_URL = "/payments";

    @Autowired
    private Payment cashier;

    @PostMapping(PAYMENT_URL + "/{idOrganizer}/pay/{idEvent}")
    public ResponseEntity<Boolean> payEvent(@PathVariable("idOrganizer") Long idOrganizer,@PathVariable("idEvent") Long idEvent) {
        return ResponseEntity.ok(cashier.payEvent(idOrganizer, idEvent));
    }

}
