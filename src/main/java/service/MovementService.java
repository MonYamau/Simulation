package main.java.service;

import main.java.map.Coordinates;
import main.java.map.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovementService {
    MovementOptionsProvider movementOptionsProvider;
    PathFinder pathFinder;

    public MovementService(MovementOptionsProvider movementOptionsProvider, PathFinder pathFinder) {
        this.movementOptionsProvider = movementOptionsProvider;
        this.pathFinder = pathFinder;
    }

    public Coordinates getNextCellForMove(GameMap gameMap, Class<?> target, Coordinates current) {
        List<Coordinates> path = pathFinder.find(current, target, gameMap);
        if (!path.isEmpty()) return path.getFirst();
        return getRandomCell(current, target, gameMap);
    }

    private Coordinates getRandomCell(Coordinates current, Class<?> target, GameMap gameMap) {
        Random random = new Random();
        List<Coordinates> availableCells;
        availableCells = new ArrayList<>(movementOptionsProvider.getAvailableCellsForMove(current, target, gameMap));
        if (!availableCells.isEmpty()) {
            return availableCells.get(random.nextInt(availableCells.size()));
        }
        return current;
    }
}
