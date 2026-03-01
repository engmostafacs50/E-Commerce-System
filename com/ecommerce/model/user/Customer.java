package com.ecommerce.model.user;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.model.cart.Cart;
import com.ecommerce.model.order.Order;
import java.io.Serializable;
public class Customer extends User implements Serializable {
    private final  Cart cart ; 
    public Customer(String name , String email)
    {
        super( name, email) ;
        this.cart = new Cart(); 
    }
    @Override
    public String getRole()
    {
        return "CUSTOMER" ;
    }

    public Cart getCart() 
    {
        return cart;
    }
    private List<Order> orderHistory = new ArrayList<>();

    public void addOrder(Order order) {
     orderHistory.add(order);
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }
}
