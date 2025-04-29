package model;

public class CashRegister {
    private float  balance;

    public void addMoney(float amountPaid) {
        balance += amountPaid;
    }
}
