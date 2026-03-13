package main.java.service;

import main.java.map.Coordinates;
import main.java.map.GameMap;

import java.util.List;

public interface PathFinder {
    List<Coordinates> find(Coordinates start, Class<?> target, GameMap gameMap);
}
