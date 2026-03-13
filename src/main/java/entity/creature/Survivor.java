package main.java.entity.creature;

import main.java.map.Coordinates;
import main.java.map.GameMap;
import main.java.service.MovementService;
import main.java.service.PathFinder;

public abstract class Survivor extends Creature {
    public Survivor(int hp, int speed, Class<?> typeOfFood, MovementService movementService) {
        super(hp, speed, typeOfFood, movementService);
    }

    @Override
    protected void getFood(Coordinates targetCoordinates, GameMap gameMap) {
        gameMap.removeEntity(targetCoordinates);
        this.setHp(this.getHp() + this.getSaturation());
    }
}