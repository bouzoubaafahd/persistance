package fr.univcotedazur.polyevent.entities;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class Ticket {

    private Long id;

    private String title;

    private String description;
    private boolean done;

    private List<Equipment> faultyEquipment = new ArrayList<>();

    // constructeur
    public Ticket() {
    }

    public Ticket(String title) {
        this.title = title;
        this.done = false;
    }

    public Ticket(String title, String description) {
        this.title = title;
        this.description = description;
        this.done = false;
    }

    public Ticket(String title, String description, List<Equipment> faultyEquipment) {
        this.title = title;
        this.description = description;
        this.faultyEquipment = faultyEquipment;
        this.done = false;
    }

    // getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Equipment> getFaultyEquipment() {
        return faultyEquipment;
    }
    public void setFaultyEquipment(List<Equipment> faultyEquipment) {
        this.faultyEquipment = faultyEquipment;
    }
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
}
