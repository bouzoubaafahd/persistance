package fr.univcotedazur.polyevent.components;

import fr.univcotedazur.polyevent.entities.Service;
import fr.univcotedazur.polyevent.interfaces.database.Finder;
import fr.univcotedazur.polyevent.interfaces.service.ServiceExplorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@ComponentScan("fr.univcotedazur")
public class ServiceInfo implements ServiceExplorator {

    @Autowired
    private InMemoryDatabase database;

    @Autowired
    private Finder finder;

    @Override
    public ArrayList<Service> listServices() {
        return new ArrayList<>(database.listServices);
    }

    @Override
    public Service getService(Long name) {
        return finder.findServiceById(name);
    }

}
