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
        if (currentAmount == 0) {
            ORDERED_PRODUCT_AMOUNT_MAP.remove(product);
        } else {
            ORDERED_PRODUCT_AMOUNT_MAP.put(product, currentAmount);
        }
        numProductsOrdered += amount;
    }

    public void deliverProduct(Product product, int amount) {
        addProductToOrder(product, -amount);
    }

    public boolean isFulfilled() {
        return numProductsOrdered == 0;
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

    public int getOfferWeight(){
        int orderWeight = 0;

        for (Product product : getOrderedProductIds()) {
            int amount = getOrderedProductAmount(product);

            orderWeight += product.getWeight() * amount;
        }

        return orderWeight;
    }

    @Override
    public String toString() {
        return "Order" + id + "(" + getRow() + "," + getColumn() + ")";
    }
}
