package main.java.entity.creature;

import main.java.service.FeedingService;
import main.java.service.PathFindingService;
import main.java.utils.Coordinates;

public abstract class Predator extends Creature {
    private final int attack;

    public Predator(int hp, int speed, Class<?> typeOfFood, Coordinates coordinates, PathFindingService pathFindingService, FeedingService feedingService, int attack) {
        super(hp, speed, typeOfFood, coordinates, pathFindingService, feedingService);
        this.attack = attack;
    }


    public int getAttack() {
        return attack;
    }
}
