package main.java.se.kth.iv1350.pos.model;

public class CashRegister {
    private float balance;

    public void addMoney(float amountPaid) {
        balance += amountPaid;
    }

    public float getBalance() {
        return balance;
    }
}
