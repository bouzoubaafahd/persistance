package fr.univcotedazur.polyevent.components;

import fr.univcotedazur.polyevent.entities.Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ServiceInfo.class)
public class ServiceCatalogTest {

    @Autowired
    ServiceInfo catalog;


    @Test
    void listServicesTest(){
        ArrayList<Service> result = catalog.listServices();
        assertEquals(50, result.size());
    }



}
