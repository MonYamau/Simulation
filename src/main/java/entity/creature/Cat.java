package main.java.entity.creature;

import main.java.service.FeedingService;
import main.java.service.PathFindingService;

public class Cat extends Predator {
    public Cat(int hp, int speed, Class<?> typeOfFood, PathFindingService pathFindingService, FeedingService feedingService, int attack) {
        super(hp, speed, typeOfFood, pathFindingService, feedingService, attack);
    }
}
