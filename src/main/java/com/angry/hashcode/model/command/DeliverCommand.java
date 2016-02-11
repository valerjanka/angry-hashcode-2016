package com.angry.hashcode.model.command;

import com.angry.hashcode.io.input.InputDataSet;
import com.angry.hashcode.model.Drone;
import com.angry.hashcode.model.Order;
import com.angry.hashcode.model.Product;
import com.angry.hashcode.model.Warehouse;
import com.google.common.base.Joiner;

public class DeliverCommand extends Command {

    private static final Joiner JOINER = Joiner.on(" ");

    private static final String COMMAND_TYPE = "D";

    private Drone drone;

    private Order order;

    private Product product;

    private int amount;

    public DeliverCommand(Drone drone, Order order, Product product, int amount) {
        this.drone = drone;
        this.order = order;
        this.product = product;
        this.amount = amount;

    }

    @Override
    public InputDataSet execute(InputDataSet currentState) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String stringRepr() {
        return JOINER.join(drone.getId(), COMMAND_TYPE, order.getId(), product.getId(), amount);
    }
}
