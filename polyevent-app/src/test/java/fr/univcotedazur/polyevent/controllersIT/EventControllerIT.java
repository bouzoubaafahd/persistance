package fr.univcotedazur.polyevent.controllersIT;


import fr.univcotedazur.polyevent.components.InMemoryDatabase;
import fr.univcotedazur.polyevent.controllers.EventController;
import fr.univcotedazur.polyevent.entities.Event;
import org.apache.catalina.startup.Bootstrap;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc
@ComponentScan(basePackages = {"fr.univcotedazur.polyevent.components" , "fr.univcotedazur.polyevent.controllers" , "fr.univcotedazur.polyevent.interfaces" })
@EntityScan(basePackages = {"fr.univcotedazur.polyevent.components" , "fr.univcotedazur.polyevent.controllers" , "fr.univcotedazur.polyevent.interfaces" })
@SpringBootTest(classes = {InMemoryDatabase.class , EventControllerIT.class})
public class EventControllerIT {
    @Autowired
    InMemoryDatabase database;

    @Autowired
    private MockMvc  mockMvc;



    Event event;

    @BeforeEach
    void setUp(){
        event = database.listEvents.get(0);}

    @Test
    void getEventsTest() throws Exception {
        mockMvc.perform(get(EventController.EVENT_URL)
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)));
    }


   @Test
    void getEventByIdTest() throws Exception {

        mockMvc.perform(get(EventController.EVENT_URL + "/" + event.getId() )
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id" , Matchers.is(event.getId())));

    }



    @Test
    void getAvailableRoomTest() throws Exception {
        mockMvc.perform(get(EventController.EVENT_URL + "/checkRoomAvailability/" + event.getId() )
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean());

    }

    @Test
    void getPendingEventsTest() throws Exception {
        mockMvc.perform(get(EventController.EVENT_URL + "/pending")
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$" , hasSize(3)))
                .andExpect(jsonPath("$").isArray());

    }



    @Test
    void getValidatedEventsTest() throws Exception {
        mockMvc.perform(get(EventController.EVENT_URL + "/validated")
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$" , hasSize(0)))
                .andExpect(jsonPath("$").isArray());

    }


    @Test
    void postValidEventTest() throws Exception {
        mockMvc.perform(post(EventController.EVENT_URL + "/validate/" + event.getId() )
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean());

    }



}
