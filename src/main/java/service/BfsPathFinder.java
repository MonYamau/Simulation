package main.java.service;

import main.java.map.GameMap;
import main.java.utils.Coordinates;
import main.java.utils.MovementUtils;

import java.util.*;

public class BfsPathFinder implements PathFindingService {
    LinkedList<Coordinates> check;
    Set<Coordinates> checked;
    Map<Coordinates, Coordinates> savedPath;

    @Override
    public List<Coordinates> find(Coordinates start, Class<?> target, GameMap gameMap) {
        check = new LinkedList<>();
        checked = new HashSet<>();
        savedPath = new HashMap<>();
        check.add(start);
        checked.add(start);
        savedPath.put(start, null);
        return useBfsAlgorithm(start, target, gameMap);
    }

    private List<Coordinates> useBfsAlgorithm(Coordinates start, Class<?> target, GameMap gameMap) {
        List<Coordinates> pathToTarget = new ArrayList<>();

        while (!check.isEmpty()) {
            Coordinates neighbor = check.poll();
            if (MovementUtils.isTarget(neighbor, target, gameMap)) {
                pathToTarget = restorePath(neighbor, start);
                return pathToTarget;
            }

            for (Coordinates coordinates : MovementUtils.getAvailableCellsForMove(neighbor, target, gameMap)) {
                if (!checked.contains(coordinates)) {
                    savedPath.put(coordinates, neighbor);
                    check.addLast(coordinates);
                    checked.add(coordinates);
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
}