package fr.univcotedazur.polyevent.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = BankProxy.class)
public class BankProxyTest {

    @Autowired
    InMemoryDatabase memory;

    @Autowired
    BankProxy proxy;

    @BeforeEach
    public void setUp() {
        memory.init();
    }

  /**  @Test
    public void validPaymentTest() {
        assertTrue(proxy.pay(memory.listOrganizers.get(0).getId(),200));
        assertTrue(proxy.pay(memory.listOrganizers.get(1).getId(),200));
    }

    @Test
    public void invalidPaymentTest() {
        assertFalse(proxy.pay(memory.listOrganizers.get(2).getId(),200));
    }
    */


}
