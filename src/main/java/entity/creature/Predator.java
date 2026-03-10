package main.java.entity.creature;

import main.java.service.FeedingService;
import main.java.service.PathFindingService;

public abstract class Predator extends Creature {
    private final int attack;

    public Predator(int hp, int speed, Class<?> typeOfFood, PathFindingService pathFindingService, FeedingService feedingService, int attack) {
        super(hp, speed, typeOfFood, pathFindingService, feedingService);
        this.attack = attack;
    }


    public int getAttack() {
        return attack;
    }
}
