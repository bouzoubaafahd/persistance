package fr.univcotedazur.polyevent.components;

import fr.univcotedazur.polyevent.entities.Room;
import fr.univcotedazur.polyevent.utils.DateHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {RoomInfo.class,InMemoryDatabase.class})
class RoomInfoTest {

    @Autowired
    RoomInfo scheduler;

    @Autowired
    InMemoryDatabase memory;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void listAvailableRooms() {
        System.out.println(DateHandler.createAFutureTimeSlot(2,2).getStartDate());
        Long id = memory.listRooms.get(0).getId();
        System.out.println(memory.listReservedRooms.get(id).get(0).getStartDate());
        ArrayList<Room> rooms = scheduler.listAvailableRooms(DateHandler.createAFutureTimeSlot(2,2));
        assertEquals(11, rooms.size());
    }


}
