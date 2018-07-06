package com.auribises.gw2018b;

public class Book {

    public String price;
    public String name;
    public String author;

    public Book(){

    }

    public Book(String price, String name, String author) {
        this.price = price;
        this.name = name;
        this.author = author;
    }

    @Override
    public String toString() {
        return  "Price: " + price +
                "\nName: " + name +
                "\nAuthor: " + author;
    }
}
