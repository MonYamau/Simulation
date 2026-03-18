package main.java.entity.creature;

import main.java.entity.Entity;
import main.java.gamemap.Coordinates;
import main.java.gamemap.GameMap;
import main.java.movement.MovementService;

import java.util.Optional;


public abstract class Creature extends Entity {
    private final int speed;
    private final Class<? extends Entity> typeOfFood;
    private final int saturation;
    private final MovementService movementService;
    private int hp;

    public Creature(int hp, int speed, Class<? extends Entity> typeOfFood, MovementService movementService) {
        this.hp = hp;
        this.speed = speed;
        this.typeOfFood = typeOfFood;
        this.movementService = movementService;
        this.saturation = 2;
    }

    protected int getHp() {
        return hp;
    }

    protected void setHp(int hp) {
        this.hp = hp;
    }

    protected int getSaturation() {
        return saturation;
    }

    public boolean isAlive() {
        return getHp() > 0;
    }

    public void makeMove(GameMap gameMap) {
        Optional<Coordinates> currentCoordinates = gameMap.getCoordinates(this);
        Coordinates currentCell = currentCoordinates.get();
        for (int i = 0; i < speed; i++) {
            Coordinates cell = movementService.getNextCellForMove(gameMap, typeOfFood, currentCell);
            if (isTypeOfFood(cell, gameMap)) {
                Optional<Entity> entity = gameMap.getEntity(cell);
                eat(entity.get(), gameMap);
            } else {
                move(currentCell, cell, gameMap);
                currentCell = cell;
            }
        }
    }

    protected abstract void eat(Entity food, GameMap gameMap);

    private boolean isTypeOfFood(Coordinates coordinates, GameMap gameMap) {
        if (!gameMap.isCellEmpty(coordinates)) {
            Optional<Entity> entity = gameMap.getEntity(coordinates);
            return entity.filter(typeOfFood::isInstance).isPresent();
        }
        return false;
    }

    private void move(Coordinates from, Coordinates to, GameMap gameMap) {
        Optional<Entity> entity = gameMap.getEntity(from);
        gameMap.removeEntity(from);
        gameMap.putEntity(to, entity.get());
    }
}
