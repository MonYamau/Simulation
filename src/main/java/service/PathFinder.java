package main.java.service;

import main.java.gamemap.GameMap;

import java.util.List;

public interface PathFinder {
    List<Coordinates> find(Coordinates start, Class<?> target, GameMap gameMap);
}
