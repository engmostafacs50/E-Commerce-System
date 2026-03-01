package com.ecommerce.model.user;

import java.io.Serializable;

public class Admin extends User implements Serializable {

    public Admin(String name, String email) {
        super(name, email);
    }

    @Override
    public String getRole()
    {
        return "ADMIN" ;
    }
} 