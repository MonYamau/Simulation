package main.java.utils;

import main.java.entity.Entity;
import main.java.gamemap.GameMap;
import main.java.gamemap.Coordinates;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class MovementUtils {
    private static final Set<Coordinates> SHIFTS = Set.of(
            new Coordinates(-1, 0),
            new Coordinates(1, 0),
            new Coordinates(0, -1),
            new Coordinates(0, 1)
    );

    private MovementUtils() {
    }

    public static Set<Coordinates> getAvailableCellsForMove(Coordinates current, Class<?> target, GameMap gameMap) {
        Set<Coordinates> availableCells = new HashSet<>();
        for (Coordinates shift : SHIFTS) {
            Coordinates candidateCell = shiftCoordinates(current, shift);
            if (!isCellBlockedForMovement(candidateCell, target, gameMap)) {
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

    private static boolean isCellBlockedForMovement(Coordinates coordinates, Class<?> target, GameMap gameMap) {
        if (!gameMap.isValidCoordinates(coordinates)) {
            return true;
        }
        if (!gameMap.isCellEmpty(coordinates)) {
            Optional<Entity> entity = gameMap.getEntity(coordinates);
            return entity.filter(value -> !target.isInstance(value)).isPresent();
        }
        return false;
    }

    private static Coordinates shiftCoordinates(Coordinates current, Coordinates shift) {
        return new Coordinates(current.column() + shift.column(), current.row() + shift.row());
    }
}