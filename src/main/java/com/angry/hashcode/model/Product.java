package com.angry.hashcode.model;

import java.util.Comparator;

public class Product {

    public static final Comparator<Product> PRODUCT_ID_COMPARATOR = new Comparator<Product>() {

        @Override
        public int compare(Product p0, Product p1) {
            return p0.getId() - p1.getId();
        }

    };

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

    @Override
    public String toString() {
        return "Product" + getId();
    }
}
