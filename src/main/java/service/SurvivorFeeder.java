package main.java.service;

import main.java.entity.creature.Creature;
import main.java.map.GameMap;
import main.java.map.Coordinates;

public class SurvivorFeeder implements FeedingService {
    @Override
    public void getFood(Creature creature, Coordinates target, GameMap gameMap) {
        gameMap.removeEntity(target);
        creature.setHp(creature.getHp() + creature.getSaturation());
    }
}
