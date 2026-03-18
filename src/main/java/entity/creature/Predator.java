package main.java.entity.creature;

import main.java.entity.Entity;
import main.java.gamemap.GameMap;
import main.java.movement.MovementService;

import java.util.Optional;

public abstract class Predator extends Creature {
    private final int attack;

    public Predator(int hp, int speed, Class<? extends Entity> typeOfFood, MovementService movementService, int attack) {
        super(hp, speed, typeOfFood, movementService);
        this.attack = attack;
    }

    protected int getAttack() {
        return attack;
    }

    @Override
    protected void eat(Entity food, GameMap gameMap) {
        Creature target = (Creature) food;
        if (target.isAlive()) {
            makeAttack(target);
        } else {
            gameMap.removeEntity(food);
            this.setHp(this.getHp() + this.getSaturation());
        }
    }

    private void makeAttack(Creature target) {
        target.setHp(target.getHp() - getAttack());
    }
}
