package main.java.entity.creature;

import main.java.entity.Entity;
import main.java.map.GameMap;
import main.java.service.FeedingService;
import main.java.service.PathFindingService;
import main.java.utils.Coordinates;
import main.java.utils.MovementUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public abstract class Creature extends Entity {
    private final int speed;
    private final Class<?> typeOfFood;
    private final PathFindingService pathFindingService;
    private final FeedingService feedingService;
    private int hp;

    public Creature(int hp, int speed, Class<?> typeOfFood, PathFindingService pathFindingService, FeedingService feedingService) {
        this.hp = hp;
        this.speed = speed;
        this.typeOfFood = typeOfFood;
        this.pathFindingService = pathFindingService;
        this.feedingService = feedingService;
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

    public boolean isDead() {
        return getHp() <= 0;
    }

    private Coordinates getCoordinatesFromMap(GameMap gameMap) {
        Coordinates coordinates = gameMap.getCoordinates(this);
        if (coordinates == null) {
            throw new IllegalStateException("Creature not found on the map");
        }
        return coordinates;
    }

    public void makeMove(GameMap gameMap) {
        Coordinates currentCoordinates = getCoordinatesFromMap(gameMap);
        for (int i = 0; i < getSpeed(); i++) {
            Coordinates move = getNextCellForMove(gameMap);
            if (MovementUtils.isTarget(move, getTypeOfFood(), gameMap)) {
                feedingService.getFood(this, move, gameMap);
            } else {
                moveEntity(currentCoordinates, move, gameMap);
                currentCoordinates = move;
            }
        }
    }

    private Coordinates getNextCellForMove(GameMap gameMap) {
        Coordinates currentCoordinates = getCoordinatesFromMap(gameMap);
        Random random = new Random();
        List<Coordinates> path = pathFindingService.find(currentCoordinates, getTypeOfFood(), gameMap);
        if (!path.isEmpty()) return path.getFirst();
        List<Coordinates> availableCells = new ArrayList<>(MovementUtils.getAvailableCellsForMove(currentCoordinates, getTypeOfFood(), gameMap));
        if (!availableCells.isEmpty()) {
            return availableCells.get(random.nextInt(availableCells.size()));
        }
        return currentCoordinates;
    }

    private void moveEntity(Coordinates from, Coordinates to, GameMap gameMap) {
        Optional<Entity> entity = gameMap.getEntity(from);
        if (entity.isEmpty()) {
            throw new IllegalArgumentException("invalid entity received: " + entity);
        }
        gameMap.removeEntity(from);
        gameMap.putEntity(to, entity.get());
    }
}
