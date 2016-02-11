package com.angry.hashcode.model.command;

import com.angry.hashcode.DistanceHelper;
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

    private int numTurnsLeft;

    public DeliverCommand(Drone drone, Order order, Product product, int amount) {
        this.drone = drone;
        this.order = order;
        this.product = product;
        this.amount = amount;

        this.numTurnsLeft = DistanceHelper.getTurns(drone.getRow(), drone.getColumn(), order.getRow(),
                order.getColumn()) + 1;
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

    public int numTurnsLeft() {
        return numTurnsLeft;
    }

    @Override
    public boolean hasNextTurn() {
        return numTurnsLeft > 0;
    }

    @Override
    public void onNextTurn() {
        assert hasNextTurn();
        numTurnsLeft--;
        if (numTurnsLeft == 0) {
            order.deliverProduct(product, amount);
            drone.removeProduct(product, amount);
        }
    }

    @Override
    public Drone getDrone() {
        return drone;
    }
}
