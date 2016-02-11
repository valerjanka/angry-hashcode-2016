package com.angry.hashcode;

public class DistanceHelper {

    public static int getTurns(int row1, int column1, int row2, int column2) {
        double distance = Math.sqrt(sqr(row1 - row2) + sqr(column1 - column2));
        return (int) Math.ceil(distance);
    }

    private static int sqr(int x) {
        return x * x;
    }
}
