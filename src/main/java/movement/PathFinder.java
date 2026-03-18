package main.java.movement;

import main.java.entity.Entity;
import main.java.gamemap.Coordinates;
import main.java.gamemap.GameMap;

import java.util.List;

public interface PathFinder {
    List<Coordinates> find(Coordinates start, Class<? extends Entity> target, GameMap gameMap);
}
