package fr.univcotedazur.polyevent.controllersIT;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univcotedazur.polyevent.components.InMemoryDatabase;
import fr.univcotedazur.polyevent.components.Reservation;
import fr.univcotedazur.polyevent.controllers.ReservationController;
import fr.univcotedazur.polyevent.entities.RoomsReservation;
import fr.univcotedazur.polyevent.entities.Service;
import fr.univcotedazur.polyevent.entities.TimeSlot;
import fr.univcotedazur.polyevent.interfaces.database.Finder;
import fr.univcotedazur.polyevent.utils.DateHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    Finder finder;


    @Autowired
    InMemoryDatabase database;

    @Autowired
    Reservation reservation;

    @Autowired
    private ObjectMapper objectMapper;

    HashMap<String  , ArrayList<TimeSlot >> choosenRooms;
    String roomId;
    ArrayList<TimeSlot> timeSlots;
    Service service;



    @BeforeEach
     void setUp(){
        choosenRooms = new HashMap<>();
        timeSlots = new ArrayList<>();
        timeSlots.add(DateHandler.createAFutureTimeSlot(1 , 2));
        roomId = "1";
        choosenRooms.put(roomId ,timeSlots );
        service = database.listServices.get(0);

    }

    @Test
    void postRoomsTest() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String startDate = dateFormat.format(timeSlots.get(0).getStartDate());
        String endDate = dateFormat.format(timeSlots.get(0).getEndDate());

        String URI = ReservationController.RESERVATION_URI + "/addRoom"+"/"+ roomId+"/" + startDate+  "/"+  endDate;
        mockMvc.perform(post(URI)
                .content(objectMapper.writeValueAsString(choosenRooms))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void postServiceTest() throws Exception {

        String URI = ReservationController.RESERVATION_URI +"/addService/"+service.getId();

        mockMvc.perform(post(URI)
                        .content(objectMapper.writeValueAsString(service.getId()))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray())
               .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getRoomsTest() throws Exception {
        mockMvc.perform(get(ReservationController.RESERVATION_URI + "/rooms")
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void getServicesTest() throws Exception {
        mockMvc.perform(get(ReservationController.RESERVATION_URI + "/rooms")
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

