package com.angry.hashcode.model.command;

import com.angry.hashcode.io.input.InputDataSet;

public abstract class Command {

    /**
     * Returns a new state after executing command.
     */
    public abstract InputDataSet execute(InputDataSet currentState);

    public abstract String stringRepr();
}
