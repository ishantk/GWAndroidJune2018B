package com.auribises.gw2018b.model;

import java.io.Serializable;

public class User implements Serializable{

    public int id;
    public String name;
    public String phone;
    public String email;

    public User(){

    }

    public User(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Id: " + id +
                "\nName: " + name +
                "\nPhone: " + phone +
                "\nEmail: " + email;

    }
}
