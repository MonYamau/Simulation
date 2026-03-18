package main.java.entity.creature;

import main.java.entity.Entity;
import main.java.movement.MovementService;

public class Mouse extends Survivor {

    public Mouse(int hp, int speed, Class<? extends Entity> typeOfFood, MovementService movementService) {
        super(hp, speed, typeOfFood, movementService);
    }
}