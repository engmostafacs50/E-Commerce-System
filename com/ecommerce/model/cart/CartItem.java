package com.ecommerce.model.cart;

import java.io.Serializable;

import com.ecommerce.model.product.Product;

public class CartItem implements Serializable {

    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        if(quantity <= 0)
            throw new IllegalArgumentException("Quantity must be positive");

        this.product = product;
        this.quantity = quantity;
    }
2


    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity <= 0)
            throw new IllegalArgumentException("Quantity must be positive");
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}