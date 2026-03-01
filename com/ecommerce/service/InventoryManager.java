package com.ecommerce.service;

import com.ecommerce.model.product.Product;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void restockProduct(String productId, int quantity) {
        for (Product p : products) {
            if (p.getId().equals(productId)) {
                p.adjustStock(quantity);
                return;
            }
        }
        throw new IllegalArgumentException("Product not found");
    }

    public List<Product> getAllProducts() {
        return products;
    }
}