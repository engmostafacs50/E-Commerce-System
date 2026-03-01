# E-Commerce-System

Overview

This project is a console-based E-Commerce System implemented in Java using Object-Oriented Programming (OOP) principles.
It supports both Admin and Customer functionalities, including product management, cart, checkout, order history, and order cancellation.

Features
User Management

Register/Login as Admin or Customer

Persistent storage of users across sessions

Admin Features

Add new products

Restock existing products

View all products

View all orders for all customers

Customer Features

View products

Add products to cart

View cart and calculate total price

Checkout using a payment method (simulated Credit Card Payment)

View Order History

Cancel orders (if eligible) with stock adjustment

Persistence

All data (users, products, stock) is saved to files

Exiting and restarting the program preserves the previous state

Classes & Structure
com.ecommerce
│
├── Main.java           // Main application, handles menus and flow
│
├── model
│   ├── user
│   │   ├── User.java      // Abstract class
│   │   ├── Admin.java
│   │   └── Customer.java
│   ├── product
│   │   └── Product.java
│   ├── cart
│   │   └── CartItem.java
│   └── order
│       ├── Order.java
│       └── OrderStatus.java
│
├── payment
│   ├── PaymentMethod.java // Interface
│   └── CreditCardPayment.java
│
└── service
    ├── AuthService.java      // Handles user registration/login
    └── InventoryManager.java // Manages product list and stock
How to Run

Clone the repository or copy the project into your IDE.

Compile all classes (if using CLI, javac -d bin src/**/*.java)

Run Main.java

java com.ecommerce.Main

Follow the console menu to Register/Login and use Admin or Customer features.

Example Usage
Register & Login
Welcome to E-Commerce System
1- Register
2- Login
3- Exit
Admin Menu
1- Add Product
2- Restock Product
3- Show Products
4- Show All Orders
5- Exit Admin Menu
Customer Menu
1- Show Products
2- Add Product to Cart
3- View Cart
4- Checkout
5- Order History / Cancel Order
6- Exit Customer Menu
Order Cancellation

Customers can cancel orders from Order History if the order is completed.

Stock is automatically updated after cancellation.

Notes

Uses UUID for unique product and order IDs

Uses OOP principles: Abstraction, Inheritance, Polymorphism

No external frameworks; all Java standard library