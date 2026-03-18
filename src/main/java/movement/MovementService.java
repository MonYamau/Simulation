package main.java.movement;

import main.java.entity.Entity;
import main.java.gamemap.Coordinates;
import main.java.gamemap.GameMap;
import main.java.utils.MovementUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovementService {
    PathFinder pathFinder;

    public MovementService(PathFinder pathFinder) {
        this.pathFinder = pathFinder;
    }

    public Coordinates getNextCellForMove(GameMap gameMap, Class<? extends Entity> target, Coordinates current) {
        List<Coordinates> path = pathFinder.find(current, target, gameMap);
        if (!path.isEmpty()) return path.getFirst();
        return getRandomCell(current, target, gameMap);
    }

    private Coordinates getRandomCell(Coordinates current, Class<?> target, GameMap gameMap) {
        Random random = new Random();
        List<Coordinates> availableCells;
        availableCells = new ArrayList<>(MovementUtils.getAvailableCellsForMove(current, target, gameMap));
        if (!availableCells.isEmpty()) {
            return availableCells.get(random.nextInt(availableCells.size()));
        }
        return current;
    }
}
