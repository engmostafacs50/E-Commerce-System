package com.ecommerce.service;

import com.ecommerce.model.product.Product;
import java.util.ArrayList;
import java.util.List;

import java.io.*;


public class InventoryManager implements Serializable {

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

    public void saveToFile() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("inventory.ser"))) {
            oos.writeObject(products);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile() throws IOException, ClassNotFoundException {
        File file = new File("inventory.ser");
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            products = (List<Product>) ois.readObject();
        }
    }
}