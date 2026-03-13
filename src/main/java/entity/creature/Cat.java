package main.java.entity.creature;

import main.java.service.MovementService;
import main.java.service.PathFinder;

public class Cat extends Predator {
    public Cat(int hp, int speed, Class<?> typeOfFood, MovementService movementService, int attack) {
        super(hp, speed, typeOfFood, movementService, attack);
    }
}
