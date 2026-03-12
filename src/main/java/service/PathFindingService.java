package main.java.service;

import main.java.map.Coordinates;
import main.java.map.GameMap;

import java.util.List;

public interface PathFindingService {
    List<Coordinates> find(Coordinates start, Class<?> target, GameMap gameMap);
}
