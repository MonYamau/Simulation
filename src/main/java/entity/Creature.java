package main.java.entity;

import main.java.utils.Coordinates;
import main.java.map.GameMap;
import main.java.service.FeedingService;
import main.java.service.PathFindingService;

public abstract class Creature extends Entity {
    private final int speed;
    private final String food;
    private final PathFindingService pathFindingService;
    private final FeedingService feedingService;
    private int hp;
    private Coordinates coordinates;

    public Creature(int hp, int speed, String food, Coordinates coordinates, PathFindingService pathFindingService, FeedingService feedingService) {
        this.hp = hp;
        this.speed = speed;
        this.food = food;
        this.coordinates = coordinates;
        this.pathFindingService = pathFindingService;
        this.feedingService = feedingService;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public String getFood() {
        return food;
    }

    public void makeMove(GameMap gameMap) {
        for (int i = 0; i < getSpeed(); i++) {
            Coordinates move = pathFindingService.getNextCellForMove(getCoordinates(), getFood(), gameMap);
            if (feedingService.canEat(this, move, gameMap)) {
                feedingService.eat(this, move, gameMap);
            } else {
                gameMap.moveEntity(getCoordinates(), move);
            }
        }
    }
}
