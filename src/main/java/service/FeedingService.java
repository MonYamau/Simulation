package main.java.service;

import main.java.Coordinates;
import main.java.entities.animate.Creature;
import main.java.map.GameMap;

public interface FeedingService {
    boolean canEat(Creature creature, Coordinates coordinates, GameMap gameMap);

    void eat(Creature creature, Coordinates coordinates, GameMap gameMap);
}
