package com.angry.hashcode.simulation;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.angry.hashcode.io.input.InputDataSet;
import com.angry.hashcode.model.Drone;
import com.angry.hashcode.model.command.Command;
import com.angry.hashcode.model.command.UnloadCommand;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class Simulation {

    private InputDataSet initialState;

    private List<Command> commands;

    private Map<Drone, LinkedList<Command>> commandsPerDroneMap;

    private int numTurnsLeft;

    public Simulation(InputDataSet initialState, List<Command> commands) {
        this.initialState = initialState;
        this.commands = commands;
        this.numTurnsLeft = initialState.getDeadline();

        populateCommandsPerDroneMap();
    }

    private void populateCommandsPerDroneMap() {
        commandsPerDroneMap = Maps.newHashMap();
        for (Command command : commands) {
            LinkedList<Command> commandsPerDrone = commandsPerDroneMap.get(command.getDrone());
            if (commandsPerDrone == null) {
                commandsPerDrone = Lists.newLinkedList();
                commandsPerDroneMap.put(command.getDrone(), commandsPerDrone);
            }
            commandsPerDrone.add(command);
        }
    }

    public boolean hasNextTurn() {
        return numTurnsLeft > 0;
    }

    public void nextTurn() {
        numTurnsLeft--;
        Set<Command> currentCommands = Sets.newHashSet();
        for (int droneId = 0; droneId < initialState.getNumDrones(); droneId++) {
            Drone drone = initialState.getDrone(droneId);
            LinkedList<Command> commandsPerDrone = commandsPerDroneMap.get(drone);
            if (commandsPerDrone == null || commandsPerDrone.isEmpty()) {
                continue;
            }
            Command command = commandsPerDrone.peekFirst();
            if (!command.hasNextTurn()) {
                commandsPerDrone.removeFirst();
                command = commandsPerDrone.peekFirst();
            }
            if (command == null) {
                continue;
            }
            currentCommands.add(command);
        }

        // process unload commands first
        for (Command command : currentCommands) {
            if (!(command instanceof UnloadCommand)) {
                continue;
            }
            command.onNextTurn();
        }

        for (Command command : currentCommands) {
            if (command instanceof UnloadCommand) {
                continue;
            }
            command.onNextTurn();
        }
    }
}
