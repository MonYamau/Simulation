package main.java.service;

import main.java.entity.Creature;
import main.java.map.GameMap;
import main.java.utils.Coordinates;

public interface FeedingService {
    void eat(Creature creature, Coordinates coordinates, GameMap gameMap);
}
