package fr.univcotedazur.polyevent.components;

import fr.univcotedazur.polyevent.interfaces.payment.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;


@SpringBootTest(classes = {Cashier.class,InMemoryDatabase.class})
public class CashierTest {


    @Autowired
    private InMemoryDatabase memory;

    @Autowired
    private Payment cashier;



    @BeforeEach
    public void setUpContext() throws Exception {
        memory.init();
    }


}
