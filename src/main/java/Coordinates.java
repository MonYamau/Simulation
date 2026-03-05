package main.java;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public record Coordinates(int col, int row) {
    public Coordinates shift(CoordinatesShift coordinatesShift) {
        return new Coordinates(this.col + coordinatesShift.shiftCol(), this.row + coordinatesShift.shiftRow());
    }

    public Set<CoordinatesShift> getMoves() {
        return new HashSet<>(Arrays.asList(
                new CoordinatesShift(-1, 0), //вверх
                new CoordinatesShift(1, 0), //вниз
                new CoordinatesShift(0, -1), //налево
                new CoordinatesShift(0, 1) //направо
        ));
    }
}