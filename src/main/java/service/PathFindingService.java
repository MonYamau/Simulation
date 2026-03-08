package main.java.service;

import main.java.map.GameMap;
import main.java.utils.Coordinates;

import java.util.List;

public interface PathFindingService {
    Coordinates getNextCellForMove(Coordinates entityCoordinates, Class<?> target, GameMap gameMap);

    List<Coordinates> findPathToTarget(Coordinates entityCoordinates, Class<?> target, GameMap gameMap);
}
