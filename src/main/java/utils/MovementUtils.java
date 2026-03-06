package main.java.utils;

import main.java.entity.Entity;
import main.java.map.GameMap;

import java.util.Set;

public final class MovementUtils {
    private MovementUtils(){}

    public static Set<CoordinatesShift> getShifts() {
        return Set.of(
                new CoordinatesShift(-1, 0), //вверх
                new CoordinatesShift(1, 0), //вниз
                new CoordinatesShift(0, -1), //налево
                new CoordinatesShift(0, 1) //направо
        );
    }

    public static Coordinates moveCoordinates(Coordinates coordinates, CoordinatesShift coordinatesShift) {
        return new Coordinates(coordinates.col() + coordinatesShift.shiftCol(), coordinates.row() + coordinatesShift.shiftRow());
    }

    public static void moveEntity(Coordinates from, Coordinates to, GameMap gameMap) {
        Entity entity = gameMap.getEntity(from);
        gameMap.removeEntity(from);
        gameMap.putEntity(to, entity);
    }

    public static boolean isCellOccupied(Coordinates coordinates, String food, GameMap gameMap) {
        if (!gameMap.isCellEmpty(coordinates)) {
            Entity entity = gameMap.getEntity(coordinates);
            return !entity.getClass().getSimpleName().equals(food);
        }
        return false;
    }
}
