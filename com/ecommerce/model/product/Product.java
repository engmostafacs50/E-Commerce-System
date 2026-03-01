package com.ecommerce.model.product;

import java.util.UUID;

public class Product {
    private final String id ; 
    private String name ;
    private double price ; 
    private int stock ; 

    public Product(String id , String name , double price , int stock)
    {
        this.id = id ; 
        this.name = name ; 
        this.price = price ; 
        this.stock = stock ; 
    }

    public String getId()
    {
        return id ; 
    }
       public String getName()
    {
        return name; 
    }
    public double getPrice() 
    { 
        return price; 
    }

    public int getStock() 
    { 
        return stock; 
    }
    //* */ public void reduceStock(int quantity) // reduce if user buy
    // {
    //     if(quantity > stock)
    //         throw new RuntimeException("Not enough stock") ; 
    //     stock -= quantity ; 
    // }
    // public void increaseStock(int quantity)
    // {
    //     stock+=quantity ; 
    // }

    /*
        if user buy quantity is negative number 
        if ademin quantity is positive number 
    */
    public void adjustStock(int quantity)
    {
        if (stock + quantity < 0)
            throw new IllegalStateException("Not enough stock");

        stock += quantity;
    }
}