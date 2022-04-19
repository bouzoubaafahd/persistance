package fr.univcotedazur.polyevent.controllers;

import fr.univcotedazur.polyevent.entities.Service;
import fr.univcotedazur.polyevent.interfaces.service.ServiceExplorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AdditionalServicesController {

    public static final String ADDITIONAL_SERVICES_URL = "/services";

    @Autowired
    private ServiceExplorator serviceInfo;

    @GetMapping(ADDITIONAL_SERVICES_URL)
    public ResponseEntity<ArrayList<Service>> getAdditionalServices() {
        return ResponseEntity.ok(serviceInfo.listServices());
    }

    @GetMapping(ADDITIONAL_SERVICES_URL + "/{id}")
    public ResponseEntity<Service> getAdditionalService(@PathVariable("id") Long id) {
        return ResponseEntity.ok(serviceInfo.getService(id));
    }

}
