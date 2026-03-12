package main.java.service;

import main.java.entity.creature.Creature;
import main.java.map.Coordinates;
import main.java.map.GameMap;

public interface FeedingService {
    void getFood(Creature creature, Coordinates target, GameMap gameMap);
}
