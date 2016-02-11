package com.angry.hashcode.knapsack;

import com.angry.hashcode.model.Order;
import com.angry.hashcode.model.Product;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by valerii.ryzhuk on 2/11/2016.
 */
public class KnapsackSolver {
    private Order order;
    private int load;

    public KnapsackSolver(Order order, int load) {
        this.order = order;
        this.load = load;
    }

    public KnapsackResult getNext() {
        int[] m = new int[load + 1];
        Collection<Product> products = order.getOrderedProductIds();
        KnapsackResult[] result = new KnapsackResult[load + 1];

        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            for (int j = 1; j <= load; j++) {
                int w = product.getWeight();
                int itemAmount = order.getOrderedProductAmount(product);
                int quantity = Math.min(itemAmount, j / w);
                for (int k = 1; k < quantity; k++) {
                    if (j >= w) {
                        if (m[j] < m[j - w * k] + k * w) {
                            m[j] = Math.max(m[j], m[j - w * k] + k * w);
                            if (result[j] == null) {
                                KnapsackResult x = new KnapsackResult();
                                x.add(product, k);
                                result[j] = x;
                            } else {
                                result[j].add(product, k);
                            }
                        }
                    }
                }
            }
        }
        return result[load];
    }
}
