package main.java.service;

import main.java.entity.Creature;
import main.java.entity.Predator;
import main.java.map.GameMap;
import main.java.utils.Coordinates;

public class PredatorFeeder implements FeedingService {
    private final static int MIN_NUM_HP = 0;

    @Override
    public void eat(Creature creature, Coordinates coordinates, GameMap gameMap) {
        Creature target = (Creature) gameMap.getEntity(coordinates);
        if (target.getHp() > MIN_NUM_HP) {
            makeAttack(target, creature);
        } else {
            gameMap.removeEntity(coordinates);
            creature.setHp(creature.getHp() + creature.getSaturation());
        }
    }

    private void makeAttack(Creature target, Creature predator) {
        int attack = ((Predator) predator).getAttack();
        target.setHp(target.getHp() - attack);
    }
}
