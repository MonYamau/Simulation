package main.java.service;

import main.java.entity.Creature;
import main.java.map.GameMap;
import main.java.utils.Coordinates;

import java.util.List;

public interface PathFindingService {
    Coordinates getNextCellForMove(Creature creature, GameMap gameMap);

    List<Coordinates> findPathToTarget(Creature creature, GameMap gameMap);
}
