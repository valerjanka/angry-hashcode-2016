package com.angry.hashcode.model;

public class Product {

    private int id;

    private int weight;

    public Product(int id, int weight) {
        this.id = id;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }
}
