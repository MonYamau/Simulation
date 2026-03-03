package main.java;

import main.java.entities.Entity;
import main.java.map.GameMap;

import java.util.*;

public class PathFinder {
    LinkedList<Coordinates> check = new LinkedList<>();
    Set<Coordinates> checked = new HashSet<>();
    Map<Coordinates, Coordinates> savedPath = new HashMap<>();
    Random random = new Random();

    public Coordinates getNextCellForMove(Coordinates entityCoordinates, String food, GameMap gameMap){
        List<Coordinates> path = findPathToMeat(entityCoordinates, food, gameMap);
        if (!path.isEmpty()) return path.getFirst();
        List<Coordinates> availableCells = new ArrayList<>(getAvailableCellsForMove(entityCoordinates, gameMap, food));
        return availableCells.get(random.nextInt(availableCells.size()));
    }

    public List<Coordinates> findPathToMeat(Coordinates entityCoordinates, String food, GameMap gameMap) {
        checked.add(entityCoordinates);
        for (Coordinates coordinates : getAvailableCellsForMove(entityCoordinates, gameMap, food)) {
            check.addLast(coordinates);
            checked.add(coordinates);
            savedPath.put(coordinates, entityCoordinates);
        }
        return useBfsAlgorithm(entityCoordinates, food, gameMap);
    }

    private List<Coordinates> useBfsAlgorithm(Coordinates entityCoordinates, String food, GameMap gameMap){
        List<Coordinates> pathToFood = new ArrayList<>();
        while (!check.isEmpty()) {
            Coordinates newCheckcoordinates = check.poll();
            if (isEntityEatable(newCheckcoordinates, gameMap, food)) {
                pathToFood = restorePath(newCheckcoordinates, entityCoordinates);
                return pathToFood;
            } else {
                for (Coordinates coordinates : getAvailableCellsForMove(newCheckcoordinates, gameMap, food)) {
                    if (!checked.contains(coordinates)) {
                        savedPath.put(coordinates, newCheckcoordinates);
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

    private boolean isEntityEatable(Coordinates coordinates, GameMap gameMap, String food) {
        if (!gameMap.isCellEmpty(coordinates)) {
            Entity entity = gameMap.getEntity(coordinates);
            return entity.getClass().getSimpleName().equals(food);
        }
        return false;
    }

    private Set<Coordinates> getAvailableCellsForMove(Coordinates coordinates, GameMap gameMap, String food) {
        Set<Coordinates> availableCells = new HashSet<>();
        for (CoordinatesShift move : coordinates.getMoves()) {
            Coordinates newCoordinates = coordinates.shift(move);
            if (gameMap.isCellWithinBoundaries(newCoordinates) && !gameMap.isCellOccupied(newCoordinates, food)) {
                availableCells.add(newCoordinates);
            }
        }
        return availableCells;
    }
}