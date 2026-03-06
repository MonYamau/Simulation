package main.java.service;

import main.java.map.GameMap;
import main.java.utils.Coordinates;

import java.util.List;

public interface PathFindingService {
    Coordinates getNextCellForMove(Coordinates entityCoordinates, String target, GameMap gameMap);

    List<Coordinates> findPathToTarget(Coordinates entityCoordinates, String target, GameMap gameMap);
}
