package fr.univcotedazur.polyevent.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Invoice {


    private Long id;


    private ArrayList<RoomsReservation> rooms;


    private ArrayList<SupplierService> supplierServicesComposition;

    public Invoice() {
    }

    public Invoice(Long eventId) {
        this.id = eventId;
        this.rooms = new  ArrayList();
        this.supplierServicesComposition = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public ArrayList<RoomsReservation> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<RoomsReservation> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<SupplierService> getServices() {
        return supplierServicesComposition;
    }

    public void setServices(ArrayList<SupplierService> services) {
        this.supplierServicesComposition = services;
    }
}
