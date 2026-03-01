package com.ecommerce.payment;

public interface PaymentMethod {
boolean pay(double amount) ; 
String getPaymentName();  
}
