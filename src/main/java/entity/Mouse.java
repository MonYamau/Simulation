package main.java.entity;

import main.java.utils.Coordinates;
import main.java.service.FeedingService;
import main.java.service.PathFindingService;

public class Mouse extends Survivor {
    public Mouse(int hp, int speed, String food, Coordinates coordinates, PathFindingService pathFindingService, FeedingService feedingService) {
        super(hp, speed, food, coordinates, pathFindingService, feedingService);
    }
}