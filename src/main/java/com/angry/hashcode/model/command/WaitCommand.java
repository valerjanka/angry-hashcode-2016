package com.angry.hashcode.model.command;

import com.angry.hashcode.io.input.InputDataSet;
import com.angry.hashcode.model.Drone;
import com.google.common.base.Joiner;

public class WaitCommand extends Command {

    private static final Joiner JOINER = Joiner.on(" ");

    private static final String COMMAND_TYPE = "W";

    private Drone drone;

    private int waitDuration;

    private int waitDurationLeft;

    public WaitCommand(Drone drone, int waitDuration) {
        this.drone = drone;
        this.waitDuration = waitDuration;
        this.waitDurationLeft = waitDuration;
    }

    @Override
    public InputDataSet execute(InputDataSet currentState) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String stringRepr() {
        return JOINER.join(drone.getId(), COMMAND_TYPE, waitDuration);
    }

    public int getNumTurnsLeft() {
        return waitDurationLeft;
    }

    @Override
    public boolean hasNextTurn() {
        return waitDurationLeft > 0;
    }

    @Override
    public void onNextTurn() {
        assert hasNextTurn();
        waitDurationLeft--;
    }

    @Override
    public Drone getDrone() {
        return drone;
    }
}
