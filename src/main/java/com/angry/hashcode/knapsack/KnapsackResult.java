package com.angry.hashcode.knapsack;

import com.angry.hashcode.model.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by valerii.ryzhuk on 2/11/2016.
 */
public class KnapsackResult {
    private Map<Product, Integer> productToAmount = new HashMap<>();

    public void add(Product product, int amount) {
        Integer cur = productToAmount.get(product);
        if (cur != null) {
            productToAmount.put(product, cur + amount);
        } else {
            productToAmount.put(product, amount);
        }
    }

    public Map<Product, Integer> getProductToAmount() {
        return productToAmount;
    }
}
