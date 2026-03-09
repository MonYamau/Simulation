package main.java.service;

import main.java.entity.Entity;
import main.java.map.GameMap;
import main.java.utils.Coordinates;
import main.java.utils.CoordinatesShift;
import main.java.utils.MovementUtils;

import java.util.*;

public class BfsPathFindingService implements PathFindingService {
    LinkedList<Coordinates> check;
    Set<Coordinates> checked;
    Map<Coordinates, Coordinates> savedPath;

    @Override
    public Coordinates getNextCellForMove(Coordinates start, Class<?> target, GameMap gameMap) {
        Random random = new Random();
        List<Coordinates> path = findPathToTarget(start, target, gameMap);
        if (!path.isEmpty()) return path.getFirst();
        List<Coordinates> availableCells = new ArrayList<>(getAvailableCellsForMove(start, target, gameMap));
        if (!availableCells.isEmpty()) {
            return availableCells.get(random.nextInt(availableCells.size()));
        }
        return start;
    }

    @Override
    public List<Coordinates> findPathToTarget(Coordinates start, Class<?> target, GameMap gameMap) {
        check = new LinkedList<>();
        checked = new HashSet<>();
        savedPath = new HashMap<>();
        checked.add(start);
        for (Coordinates coordinates : getAvailableCellsForMove(start, target, gameMap)) {
            check.addLast(coordinates);
            checked.add(coordinates);
            savedPath.put(coordinates, start);
        }
        return useBfsAlgorithm(start, target, gameMap);
    }

    private List<Coordinates> useBfsAlgorithm(Coordinates start, Class<?> target, GameMap gameMap) {
        List<Coordinates> pathToTarget = new ArrayList<>();
        while (!check.isEmpty()) {
            Coordinates nextCheck = check.poll();
            if (isTarget(nextCheck, target, gameMap)) {
                pathToTarget = restorePath(nextCheck, start);
                return pathToTarget;
            } else {
                for (Coordinates coordinates : getAvailableCellsForMove(nextCheck, target, gameMap)) {
                    if (!checked.contains(coordinates)) {
                        savedPath.put(coordinates, nextCheck);
                        check.addLast(coordinates);
                        checked.add(coordinates);
                    }
                }
            }
        }
        return pathToTarget;
    }

    private List<Coordinates> restorePath(Coordinates finish, Coordinates start) {
        List<Coordinates> path = new ArrayList<>();
        Coordinates current = finish;
        while (current != start) {
            path.add(current);
            current = savedPath.get(current);
        }
        return path.reversed();
    }

    public boolean isTarget(Coordinates coordinates, Class<?> target, GameMap gameMap) {
        if (!gameMap.isCellEmpty(coordinates)) {
            Entity entity = gameMap.getEntity(coordinates);
            return target.isInstance(entity);
        }
        return false;
    }

    private Set<Coordinates> getAvailableCellsForMove(Coordinates coordinates, Class<?> target, GameMap gameMap) {
        Set<Coordinates> availableCells = new HashSet<>();
        for (CoordinatesShift shift : MovementUtils.getShifts()) {
            Coordinates newCheck = MovementUtils.moveCoordinates(coordinates, shift);
            if (gameMap.isCellWithinBoundaries(newCheck) && !MovementUtils.isCellOccupied(newCheck, target, gameMap)) {
                availableCells.add(newCheck);
            }
        }
        return availableCells;
    }
}