package main.java.entity.creature;

import main.java.movement.MovementService;

public class Cat extends Predator {
    public Cat(int hp, int speed, Class<?> typeOfFood, MovementService movementService, int attack) {
        super(hp, speed, typeOfFood, movementService, attack);
    }
}
