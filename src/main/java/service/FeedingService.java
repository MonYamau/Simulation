package main.java.service;

import main.java.entity.creature.Creature;
import main.java.map.GameMap;
import main.java.utils.Coordinates;

public interface FeedingService {
    void getFood(Creature creature, Coordinates target, GameMap gameMap);
}
