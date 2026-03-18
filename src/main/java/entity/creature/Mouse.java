package main.java.entity.creature;

import main.java.movement.MovementService;

public class Mouse extends Survivor {

    public Mouse(int hp, int speed, Class<?> typeOfFood, MovementService movementService) {
        super(hp, speed, typeOfFood, movementService);
    }
}