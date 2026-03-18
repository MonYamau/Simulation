package main.java.entity.creature;

import main.java.gamemap.GameMap;
import main.java.gamemap.Coordinates;
import main.java.service.MovementService;

public abstract class Survivor extends Creature {
    public Survivor(int hp, int speed, Class<?> typeOfFood, MovementService movementService) {
        super(hp, speed, typeOfFood, movementService);
    }

    @Override
    protected void eat(Coordinates targetCoordinates, GameMap gameMap) {
        gameMap.removeEntity(targetCoordinates);
        this.setHp(this.getHp() + this.getSaturation());
    }
}