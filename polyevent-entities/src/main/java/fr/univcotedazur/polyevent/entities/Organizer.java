package fr.univcotedazur.polyevent.entities;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;

public class Organizer {


    private Long id;
    private String name;


    private ArrayList<Event> events = new ArrayList();
    private String creditCard;

    public Organizer() {
    }

    public Organizer(String name, String creditCard) {
        this.id = new Random().nextLong();
        this.name = name;
        this.creditCard = creditCard;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organizer)) return false;
        Organizer organizer = (Organizer) o;
        return Objects.equals(id, organizer.id) && Objects.equals(name, organizer.name)  && Objects.equals(creditCard, organizer.creditCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, creditCard);
    }
}
