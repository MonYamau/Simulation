package main.java.entity.creature;

import main.java.entity.Entity;
import main.java.gamemap.GameMap;
import main.java.movement.MovementService;

public abstract class Survivor extends Creature {
    public Survivor(int hp, int speed, Class<? extends Entity> typeOfFood, MovementService movementService) {
        super(hp, speed, typeOfFood, movementService);
    }

    @Override
    protected void eat(Entity food, GameMap gameMap) {
        gameMap.removeEntity(food);
        this.setHp(this.getHp() + this.getSaturation());
    }
}