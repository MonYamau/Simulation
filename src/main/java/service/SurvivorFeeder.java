package main.java.service;

import main.java.entity.Creature;
import main.java.map.GameMap;
import main.java.utils.Coordinates;

public class SurvivorFeeder implements FeedingService {
    @Override
    public void eat(Creature creature, Coordinates coordinates, GameMap gameMap) {
        gameMap.removeEntity(coordinates);
        creature.setHp(creature.getHp() + creature.getSaturation());
    }
}
