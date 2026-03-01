package com.ecommerce.model.user;
import com.ecommerce.model.cart.Cart;

public class Customer extends User{
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
}
