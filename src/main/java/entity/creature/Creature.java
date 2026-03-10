package main.java.entity.creature;

import main.java.entity.Entity;
import main.java.map.GameMap;
import main.java.service.FeedingService;
import main.java.service.PathFindingService;
import main.java.utils.Coordinates;
import main.java.utils.MovementUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Creature extends Entity {
    private final int speed;
    private final Class<?> typeOfFood;
    private final PathFindingService pathFindingService;
    private final FeedingService feedingService;
    private int hp;
    private Coordinates coordinates;

    public Creature(int hp, int speed, Class<?> typeOfFood, Coordinates coordinates, PathFindingService pathFindingService, FeedingService feedingService) {
        this.hp = hp;
        this.speed = speed;
        this.typeOfFood = typeOfFood;
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

    public Class<?> getTypeOfFood() {
        return typeOfFood;
    }

    public int getSaturation() {
        return 2;
    }

    public void makeMove(GameMap gameMap) {
        for (int i = 0; i < getSpeed(); i++) {
            Coordinates move = getNextCellForMove(gameMap);
            if (MovementUtils.isTarget(move, getTypeOfFood(), gameMap)) {
                feedingService.getFood(this, move, gameMap);
            } else {
                moveEntity(getCoordinates(), move, gameMap);
            }
        }
    }

    private Coordinates getNextCellForMove(GameMap gameMap) {
        Random random = new Random();
        List<Coordinates> path = pathFindingService.find(getCoordinates(), getTypeOfFood(), gameMap);
        if (!path.isEmpty()) return path.getFirst();
        List<Coordinates> availableCells = new ArrayList<>(MovementUtils.getAvailableCellsForMove(getCoordinates(), getTypeOfFood(), gameMap));
        if (!availableCells.isEmpty()) {
            return availableCells.get(random.nextInt(availableCells.size()));
        }
        return getCoordinates();
    }

    private void moveEntity(Coordinates from, Coordinates to, GameMap gameMap) {
        Entity entity = gameMap.getEntity(from);
        gameMap.removeEntity(from);
        gameMap.putEntity(to, entity);
    }
}
