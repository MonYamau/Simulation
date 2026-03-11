package main.java.utils;

import main.java.entity.Entity;
import main.java.map.GameMap;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class MovementUtils {
    private MovementUtils() {
    }

    public static Set<Coordinates> getAvailableCellsForMove(Coordinates current, Class<?> target, GameMap gameMap) {
        Set<Coordinates> availableCells = new HashSet<>();
        for (Coordinates shift : MovementUtils.getShifts()) {
            Coordinates candidateCell = MovementUtils.shiftCoordinates(current, shift);
            if (gameMap.isValidCoordinates(candidateCell) && !MovementUtils.isCellBlockedForMovement(candidateCell, target, gameMap)) {
                availableCells.add(candidateCell);
            }
        }
        return availableCells;
    }

    public static boolean isTarget(Coordinates coordinates, Class<?> target, GameMap gameMap) {
        if (!gameMap.isCellEmpty(coordinates)) {
            Optional<Entity> entity = gameMap.getEntity(coordinates);
            return entity.filter(target::isInstance).isPresent();
        }
        return false;
    }

    private static Set<Coordinates> getShifts() {
        return Set.of(
                new Coordinates(-1, 0), //вверх
                new Coordinates(1, 0), //вниз
                new Coordinates(0, -1), //налево
                new Coordinates(0, 1) //направо
        );
    }

    private static Coordinates shiftCoordinates(Coordinates current, Coordinates shift) {
        return new Coordinates(current.col() + shift.col(), current.row() + shift.row());
    }

    private static boolean isCellBlockedForMovement(Coordinates coordinates, Class<?> target, GameMap gameMap) {
        if (!gameMap.isCellEmpty(coordinates)) {
            Optional<Entity> entity = gameMap.getEntity(coordinates);
            return entity.filter(value -> !target.isInstance(value)).isPresent();
        }
        return false;
    }
}