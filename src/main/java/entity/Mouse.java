package main.java.entity;

import main.java.service.FeedingService;
import main.java.service.PathFindingService;
import main.java.utils.Coordinates;

public class Mouse extends Survivor {

    public Mouse(int hp, int speed, Class<?> typeOfFood, Coordinates coordinates, PathFindingService pathFindingService, FeedingService feedingService) {
        super(hp, speed, typeOfFood, coordinates, pathFindingService, feedingService);
    }
}