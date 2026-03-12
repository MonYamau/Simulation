package main.java.service;

import main.java.map.GameMap;
import main.java.map.Coordinates;
import main.java.utils.MovementUtils;

import java.util.*;

public class BfsPathFinder implements PathFindingService {
    Queue<Node> check;
    Set<Coordinates> checked;

    @Override
    public List<Coordinates> find(Coordinates start, Class<?> target, GameMap gameMap) {
        check = new LinkedList<>();
        checked = new HashSet<>();
        Node startCell = new Node(start);
        check.add(startCell);
        checked.add(start);
        return executeBfs(start, target, gameMap);
    }

    private List<Coordinates> executeBfs(Coordinates start, Class<?> target, GameMap gameMap) {
        List<Coordinates> pathToTarget = new ArrayList<>();

        while (!check.isEmpty()) {
            Node current = check.poll();
            Coordinates currentCoordinates = current.getCoordinates();
            if (MovementUtils.isTarget(currentCoordinates, target, gameMap)) {
                pathToTarget = restorePath(start, current);
                return pathToTarget;
            }

            for (Coordinates coordinates : MovementUtils.getAvailableCellsForMove(currentCoordinates, target, gameMap)) {
                if (!checked.contains(coordinates)) {
                    Node neighbor = new Node(coordinates);
                    neighbor.setParent(current);
                    check.add(neighbor);
                    checked.add(coordinates);
                }
            }
        }
        return pathToTarget;
    }

    private List<Coordinates> restorePath(Coordinates start, Node finish) {
        List<Coordinates> path = new ArrayList<>();
        Node current = finish;
        while (current.getCoordinates() != start) {
            path.add(current.getCoordinates());
            current = current.getParent();
        }
        return path.reversed();
    }
}