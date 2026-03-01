package com.ecommerce.service;

import com.ecommerce.model.user.User;
import java.util.ArrayList;
import java.util.List;

public class AuthService {

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
        throw new IllegalArgumentException("User not found");
    }

    public List<User> getAllUsers() {
        return users;
    }
}