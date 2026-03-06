package main.java.service;

import main.java.entity.Creature;
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
    public Coordinates getNextCellForMove(Creature creature, GameMap gameMap) {
        Random random = new Random();
        List<Coordinates> path = findPathToTarget(creature, gameMap);
        if (!path.isEmpty()) return path.getFirst();
        List<Coordinates> availableCells = new ArrayList<>(getAvailableCellsForMove(creature.getCoordinates(), creature.getTypeOfFood(), gameMap));
        if (!availableCells.isEmpty()) {
            return availableCells.get(random.nextInt(availableCells.size()));
        }
        return creature.getCoordinates();
    }

    @Override
    public List<Coordinates> findPathToTarget(Creature creature, GameMap gameMap) {
        check = new LinkedList<>();
        checked = new HashSet<>();
        savedPath = new HashMap<>();
        checked.add(creature.getCoordinates());
        Set<Coordinates> availableCells = getAvailableCellsForMove(creature.getCoordinates(), creature.getTypeOfFood(), gameMap);
        for (Coordinates coordinate : availableCells) {
            check.addLast(coordinate);
            checked.add(coordinate);
            savedPath.put(coordinate, creature.getCoordinates());
        }
        return useBfsAlgorithm(creature, gameMap);
    }

    private List<Coordinates> useBfsAlgorithm(Creature creature, GameMap gameMap) {
        List<Coordinates> pathToFood = new ArrayList<>();
        while (!check.isEmpty()) {
            Coordinates nextCheck = check.poll();
            if (isEntityEdible(nextCheck, creature.getTypeOfFood(), gameMap)) {
                pathToFood = restorePath(nextCheck, creature.getCoordinates());
                return pathToFood;
            } else {
                Set<Coordinates> availableCells = getAvailableCellsForMove(creature.getCoordinates(), creature.getTypeOfFood(), gameMap);
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