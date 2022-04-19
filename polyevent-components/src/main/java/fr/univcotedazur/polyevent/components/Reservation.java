package fr.univcotedazur.polyevent.components;

import fr.univcotedazur.polyevent.entities.*;
import fr.univcotedazur.polyevent.interfaces.database.Finder;
import fr.univcotedazur.polyevent.interfaces.reservation.ReservationInfoProvider;
import fr.univcotedazur.polyevent.interfaces.reservation.ReservationModifier;
import fr.univcotedazur.polyevent.interfaces.reservation.ReservationProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


import java.util.*;


@Component
@ComponentScan("fr.univcotedazur")
public class Reservation implements ReservationModifier, ReservationProcessor, ReservationInfoProvider {

    private static final Logger LOG = LoggerFactory.getLogger(Reservation.class);

    @Autowired
    public InMemoryDatabase memory;

    @Autowired
    public Finder finder;

    private List<RoomsReservation>  choosenRooms = new ArrayList<>();
    private ArrayList<Service> services = new ArrayList<>();
    private Organizer organizer;

    @Override
    public void updateReservation(Long roomId, Date start, Date end) {

        LOG.info("POLYEVENT:Reservation-Component: Adding room" + roomId + " to reservation from " + start + " to " + end);

        TimeSlot timeSlot = new TimeSlot(start,end);
        updateReservation(roomId,timeSlot);
    }

    public void updateReservation(Long room, TimeSlot timeSlot) {
        RoomsReservation roomsReservation  = new RoomsReservation(room , timeSlot);
        if(!choosenRooms.contains(roomsReservation)) {
            choosenRooms.add(new RoomsReservation(room, timeSlot));
        }
    }
    @Override
    public void addService(Long serviceId){

        LOG.info("POLYEVENT:Reservation-Component: Adding service " + serviceId + " to reservation");


        Service service = finder.findServiceById(serviceId);
        addService(service);
    }


    public void addService(Service service) {
        if (!services.contains(service)) {
            services.add(service);
        }
    }

    @Override
    public List<RoomsReservation>  getChoosenRooms() {
        return choosenRooms;
    }

    public void setChoosenRooms(List<RoomsReservation> choosenRooms) {
        this.choosenRooms = choosenRooms;
    }

    @Override
    public ArrayList<Service> getServices() {
        return services;
    }

    @Override
    public Organizer getOrganizer() {
        return organizer;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public void resetReservation(){
        choosenRooms.clear();
        services.clear();
        organizer = null;
    }

    @Override
    public void setOrganizer(Long organizerId){

        LOG.info("POLYEVENT:Reservation-Component: set organizer "+ organizerId + " to reservation");


        organizer = finder.findOrganizerById(organizerId);
    }

    @Override
    public Event validate() {

        LOG.info("POLYEVENT:Reservation-Component: Validating reservation...");

        Event event = new Event();
        event.setRooms(new ArrayList<>(choosenRooms));
        event.setServices(new ArrayList<>(services));
        event.setEventStatus(EventStatus.PENDING);
        event.setPaymentStatus(PaymentStatus.NOT_PAID);
        memory.listEvents.add(event);
        organizer.getEvents().add(event);
        resetReservation();
        return event;
    }
}
