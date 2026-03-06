package main.java.service;

import main.java.utils.Coordinates;
import main.java.map.GameMap;

import java.util.List;

public interface PathFindingService {
    Coordinates getNextCellForMove(Coordinates entityCoordinates, String food, GameMap gameMap);

    List<Coordinates> findPathToTarget(Coordinates entityCoordinates, String food, GameMap gameMap);
}
