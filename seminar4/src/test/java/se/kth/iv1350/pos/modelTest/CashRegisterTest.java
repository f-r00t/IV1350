package test.java.se.kth.iv1350.pos.modelTest;

import org.junit.jupiter.api.Test;

import main.java.se.kth.iv1350.pos.model.CashRegister;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashRegisterTest {

    @Test
    public void testAddMoneyIncreasesBalance() {
        CashRegister register = new CashRegister();
        register.addMoney(100.0f);
        assertEquals(100.0f, register.getBalance(), 0.01f);

        register.addMoney(50.0f);
        assertEquals(150.0f, register.getBalance(), 0.01f);
    }

    @Test
    public void testInitialBalanceIsZero() {
        CashRegister register = new CashRegister();
        assertEquals(0.0f, register.getBalance(), 0.01f);
    }
}
