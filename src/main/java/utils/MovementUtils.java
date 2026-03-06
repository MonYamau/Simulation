package main.java.utils;

import java.util.Set;

public class MovementUtils {
    public static Set<CoordinatesShift> getShifts() {
        return Set.of(
                new CoordinatesShift(-1, 0), //вверх
                new CoordinatesShift(1, 0), //вниз
                new CoordinatesShift(0, -1), //налево
                new CoordinatesShift(0, 1) //направо
        );
    }

    public static Coordinates move(Coordinates coordinates, CoordinatesShift coordinatesShift) {
        return new Coordinates(coordinates.col() + coordinatesShift.shiftCol(), coordinates.row() + coordinatesShift.shiftRow());
    }
}
