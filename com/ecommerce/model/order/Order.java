package com.ecommerce.model.order;

import com.ecommerce.model.cart.CartItem;
import com.ecommerce.model.user.Customer;
import com.ecommerce.payment.PaymentMethod;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order implements Serializable{
    private final String  id ; 
    private final Customer customer ; 
    private final List<CartItem>items ; 
    private double totalPrice ;
    private OrderStatus status ; 
    private final LocalDateTime createdAt; 
    private void calculateTotalPrice()
    {  
        this.totalPrice = 0.0;
        for(CartItem item : items)
        {
            this.totalPrice+=item.getTotalPrice() ; 
        }
    }
    public Order(Customer customer , List<CartItem>cartItems)
    {
        this.id = UUID.randomUUID().toString() ; 
        this.customer = customer ; 
        this.status = OrderStatus.PENDING ; 
        this.items = new ArrayList<>(cartItems) ; // Saving 
        this.createdAt = LocalDateTime.now() ; 
        calculateTotalPrice() ;
        customer.addOrder(this);
    }
    public void confirmOrder()
    {
        if(status != OrderStatus.PENDING)
        {
            throw new IllegalStateException("Order already processed");
        }
        for(CartItem item : items)
        {
            item.getProduct().adjustStock(-item.getQuantity());  // reduce stock
        }
        status = OrderStatus.COMPLETED ; 
    }
    public void cancelOrder()
    {
        if (status != OrderStatus.COMPLETED)
        {
                throw new IllegalStateException("Only completed orders can be cancelled");
        }
        for(CartItem item : items)
        {
            item.getProduct().adjustStock(item.getQuantity());
        }
        status = OrderStatus.CANCELED ; 
    }
    public double getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void processPayment(PaymentMethod paymentMethod)
    {

        if (status != OrderStatus.PENDING)
            throw new IllegalStateException("Order already processed");

        boolean success = paymentMethod.pay(totalPrice);

        if (!success)
            throw new IllegalStateException("Payment failed");

           confirmOrder(); 
    }
}