package main.java.entity.creature;

import main.java.entity.Entity;
import main.java.map.Coordinates;
import main.java.map.GameMap;
import main.java.service.MovementService;

import java.util.Optional;

public abstract class Predator extends Creature {
    private final int attack;

    public Predator(int hp, int speed, Class<?> typeOfFood, MovementService movementService, int attack) {
        super(hp, speed, typeOfFood, movementService);
        this.attack = attack;
    }

    protected int getAttack() {
        return attack;
    }

    @Override
    protected void getFood(Coordinates targetCoordinates, GameMap gameMap) {
        Optional<Entity> entity = gameMap.getEntity(targetCoordinates);
        if (entity.isEmpty()) {
            return;
        }
        Creature target = (Creature) entity.get();
        if (target.isAlive()) {
            makeAttack(target);
        } else {
            gameMap.removeEntity(targetCoordinates);
            this.setHp(this.getHp() + this.getSaturation());
        }
    }

    private void makeAttack(Creature target) {
        target.setHp(target.getHp() - getAttack());
    }
}
