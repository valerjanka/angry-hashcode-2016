package com.angry.hashcode.model;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;

public class Order {

    private int id;

    private int row;

    private int column;

    private int numProductsOrdered;

    private final Map<Integer, Integer> ORDERED_PRODUCT_AMOUNT_MAP = Maps.newTreeMap();

    public Order(int id) {
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

    public void addProductToOrder(int productId) {
        Integer currentAmount = ORDERED_PRODUCT_AMOUNT_MAP.get(productId);
        if (currentAmount == null) {
            currentAmount = 0;
        }
        currentAmount++;
        ORDERED_PRODUCT_AMOUNT_MAP.put(productId, currentAmount);
        numProductsOrdered++;
    }

    public Collection<Integer> getOrderedProductIds() {
        return ORDERED_PRODUCT_AMOUNT_MAP.keySet();
    }

    public int getOrderedProductAmount(int productId) {
        Integer amount = ORDERED_PRODUCT_AMOUNT_MAP.get(productId);
        if (amount == null) {
            amount = 0;
        }
        return amount;
    }

    public int getNumProductsOrdered() {
        return numProductsOrdered;
    }
}
