package com.angry.hashcode.model.command;

import com.angry.hashcode.DistanceHelper;
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

    private int numTurnsLeft;

    public UnloadCommand(Drone drone, Warehouse warehouse, Product product, int amount) {
        this.drone = drone;
        this.warehouse = warehouse;
        this.product = product;
        this.amount = amount;

        this.numTurnsLeft = DistanceHelper.getTurns(drone.getRow(), drone.getColumn(), warehouse.getRow(),
                warehouse.getColumn()) + 1;
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

    @Override
    public boolean hasNextTurn() {
        return numTurnsLeft > 0;
    }

    @Override
    public void onNextTurn() {
        assert hasNextTurn();
        numTurnsLeft--;
        if (numTurnsLeft == 0) {
            drone.removeProduct(product, amount);
            warehouse.moveFromDrone(product, amount);
        }
    }

    @Override
    public Drone getDrone() {
        return drone;
    }
}
