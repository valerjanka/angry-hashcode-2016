package com.angry.hashcode.io.input;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import com.angry.hashcode.io.FastReader;
import com.angry.hashcode.model.Order;
import com.angry.hashcode.model.Product;
import com.angry.hashcode.model.Warehouse;
import com.google.common.collect.Maps;

public class InputDataSet {

    private final Map<Integer, Product> PRODUCT_MAP = Maps.newHashMap();

    private final Map<Integer, Warehouse> WAREHOUSE_MAP = Maps.newHashMap();

    private final Map<Integer, Order> ORDER_MAP = Maps.newHashMap();

    private int numRows;

    private int numColumns;

    private int numDrones;

    private int deadline;

    private int maxLoad;

    private int numProducts;

    private int numWarehouses;

    private int numOrders;

    public InputDataSet(String fileName) throws FileNotFoundException, IOException {
        FastReader fr = new FastReader(fileName);

        setNumRows(fr.nextInt());
        setNumColumns(fr.nextInt());
        setNumDrones(fr.nextInt());
        setDeadline(fr.nextInt());
        setMaxLoad(fr.nextInt());

        setNumProducts(fr.nextInt());
        for (int i = 0; i < getNumProducts(); i++) {
            int weight = fr.nextInt();
            PRODUCT_MAP.put(i, new Product(i, weight));
        }

        setNumWarehouses(fr.nextInt());
        for (int warehouseId = 0; warehouseId < getNumWarehouses(); warehouseId++) {
            Warehouse warehouse = new Warehouse(warehouseId);
            warehouse.setRow(fr.nextInt());
            warehouse.setColumn(fr.nextInt());
            for (int productId = 0; productId < getNumProducts(); productId++) {
                int amount = fr.nextInt();
                warehouse.setProductAmount(productId, amount);
            }
            WAREHOUSE_MAP.put(warehouseId, warehouse);
        }

        setNumOrders(fr.nextInt());
        for (int orderId = 0; orderId < getNumOrders(); orderId++) {
            Order order = new Order(orderId);
            order.setRow(fr.nextInt());
            order.setColumn(fr.nextInt());
            int numProductsOrdered = fr.nextInt();
            for (int j = 0; j < numProductsOrdered; j++) {
                int orderedProductId = fr.nextInt();
                order.addProductToOrder(orderedProductId);
            }
            ORDER_MAP.put(orderId, order);
        }
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
    }

    public int getNumDrones() {
        return numDrones;
    }

    public void setNumDrones(int numDrones) {
        this.numDrones = numDrones;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(int maxLoad) {
        this.maxLoad = maxLoad;
    }

    public int getNumProducts() {
        return numProducts;
    }

    public void setNumProducts(int numProducts) {
        this.numProducts = numProducts;
    }

    public Product getProduct(int id) {
        return PRODUCT_MAP.get(id);
    }

    public int getNumWarehouses() {
        return numWarehouses;
    }

    public void setNumWarehouses(int numWarehouses) {
        this.numWarehouses = numWarehouses;
    }

    public Warehouse getWarehouse(int id) {
        return WAREHOUSE_MAP.get(id);
    }

    public int getNumOrders() {
        return numOrders;
    }

    public void setNumOrders(int numOrders) {
        this.numOrders = numOrders;
    }

    public Order getOrder(int id) {
        return ORDER_MAP.get(id);
    }
}
