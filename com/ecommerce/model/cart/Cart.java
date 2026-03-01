package com.ecommerce.model.cart;
import com.ecommerce.model.product.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable 
{
    private final List<CartItem>items = new ArrayList<>();
    public void addProduct(Product product , int quantity)
    {
        for(CartItem item:items)
        {
            if(item.getProduct().getId().equals(product.getId())) ;
            {

                item.setQuantity(item.getQuantity()+quantity);
            }
        }
        items.add(new CartItem(product, quantity)) ;
    }

     public void removeProduct(Product product) {
        items.removeIf(item->item.getProduct().getId().equals(product.getId())) ; 
    }
    public double calculateTotal()
    {
         double total = 0 ; 
        for(CartItem item : items)
        {
            total += item.getProduct().getPrice() * item.getQuantity() ; 
        }
        return total ; 
    }

    public List<CartItem> getItems() 
    {
        return new ArrayList<>(items);
    }

    public void clear() {
        items.clear();
    }
}