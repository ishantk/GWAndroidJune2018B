package com.auribises.gw2018b;

import java.io.Serializable;

public class Uuser implements Serializable{

    public String name;
    public String email;

    public Uuser(){

    }

    public Uuser(String name, String email) {
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
