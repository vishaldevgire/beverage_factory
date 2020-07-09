package com.example.beveragefactory.model;

public class Ingredient {
    public Ingredient(String name, double price) {
        this.name = name.toLowerCase();
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    private String name;
    private double price;
}
