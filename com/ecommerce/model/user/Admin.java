package com.ecommerce.model.user;

public class Admin extends User{

    public Admin(String name, String email) {
        super(name, email);
    }

    @Override
    public String getRole()
    {
        return "ADMIN" ;
    }
} 