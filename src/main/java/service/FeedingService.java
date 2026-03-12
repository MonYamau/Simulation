package main.java.service;

import main.java.entity.creature.Creature;
import main.java.map.GameMap;
import main.java.map.Coordinates;

public interface FeedingService {
    void getFood(Creature creature, Coordinates target, GameMap gameMap);
}
