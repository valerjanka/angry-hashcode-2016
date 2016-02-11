package com.angry.hashcode.model;

public class Drone {

    private int id;

    private int row;

    private int column;

    public Drone(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
