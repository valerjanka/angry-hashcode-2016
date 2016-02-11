package com.angry.hashcode.model;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;

public class Warehouse {

    private int id;

    private int row;

    private int column;

    private final Map<Product, Integer> PRODUCT_AVAILABILITY_MAP = Maps.newTreeMap(Product.PRODUCT_ID_COMPARATOR);

    public Warehouse(int id) {
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

    public int getProductAmount(Product product) {
        Integer amount = PRODUCT_AVAILABILITY_MAP.get(product);
        if (amount == null) {
            amount = 0;
        }
        return amount;
    }

    public void setProductAmount(Product product, int amount) {
        if (amount == 0) {
            PRODUCT_AVAILABILITY_MAP.remove(product);
        } else {
            PRODUCT_AVAILABILITY_MAP.put(product, amount);
        }
    }

    public void moveToDrone(Product product, int amount) {
        int currentAmount = getProductAmount(product);
        assert currentAmount >= amount;
        setProductAmount(product, currentAmount - amount);
    }

    public void moveFromDrone(Product product, int amount) {
        int currentAmount = getProductAmount(product);
        setProductAmount(product, currentAmount + amount);
    }

    public Collection<Product> getAvailableProductIds() {
        return PRODUCT_AVAILABILITY_MAP.keySet();
    }

    @Override
    public String toString() {
        return "Warehouse" + id + "(" + getRow() + "," + getColumn() + ")";
    }
}
