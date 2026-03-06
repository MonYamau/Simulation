package main.java.entity;

import main.java.service.FeedingService;
import main.java.service.PathFindingService;
import main.java.utils.Coordinates;

public class Cat extends Predator {
    public Cat(int hp, int speed, String food, Coordinates coordinates, PathFindingService pathFindingService, FeedingService feedingService, int attack) {
        super(hp, speed, food, coordinates, pathFindingService, feedingService, attack);
    }
}
