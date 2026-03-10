package main.java.entity.creature;

import main.java.service.FeedingService;
import main.java.service.PathFindingService;

public abstract class Survivor extends Creature {
    public Survivor(int hp, int speed, Class<?> typeOfFood, PathFindingService pathFindingService, FeedingService feedingService) {
        super(hp, speed, typeOfFood, pathFindingService, feedingService);
    }
}