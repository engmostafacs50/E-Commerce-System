package com.ecommerce.service;

import com.ecommerce.model.user.User;
import java.util.ArrayList;
import java.util.List;

import java.io.*;

public class AuthService implements Serializable {
    private List<User> users = new ArrayList<>();

    public void register(User user) {
        users.add(user);
    }

    public User login(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        return users;
    }

    // Save users to file
    public void saveToFile() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.ser"))) {
            oos.writeObject(users);
        }
    }

    // Load users from file
    @SuppressWarnings("unchecked")
    public void loadFromFile() throws IOException, ClassNotFoundException {
        File file = new File("users.ser");
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            users = (List<User>) ois.readObject();
        }
    }
}