package main.java.service;

import main.java.utils.Coordinates;
import main.java.entity.Creature;
import main.java.map.GameMap;

public interface FeedingService {
    boolean canEat(Creature creature, Coordinates coordinates, GameMap gameMap);

    void eat(Creature creature, Coordinates coordinates, GameMap gameMap);
}
