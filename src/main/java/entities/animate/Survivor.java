package main.java.entities.animate;

import main.java.utils.Coordinates;
import main.java.service.FeedingService;
import main.java.service.PathFindingService;

public abstract class Survivor extends Creature {
    public Survivor(int hp, int speed, String food, Coordinates coordinates, PathFindingService pathFindingService, FeedingService feedingService) {
        super(hp, speed, food, coordinates, pathFindingService, feedingService);
    }
}