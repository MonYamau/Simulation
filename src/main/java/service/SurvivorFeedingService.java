package main.java.service;

import main.java.utils.Coordinates;
import main.java.entities.Entity;
import main.java.entities.animate.Creature;
import main.java.map.GameMap;

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
