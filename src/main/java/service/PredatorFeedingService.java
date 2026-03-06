package main.java.service;

import main.java.utils.Coordinates;
import main.java.entity.Entity;
import main.java.entity.Creature;
import main.java.entity.Predator;
import main.java.map.GameMap;

public class PredatorFeedingService implements FeedingService {
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
        Creature target = (Creature) gameMap.getEntity(coordinates);
        if (target.getHp() > 0) {
            makeAttack(target, creature);
        } else {
            gameMap.removeEntity(coordinates);
            creature.setHp(creature.getHp() + 2);
        }
    }

    public void makeAttack(Creature target, Creature predator) {
        int attack = ((Predator) predator).getAttack();
        target.setHp(target.getHp() - attack);
    }
}
