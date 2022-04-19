package fr.univcotedazur.polyevent.components;

import fr.univcotedazur.polyevent.entities.*;
import fr.univcotedazur.polyevent.utils.DateHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Reservation.class)
class ReservationTest {

    @Autowired
    InMemoryDatabase memory;

    @Autowired
    Reservation reservation;

     @BeforeEach
    void setUp() {
        memory.init();
    }


    @Test
    public void testUpdateReservation() {
        Room selectedRoom = memory.listRooms.get(5);
        TimeSlot timeSlot = DateHandler.createAFutureTimeSlot(4, 4);
        reservation.updateReservation(selectedRoom.getId(), timeSlot );
        assertTrue(reservation.getChoosenRooms().contains(new RoomsReservation(selectedRoom.getId() , timeSlot)));
    }

   @Test
   public void validReservationTest() {
       Room selectedRoom = memory.listRooms.get(5);
       reservation.updateReservation(selectedRoom.getId(), DateHandler.createAFutureTimeSlot(4, 4));
       assertEquals(3, memory.listEvents.size());
       reservation.setOrganizer(memory.listOrganizers.get(0).getId());
       Event event =  reservation.validate();
       assertNotNull(event);
       assertEquals(event.getEventStatus() , EventStatus.PENDING);
       assertEquals(event.getPaymentStatus(), PaymentStatus.NOT_PAID);
       assertNotNull(event.getServices());
       assertNotNull(event.getRooms());
   }

    @Test
    public void validReservationDBTest() {
        Room selectedRoom = memory.listRooms.get(5);
        reservation.updateReservation(selectedRoom.getId(), DateHandler.createAFutureTimeSlot(4, 4));
        assertEquals(3, memory.listEvents.size());
        reservation.setOrganizer(memory.listOrganizers.get(0).getId());
        reservation.validate();
        assertEquals(4, memory.listEvents.size());
    }



}
