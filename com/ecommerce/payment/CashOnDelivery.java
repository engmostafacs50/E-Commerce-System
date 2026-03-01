package com.ecommerce.payment;

public class CashOnDelivery implements PaymentMethod {
     @Override
    public boolean pay(double amount) {
        System.out.println("Order will be paid upon delivery.");
        System.out.println("Amount to collect: " + amount);
        return true;
    }
    @Override
    public String getPaymentName() {
        return "Cash On Delivery";
    }
}
