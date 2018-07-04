package com.auribises.gw2018b;

import java.io.Serializable;

public class User implements Serializable{

    public String name;
    public String email;

    public User(){

    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
