package com.angry.hashcode.model.command;

import com.angry.hashcode.io.input.InputDataSet;
import com.angry.hashcode.model.Drone;

public abstract class Command {

    /**
     * Returns a new state after executing command.
     */
    public abstract InputDataSet execute(InputDataSet currentState);

    public abstract String stringRepr();

    public abstract boolean hasNextTurn();

    public abstract void onNextTurn();

    public abstract Drone getDrone();
}
