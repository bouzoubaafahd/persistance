package fr.univcotedazur.polyevent.components.externaldto;

public class PaymentDto {


    private String creditCard;

    private double amount;

    public PaymentDto(String creditCard, double amount) {
        this.creditCard = creditCard;
        this.amount = amount;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
