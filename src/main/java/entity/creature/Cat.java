package main.java.entity.creature;

import main.java.entity.Entity;
import main.java.movement.MovementService;

public class Cat extends Predator {
    public Cat(int hp, int speed, Class<? extends Entity> typeOfFood, MovementService movementService, int attack) {
        super(hp, speed, typeOfFood, movementService, attack);
    }
}
