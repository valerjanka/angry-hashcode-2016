package com.angry.hashcode.model.command;

import com.angry.hashcode.io.input.InputDataSet;
import com.angry.hashcode.model.Drone;
import com.angry.hashcode.model.Product;
import com.angry.hashcode.model.Warehouse;
import com.google.common.base.Joiner;

public class UnloadCommand extends Command {

    private static final Joiner JOINER = Joiner.on(" ");

    private static final String COMMAND_TYPE = "U";

    private Drone drone;

    private Warehouse warehouse;

    private Product product;

    private int amount;

    public UnloadCommand(Drone drone, Warehouse warehouse, Product product, int amount) {
        this.drone = drone;
        this.warehouse = warehouse;
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
        return JOINER.join(drone.getId(), COMMAND_TYPE, warehouse.getId(), product.getId(), amount);
    }
}
