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
    public Coordinates getNextCellForMove(Coordinates start, String food, GameMap gameMap) {
        Random random = new Random();
        List<Coordinates> path = findPathToTarget(start, food, gameMap);
        if (!path.isEmpty()) return path.getFirst();
        List<Coordinates> availableCells = new ArrayList<>(getAvailableCellsForMove(start, food, gameMap));
        if (!availableCells.isEmpty()) {
            return availableCells.get(random.nextInt(availableCells.size()));
        }
        return start;
    }

    @Override
    public List<Coordinates> findPathToTarget(Coordinates start, String food, GameMap gameMap) {
        check = new LinkedList<>();
        checked = new HashSet<>();
        savedPath = new HashMap<>();
        checked.add(start);
        for (Coordinates coordinates : getAvailableCellsForMove(start, food, gameMap)) {
            check.addLast(coordinates);
            checked.add(coordinates);
            savedPath.put(coordinates, start);
        }
        return useBfsAlgorithm(start, food, gameMap);


    }

    private List<Coordinates> useBfsAlgorithm(Coordinates start, String food, GameMap gameMap) {
        List<Coordinates> pathToFood = new ArrayList<>();
        while (!check.isEmpty()) {
            Coordinates nextCheck = check.poll();
            if (isEntityEdible(nextCheck, food, gameMap)) {
                pathToFood = restorePath(nextCheck, start);
                return pathToFood;
            } else {
                for (Coordinates coordinates : getAvailableCellsForMove(nextCheck, food, gameMap)) {
                    if (!checked.contains(coordinates)) {
                        savedPath.put(coordinates, nextCheck);
                        check.addLast(coordinates);
                        checked.add(coordinates);
                    }
                }
            }
        }
        return pathToFood;
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

    public boolean isEntityEdible(Coordinates coordinates, String food, GameMap gameMap) {
        if (!gameMap.isCellEmpty(coordinates)) {
            Entity entity = gameMap.getEntity(coordinates);
            return entity.getClass().getSimpleName().equals(food);
        }
        return false;
    }

    private Set<Coordinates> getAvailableCellsForMove(Coordinates coordinates, String food, GameMap gameMap) {
        Set<Coordinates> availableCells = new HashSet<>();
        for (CoordinatesShift shift : MovementUtils.getShifts()) {
            Coordinates newCheck = MovementUtils.move(coordinates, shift);
            if (gameMap.isCellWithinBoundaries(newCheck) && !gameMap.isCellOccupied(newCheck, food)) {
                availableCells.add(newCheck);
            }
        }
        return availableCells;
    }


}