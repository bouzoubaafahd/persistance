package fr.univcotedazur.polyevent.controllersIT;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univcotedazur.polyevent.components.InMemoryDatabase;
import fr.univcotedazur.polyevent.controllers.InvoiceController;
import fr.univcotedazur.polyevent.entities.Event;
import fr.univcotedazur.polyevent.entities.EventStatus;
import fr.univcotedazur.polyevent.entities.Invoice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class InvoiceControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    InMemoryDatabase database;



    @Test
    void getServicesTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(InvoiceController.BASE_URI )
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getSupplierPaiementTest() throws Exception {
        Invoice invoice = database.listeInvoices.get(0);
        mockMvc.perform(get(InvoiceController.BASE_URI + "/suppliersPayment/" + invoice.getId() )
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap());
    }


    @Test
    void getRoomsPriceTest() throws Exception {
        Invoice invoice = database.listeInvoices.get(0);
        mockMvc.perform(get(InvoiceController.BASE_URI + "/roomsPrice/" + invoice.getId() )
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber());
    }

    @Test
    void postInvoiceTest() throws Exception {

        Event event = database.listEvents.get(0);
        event.setEventStatus(EventStatus.VALIDATED);
        String URI = InvoiceController.BASE_URI +"/create/"+event.getId();
        mockMvc.perform(post(URI)
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap());
    }



}
