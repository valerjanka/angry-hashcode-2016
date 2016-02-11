package com.angry.hashcode.model;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;

public class Drone {

    private int id;

    private int row;

    private int column;

    private final Map<Product, Integer> PRODUCT_CARRIED_MAP = Maps.newTreeMap(Product.PRODUCT_ID_COMPARATOR);

    public Drone(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getProductCarriedAmount(Product product) {
        Integer amount = PRODUCT_CARRIED_MAP.get(product);
        if (amount == null) {
            amount = 0;
        }
        return amount;
    }

    public Collection<Product> getProductsCarried() {
        return PRODUCT_CARRIED_MAP.keySet();
    }

    public void setProductCarriedAmount(Product product, int amount) {
        if (amount == 0) {
            PRODUCT_CARRIED_MAP.remove(product);
        } else {
            PRODUCT_CARRIED_MAP.put(product, amount);
        }
    }

    public void addProduct(Product product, int amount) {
        int currentAmount = getProductCarriedAmount(product);
        int newAmount = currentAmount + amount;
        setProductCarriedAmount(product, newAmount);
    }

    public int getLoad() {
        int load = 0;
        for (int amount : PRODUCT_CARRIED_MAP.values()) {
            load += amount;
        }
        return load;
    }
}
