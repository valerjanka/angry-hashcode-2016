package com.angry.hashcode.model;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;

public class Warehouse {

    private int id;

    private int row;

    private int column;

    private final Map<Integer, Integer> PRODUCT_AVAILABILITY_MAP = Maps.newTreeMap();

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

    public int getProductAmount(int productId) {
        Integer amount = PRODUCT_AVAILABILITY_MAP.get(productId);
        if (amount == null) {
            amount = 0;
        }
        return amount;
    }

    public void setProductAmount(int productId, int amount) {
        if (amount == 0) {
            PRODUCT_AVAILABILITY_MAP.remove(productId);
        } else {
            PRODUCT_AVAILABILITY_MAP.put(productId, amount);
        }
    }

    public Collection<Integer> getAvailableProductIds() {
        return PRODUCT_AVAILABILITY_MAP.keySet();
    }
}
