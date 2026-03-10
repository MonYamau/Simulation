package main.java.service;

import main.java.map.GameMap;
import main.java.utils.Coordinates;

import java.util.List;

public interface PathFindingService {
    List<Coordinates> find(Coordinates start, Class<?> target, GameMap gameMap);
}
