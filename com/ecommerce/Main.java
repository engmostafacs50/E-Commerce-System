package com.ecommerce;

import com.ecommerce.model.user.User;
import com.ecommerce.model.user.Admin;
import com.ecommerce.model.user.Customer;
import com.ecommerce.model.product.Product;
import com.ecommerce.model.cart.CartItem;
import com.ecommerce.model.order.Order;
import com.ecommerce.payment.PaymentMethod;
import com.ecommerce.payment.CreditCardPayment;
import com.ecommerce.service.AuthService;
import com.ecommerce.service.InventoryManager;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AuthService authService = new AuthService();
        InventoryManager inventoryManager = new InventoryManager();

        // =========================
        // Load previous state
        // =========================
        try {
            authService.loadFromFile();
            inventoryManager.loadFromFile();
        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
        }

        while (true) {
            System.out.println("\n=== Welcome to E-Commerce System ===");
            System.out.println("Choose an option:");
            System.out.println("1- Register");
            System.out.println("2- Login");
            System.out.println("3- Exit Program");

            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 3) {
                saveData(authService, inventoryManager);
                System.out.println("Exiting program. Data saved.");
                break;
            }

            User loggedUser = null;

            // =========================
            // Register
            // =========================
            if (option == 1) {
                System.out.println("Enter your name:");
                String name = scanner.nextLine();

                System.out.println("Enter your email:");
                String email = scanner.nextLine();

                System.out.println("Are you Admin? (yes/no):");
                String role = scanner.nextLine();

                if (role.equalsIgnoreCase("yes")) {
                    loggedUser = new Admin(name, email);
                } else {
                    loggedUser = new Customer(name, email);
                }

                authService.register(loggedUser);
                System.out.println("Registration successful! Please login now.");
                continue;
            }

            // =========================
            // Login
            // =========================
            if (option == 2) {
                System.out.println("Enter your email to login:");
                String email = scanner.nextLine();
                loggedUser = authService.login(email);

                if (loggedUser == null) {
                    System.out.println("User not found! Try Registering first.");
                    continue;
                }
            }

            System.out.println("Logged in as: " + loggedUser.getName() + " | Role: " + loggedUser.getRole());

            // =========================
            // Show Menu based on Role
            // =========================
            if (loggedUser.getRole().equals("ADMIN")) {
                showAdminMenu(scanner, inventoryManager);
            } else {
                showCustomerMenu(scanner, (Customer) loggedUser, inventoryManager);
            }

            // Save data after each session
            saveData(authService, inventoryManager);
        }
    }

    // =========================
    // Save Data Helper
    // =========================
    private static void saveData(AuthService authService, InventoryManager inventoryManager) {
        try {
            authService.saveToFile();
            inventoryManager.saveToFile();
        } catch (Exception e) {
            System.out.println("Failed to save data!");
        }
    }

    // =========================
    // Admin Menu
    // =========================
    public static void showAdminMenu(Scanner scanner, InventoryManager inventoryManager) {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1- Add Product");
            System.out.println("2- Restock Product");
            System.out.println("3- Show Products");
            System.out.println("4- Exit Admin Menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter product name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter price:");
                    double price = scanner.nextDouble();
                    System.out.println("Enter stock:");
                    int stock = scanner.nextInt();
                    scanner.nextLine();

                    Product product = new Product(UUID.randomUUID().toString(), name, price, stock);
                    inventoryManager.addProduct(product);
                    System.out.println("Product added successfully!");
                }
                case 2 -> {
                    System.out.println("Enter product ID to restock:");
                    String id = scanner.nextLine();
                    System.out.println("Enter quantity to add:");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    inventoryManager.restockProduct(id, quantity);
                    System.out.println("Product restocked successfully!");
                }
                case 3 -> {
                    System.out.println("=== Products ===");
                    for (Product p : inventoryManager.getAllProducts()) {
                        System.out.println(p.getId() + " | " + p.getName() + " | Price: " + p.getPrice() + " | Stock: " + p.getStock());
                    }
                }
                case 4 -> { // Show All Orders
                            System.out.println("=== All Orders ===");
                            AuthService authService = null;
                            List<User> allUsers = authService.getAllUsers(); 
                            for (User u : allUsers) {
                                if (u instanceof Customer customer) {
                                    List<Order> orders = customer.getOrderHistory();
                                    if (!orders.isEmpty()) {
                                        System.out.println("Customer: " + customer.getName());
                                        for (Order o : orders) {
                                            System.out.println("  Order ID: " + o.getId() + " | Status: " + o.getStatus() + " | Total: " + o.getTotalPrice());
                                        }
                                    }
                                }
                            }
                        }
                case 5 -> {
                    System.out.println("Exiting Admin Menu.");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // =========================
    // Customer Menu
    // =========================
    public static void showCustomerMenu(Scanner scanner, Customer customer, InventoryManager inventoryManager) {
        while (true) {
            System.out.println("\n=== Customer Menu ===");
            System.out.println("1- Show Products");
            System.out.println("2- Add Product to Cart");
            System.out.println("3- View Cart");
            System.out.println("4- Checkout");
            System.out.println("5- Order History");
            System.out.println("6- Exit Customer Menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("=== Products ===");
                    for (Product p : inventoryManager.getAllProducts()) {
                        System.out.println(p.getId() + " | " + p.getName() + " | Price: " + p.getPrice() + " | Stock: " + p.getStock());
                    }
                }
                case 2 -> {
                    System.out.println("Enter product ID:");
                    String id = scanner.nextLine();
                    Product selected = null;
                    for (Product p : inventoryManager.getAllProducts()) {
                        if (p.getId().equals(id)) {
                            selected = p;
                            break;
                        }
                    }
                    if (selected == null) {
                        System.out.println("Product not found!");
                        break;
                    }
                    System.out.println("Enter quantity:");
                    int qty = scanner.nextInt();
                    scanner.nextLine();
                    customer.getCart().addProduct(selected, qty);
                    System.out.println("Product added to cart!");
                }
                case 3 -> {
                    System.out.println("=== Cart ===");
                    List<CartItem> items = customer.getCart().getItems();
                    for (CartItem item : items) {
                        System.out.println(item.getProduct().getName() + " x " + item.getQuantity() + " = " + item.getTotalPrice());
                    }
                    System.out.println("Cart Total: " + customer.getCart().calculateTotal());
                }
                case 4 -> {
                    if (customer.getCart().getItems().isEmpty()) {
                        System.out.println("Cart is empty!");
                        break;
                    }
                    Order order = new Order(customer, customer.getCart().getItems());
                    PaymentMethod payment = new CreditCardPayment("1234-5678-9999");
                    order.processPayment(payment);
                    customer.getCart().clear();
                    System.out.println("Checkout complete! Order Status: " + order.getStatus());
                }
                case 5 -> { // Order History
                    System.out.println("=== Order History ===");
                    List<Order> orders = customer.getOrderHistory();
                    if (orders.isEmpty()) {
                    System.out.println("No orders yet!");
                    break;
                }
                    for (int i = 0; i < orders.size(); i++) {
                     Order o = orders.get(i);
                    System.out.println((i + 1) + "- Order ID: " + o.getId() + " | Status: " + o.getStatus() + " | Total: " + o.getTotalPrice());
                    }

                    System.out.println("Do you want to cancel an order? (yes/no)");
                    String cancel = scanner.nextLine();
                    if (cancel.equalsIgnoreCase("yes")) {
                        System.out.println("Enter order number to cancel:");
                        int orderIndex = scanner.nextInt() - 1;
                        scanner.nextLine();
                    if (orderIndex < 0 || orderIndex >= orders.size()) {
                        System.out.println("Invalid order number!");
                         break;
            }

        Order toCancel = orders.get(orderIndex);
        try {
            toCancel.cancelOrder();
            System.out.println("Order canceled successfully!");
        } catch (IllegalStateException e) {
            System.out.println("Cannot cancel this order: " + e.getMessage());
        }
    }
}
                case 6 -> {
                    System.out.println("Exiting Customer Menu.");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

}