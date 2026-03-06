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
    public Coordinates getNextCellForMove(Coordinates start, String target, GameMap gameMap) {
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
    public List<Coordinates> findPathToTarget(Coordinates start, String target, GameMap gameMap) {
        check = new LinkedList<>();
        checked = new HashSet<>();
        savedPath = new HashMap<>();
        checked.add(start);
        Set<Coordinates> availableCells = getAvailableCellsForMove(start, target, gameMap);
        for (Coordinates coordinate : availableCells) {
            check.addLast(coordinate);
            checked.add(coordinate);
            savedPath.put(coordinate, start);
        }
        return useBfsAlgorithm(start, target, gameMap);
    }

    private List<Coordinates> useBfsAlgorithm(Coordinates start, String target, GameMap gameMap) {
        List<Coordinates> pathToFood = new ArrayList<>();
        while (!check.isEmpty()) {
            Coordinates nextCheck = check.poll();
            if (isEntityEdible(nextCheck, target, gameMap)) {
                pathToFood = restorePath(nextCheck, start);
                return pathToFood;
            } else {
                Set<Coordinates> availableCells = getAvailableCellsForMove(start, target, gameMap);
                for (Coordinates coordinate : availableCells) {
                    if (!checked.contains(coordinate)) {
                        savedPath.put(coordinate, nextCheck);
                        check.addLast(coordinate);
                        checked.add(coordinate);
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

    public boolean isEntityEdible(Coordinates coordinates, String typeOfFood, GameMap gameMap) {
        if (!gameMap.isCellEmpty(coordinates)) {
            Entity entity = gameMap.getEntity(coordinates);
            return entity.getClass().getSimpleName().equals(typeOfFood);
        }
        return false;
    }

    private Set<Coordinates> getAvailableCellsForMove(Coordinates coordinates, String typeOfFood, GameMap gameMap) {
        Set<Coordinates> availableCells = new HashSet<>();
        for (CoordinatesShift shift : MovementUtils.getShifts()) {
            Coordinates newCheck = MovementUtils.moveCoordinates(coordinates, shift);
            if (isAvailableCell(newCheck, typeOfFood, gameMap)) {
                availableCells.add(newCheck);
            }
        }
        return availableCells;
    }

    public boolean isAvailableCell(Coordinates coordinates, String typeOfFood, GameMap gameMap) {
        return gameMap.isCellWithinBoundaries(coordinates) && !MovementUtils.isCellOccupied(coordinates, typeOfFood, gameMap);
    }
}