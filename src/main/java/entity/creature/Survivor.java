package main.java.entity.creature;

import main.java.service.FeedingService;
import main.java.service.PathFindingService;
import main.java.utils.Coordinates;

public abstract class Survivor extends Creature {
    public Survivor(int hp, int speed, Class<?> typeOfFood, Coordinates coordinates, PathFindingService pathFindingService, FeedingService feedingService) {
        super(hp, speed, typeOfFood, coordinates, pathFindingService, feedingService);
    }
}