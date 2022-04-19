package fr.univcotedazur.polyevent.entities;

import javax.persistence.*;
import java.util.*;


public class Event {


    private Long id;
    private EventStatus eventStatus;
    private PaymentStatus paymentStatus;


    private ArrayList<Service> services;

    private List<RoomsReservation> rooms;

    public Event() {
        this.id = new Random().nextLong();
        services = new ArrayList<>();
        rooms = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public List<RoomsReservation> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomsReservation> rooms) {
        this.rooms = rooms;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) && eventStatus == event.eventStatus && paymentStatus == event.paymentStatus && Objects.equals(services, event.services) && Objects.equals(rooms, event.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventStatus, paymentStatus, services, rooms);
    }
}
