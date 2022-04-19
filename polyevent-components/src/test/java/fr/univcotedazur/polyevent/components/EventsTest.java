package fr.univcotedazur.polyevent.components;


import fr.univcotedazur.polyevent.entities.*;
import fr.univcotedazur.polyevent.interfaces.database.Finder;
import fr.univcotedazur.polyevent.utils.DateHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static fr.univcotedazur.polyevent.utils.DateHandler.createAFutureTimeSlot;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = Events.class)
public class EventsTest {

    @Autowired
    InMemoryDatabase memory;

    @Autowired
    Finder finder;

    @Autowired
    Events events;

    @Autowired
    RoomInfo roomInfo;


    ArrayList<Service> services;
    Event event;
    TimeSlot timeSlot;
    TimeSlot timeSlot1;
    TimeSlot timeSlot2;
    ArrayList <RoomsReservation>   availablerooms;


    @BeforeEach
    public void setUp(){
        memory.init();
        services  = new ArrayList<>();
        services.add(memory.listServicesCLEANING.get(1));
        services.add(memory.listeServicesGAS.get(1));
        services.add(memory.listServicesSECURITY.get(1));
        services.add(memory.listServicesELECTRICITY.get(2));
        event = memory.listEvents.get(0);
        timeSlot = createAFutureTimeSlot(24,3);
        timeSlot1 = createAFutureTimeSlot(25,3);
        timeSlot2 = createAFutureTimeSlot(26,3);
        ArrayList<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(timeSlot);
        timeSlots.add(timeSlot1);
        timeSlots.add(timeSlot2);
        event.setServices(services);
        availablerooms = new ArrayList<>();
        for(TimeSlot ts : timeSlots){
            availablerooms.add(new RoomsReservation( memory.listRooms.get(5).getId(), ts) );
            availablerooms.add(new RoomsReservation( memory.listRooms.get(6).getId(), ts) );
            availablerooms.add(new RoomsReservation( memory.listRooms.get(7).getId(), ts) );
        }
        event.setRooms(availablerooms);
    }


    @Test
    public void validateEligibleEventTest() {
        assertTrue(events.validateEvent(event.getId()));
        assertTrue(event.getEventStatus().equals(EventStatus.VALIDATED));
        for (RoomsReservation roomsReservation : event.getRooms()) {
            assertTrue(memory.listReservedRooms.containsKey(roomsReservation.getRoomId()));
        }
    }

    @Test
    public void validateNotEligibleEventStatusTest(){
        event.setEventStatus(EventStatus.VALIDATED);
        assertFalse(events.validateEvent(event.getId()));

    }


    @Test
    public void validateNotEligibleEventRoomTest(){
     RoomsReservation roomsReservation = new RoomsReservation(roomInfo.listAllRooms().get(0).getId() ,DateHandler.createAFutureTimeSlot(2,2) );
     RoomsReservation roomsReservation1 = new RoomsReservation(roomInfo.listAllRooms().get(0).getId() ,DateHandler.createAFutureTimeSlot(4, 4) );

     availablerooms.add(roomsReservation);
     availablerooms.add(roomsReservation1);
        event.setRooms(availablerooms);
        assertFalse(events.validateEvent(event.getId()));


    }



}
