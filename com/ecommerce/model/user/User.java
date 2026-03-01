package com.ecommerce.model.user;
import java.util.UUID;

public abstract class User {
    private final String id ; 
    private String name ; 
    private String email ; 

    public User(String name , String email)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name ; 
        this.email = email ; 
    }
    public String getId() 
    { 
        return id; 
    }

    public String getName() 
    { 
        return name;
    }

    public String getEmail() 
    { 
        return email; 
    }

    public void setName(String name)
    {
        if(name == null || name.isBlank())
            throw new IllegalArgumentException("name isn't valid") ;
        this.name = name ; 
    }
       public void setEmail(String email)
    {
        if(email == null || email.isBlank())
            throw new IllegalArgumentException("email isn't valid") ;
        this.email = email ; 
    }
    public abstract String getRole();
}