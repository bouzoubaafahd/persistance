package fr.univcotedazur.polyevent.controllersIT;


import fr.univcotedazur.polyevent.components.InMemoryDatabase;
import fr.univcotedazur.polyevent.controllers.RoomController;
import fr.univcotedazur.polyevent.entities.Room;
import fr.univcotedazur.polyevent.entities.TimeSlot;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    InMemoryDatabase database;



    @Test
    void getServicesTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(RoomController.ROOM_URI )
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(12)));
    }

   /* @Test
    void getRoomByIdTest() throws Exception {
        Room room = database.listRooms.get(0);
        mockMvc.perform(get(RoomController.ROOM_URI + "/" + room.getId() )
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id" , Matchers.is( room.getId())));
    }

    */

    @Test
    void getReservationsTest() throws Exception {

        mockMvc.perform(get(RoomController.ROOM_URI +"/reservations" )
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.keys()" , hasItem("0")));
    }

    @Test
    void getReservationsByIdTest() throws Exception {
        Room room = database.listRooms.get(0);
        mockMvc.perform(get(RoomController.ROOM_URI +"/reservations/" + room.getId() )
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$" , hasSize(2)));
    }


}
