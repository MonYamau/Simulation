package main.java.service;

import main.java.entity.Entity;
import main.java.entity.creature.Creature;
import main.java.entity.creature.Predator;
import main.java.map.GameMap;
import main.java.utils.Coordinates;

import java.util.Optional;

import static main.java.utils.SimulationConstants.MIN_HP;

public class PredatorFeeder implements FeedingService {

    @Override
    public void getFood(Creature creature, Coordinates coordinates, GameMap gameMap) {
        Optional<Entity> entity = gameMap.getEntity(coordinates);
        if (entity.isEmpty()) {
            return;
        }
        Creature target = (Creature) entity.get();
        if (target.getHp() > MIN_HP) {
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
