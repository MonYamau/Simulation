package main.java.service;

import main.java.entity.Entity;
import main.java.map.Coordinates;
import main.java.map.GameMap;

import java.util.*;

public class MovementOptionsProvider {
    Set<Coordinates> shifts;

    public MovementOptionsProvider(Set<Coordinates> shifts){
        this.shifts = shifts;
    }

    public Set<Coordinates> getAvailableCellsForMove(Coordinates current, Class<?> target, GameMap gameMap) {
        Set<Coordinates> availableCells = new HashSet<>();
        for (Coordinates shift : shifts) {
            Coordinates candidateCell = shiftCoordinates(current, shift);
            if (!isCellBlockedForMovement(candidateCell, target, gameMap)) {
                availableCells.add(candidateCell);
            }
        }
        return availableCells;
    }

    public boolean isTarget(Coordinates coordinates, Class<?> target, GameMap gameMap) {
        if (!gameMap.isCellEmpty(coordinates)) {
            Optional<Entity> entity = gameMap.getEntity(coordinates);
            return entity.filter(target::isInstance).isPresent();
        }
        return false;
    }

    private boolean isCellBlockedForMovement(Coordinates coordinates, Class<?> target, GameMap gameMap) {
        if (!gameMap.isValidCoordinates(coordinates)) {
            return true;
        }
        if (!gameMap.isCellEmpty(coordinates)) {
            Optional<Entity> entity = gameMap.getEntity(coordinates);
            return entity.filter(value -> !target.isInstance(value)).isPresent();
        }
        return false;
    }

    private Coordinates shiftCoordinates(Coordinates current, Coordinates shift) {
        return new Coordinates(current.col() + shift.col(), current.row() + shift.row());
    }
}