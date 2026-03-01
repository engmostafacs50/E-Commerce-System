package com.ecommerce;

import com.ecommerce.model.product.Product;
import com.ecommerce.model.user.Customer;
import com.ecommerce.model.cart.CartItem;
import com.ecommerce.model.order.Order;
import com.ecommerce.payment.PaymentMethod;
import com.ecommerce.payment.CreditCardPayment;

public class Main {

    public static void main(String[] args) {

        // ===============================
        // Create Products
        // ===============================
        Product laptop = new Product("1", "Laptop", 1500, 10);
        Product mouse = new Product("2", "Mouse", 50, 100);
        Product keyboard = new Product("3", "Keyboard", 100, 50);

        System.out.println("=== Initial Stock ===");
        System.out.println(laptop.getName() + ": " + laptop.getStock());
        System.out.println(mouse.getName() + ": " + mouse.getStock());
        System.out.println(keyboard.getName() + ": " + keyboard.getStock());
        System.out.println();


        // ===============================
        // Create Customer
        // ===============================
        Customer customer = new Customer("Mostafa Ahmed", "mostafa@mail.com");

        System.out.println("Customer: " + customer.getName());
        System.out.println();


        // ===============================
        //  Add Products To Cart
        // ===============================
        customer.getCart().addProduct(laptop, 2);
        customer.getCart().addProduct(mouse, 3);
        customer.getCart().addProduct(keyboard, 1);

        System.out.println("=== Cart Items ===");
        for (CartItem item : customer.getCart().getItems()) {
            System.out.println(
                    item.getProduct().getName() +
                            " x " + item.getQuantity() +
                            " = " + item.getTotalPrice()
            );
        }

        double cartTotal = customer.getCart().calculateTotal();
        System.out.println("Cart Total: " + cartTotal);
        System.out.println();


        // ===============================
        // Create Order From Cart
        // ===============================
        Order order = new Order(customer, customer.getCart().getItems());

        System.out.println("Order Created.");
        System.out.println("Order Status: " + order.getStatus());
        System.out.println();


        // ===============================
        //  Process Payment (Auto Confirm)
        // ===============================
        PaymentMethod payment = new CreditCardPayment("1234-5678-9999");

        try {
            order.processPayment(payment);

            System.out.println("Payment Successful!");
            System.out.println("Order Status: " + order.getStatus());

        } catch (Exception e) {
            System.out.println("Payment Failed: " + e.getMessage());
        }

        System.out.println();


        // ===============================
        // Show Stock After Payment
        // ===============================
        System.out.println("=== Stock After Checkout ===");
        System.out.println(laptop.getName() + ": " + laptop.getStock());
        System.out.println(mouse.getName() + ": " + mouse.getStock());
        System.out.println(keyboard.getName() + ": " + keyboard.getStock());
        System.out.println();


        // ===============================
        // Clear Cart
        // ===============================
        customer.getCart().clear();
        System.out.println("Cart Cleared. Items Count: "
                + customer.getCart().getItems().size());
    }
}