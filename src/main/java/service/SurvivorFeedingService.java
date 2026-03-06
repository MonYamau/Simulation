package main.java.service;

import main.java.entity.Creature;
import main.java.entity.Entity;
import main.java.map.GameMap;
import main.java.utils.Coordinates;

public class SurvivorFeedingService implements FeedingService {
    @Override
    public boolean canEat(Creature creature, Coordinates coordinates, GameMap gameMap) {
        if (!gameMap.isCellEmpty(coordinates)) {
            Entity entity = gameMap.getEntity(coordinates);
            return entity.getClass().getSimpleName().equals(creature.getFood());
        }
        return false;
    }

    @Override
    public void eat(Creature creature, Coordinates coordinates, GameMap gameMap) {
        gameMap.removeEntity(coordinates);
        creature.setHp(creature.getHp() + 2);
    }
}
