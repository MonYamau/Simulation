package main.java.entity;

import main.java.service.FeedingService;
import main.java.service.PathFindingService;
import main.java.utils.Coordinates;

public abstract class Survivor extends Creature {
    public Survivor(int hp, int speed, String food, Coordinates coordinates, PathFindingService pathFindingService, FeedingService feedingService) {
        super(hp, speed, food, coordinates, pathFindingService, feedingService);
    }
}