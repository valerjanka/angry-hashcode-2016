package com.angry.hashcode.model;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;

public class Order {

    private int id;

    private int row;

    private int column;

    private int numProductsOrdered;

    private final Map<Product, Integer> ORDERED_PRODUCT_AMOUNT_MAP = Maps.newTreeMap(Product.PRODUCT_ID_COMPARATOR);

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

    public void addProductToOrder(Product product) {
        addProductToOrder(product, 1);
    }

    public void addProductToOrder(Product product, int amount) {
        Integer currentAmount = ORDERED_PRODUCT_AMOUNT_MAP.get(product);
        if (currentAmount == null) {
            currentAmount = 0;
        }
        currentAmount += amount;
        ORDERED_PRODUCT_AMOUNT_MAP.put(product, currentAmount);
        numProductsOrdered += amount;
    }

    public Collection<Product> getOrderedProductIds() {
        return ORDERED_PRODUCT_AMOUNT_MAP.keySet();
    }

    public int getOrderedProductAmount(Product product) {
        Integer amount = ORDERED_PRODUCT_AMOUNT_MAP.get(product);
        if (amount == null) {
            amount = 0;
        }
        return amount;
    }

    public int getNumProductsOrdered() {
        return numProductsOrdered;
    }
}
