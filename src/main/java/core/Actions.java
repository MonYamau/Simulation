package main.java.core;

import main.java.entity.Cheese;
import main.java.entity.Creature;
import main.java.entity.Entity;
import main.java.entity.Mouse;
import main.java.map.GameMap;
import main.java.map.GameMapLayout;
import main.java.utils.Coordinates;

import java.util.List;
import java.util.Random;

public class Actions {
    public final static int MIN_NUM_OF_ENTITY = 3;

    GameMap gameMap;
    GameMapLayout gameMapLayout;

    public Actions(GameMap gameMap, GameMapLayout gameMapLayout) {
        this.gameMap = gameMap;
        this.gameMapLayout = gameMapLayout;
    }

    public void initActions() {
        gameMapLayout.setupStartMap();
    }

    public void turnActions() {
        replenishFood(Mouse.class);
        replenishFood(Cheese.class);
        List<Creature> creatures = gameMap.getEntitiesOfAnyType(Creature.class);
        for (Creature creature : creatures) {
            creature.makeMove(gameMap);
        }
    }

    private <T extends Entity> void replenishFood(Class<T> entity) {
        if (isSmallAmountOfEntity(entity)) {
            Coordinates randomCell = getRandomEmptyCell();
            gameMapLayout.setupNewEntity(randomCell, entity);
        }
    }

    private <T extends Entity> boolean isSmallAmountOfEntity(Class<T> entityClass) {
        int entityCounter = 0;
        for (Entity entity : gameMap.getEntitiesOfAnyType(Entity.class)) {
            if (entityClass.isInstance(entity)) {
                entityCounter++;
            }
        }
        return entityCounter < MIN_NUM_OF_ENTITY;
    }

    public Coordinates getRandomEmptyCell() {
        Random random = new Random();
        List<Coordinates> emptyCells = gameMap.getAllEmptyCells();
        int randomIndex = random.nextInt(emptyCells.size());
        return emptyCells.get(randomIndex);
    }


}
