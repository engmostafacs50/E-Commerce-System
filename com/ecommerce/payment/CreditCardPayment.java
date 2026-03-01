package com.ecommerce.payment;

public class CreditCardPayment implements PaymentMethod{
    private String cardNumber;
    
    public CreditCardPayment(String cardNumber){
        this.cardNumber = cardNumber ; 
    }
      @Override
    public boolean pay(double amount) {
        System.out.println("Processing Credit Card Payment...");
        System.out.println("Paid " + amount + " using Card: " + cardNumber);
        return true;
    }

    @Override
    public String getPaymentName() {
        return "Credit Card";
    }
}
