package fr.univcotedazur.polyevent.components;

import fr.univcotedazur.polyevent.entities.Organizer;
import fr.univcotedazur.polyevent.interfaces.database.Finder;
import fr.univcotedazur.polyevent.interfaces.organizer.OrganizerExplorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@ComponentScan("fr.univcotedazur")
public class OrganizerInfo implements OrganizerExplorator {

    @Autowired
    private InMemoryDatabase database;

    @Autowired
    private Finder finder;

    @Override
    public ArrayList<Organizer> getOrganizers() {
        return database.listOrganizers;
    }

    public Organizer getOrganizer(Long id) {
        return finder.findOrganizerById(id);
    }
}
