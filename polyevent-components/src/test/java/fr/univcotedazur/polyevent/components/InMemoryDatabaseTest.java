package fr.univcotedazur.polyevent.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = InMemoryDatabase.class)
class InMemoryDatabaseTest {

    @Autowired
    InMemoryDatabase memory;

    @BeforeEach
    public void setUp() {
        memory = new InMemoryDatabase();
    }

    @Test
    public void testNumberOfRoomsInDatabase() {
        assertEquals(12, memory.listRooms.size());
    }

    @Test
    public void testNumberOfReservedRooms() {
        assertEquals(3, memory.listReservedRooms.size());
    }

    @Test
    public void testSupplierServiceList(){
        for(int i = 0 ; i < memory.listSuppliers.size() ; i++ ){
            assertEquals(10 , memory.listSuppliers.get(i).getServices().size());
        }
    }




}
