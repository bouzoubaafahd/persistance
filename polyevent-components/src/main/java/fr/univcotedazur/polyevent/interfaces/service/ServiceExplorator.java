package fr.univcotedazur.polyevent.interfaces.service;

import fr.univcotedazur.polyevent.entities.Service;

import java.util.ArrayList;
import java.util.Set;

public interface ServiceExplorator {

    ArrayList<Service> listServices();
    Service getService(Long name);

}
