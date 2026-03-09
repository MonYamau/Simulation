package main.java.core;

import main.java.entity.Entity;
import main.java.map.GameMap;
import main.java.map.GameMapLayout;
import main.java.utils.Coordinates;

import java.util.List;
import java.util.Random;

public class ResourceProvider {
    public final static int MIN_NUM_OF_ENTITY = 5;

    GameMap gameMap;
    GameMapLayout gameMapLayout;

    public ResourceProvider(GameMap gameMap, GameMapLayout gameMapLayout) {
        this.gameMap = gameMap;
        this.gameMapLayout = gameMapLayout;
    }

    public <T extends Entity> void replenishEntity(Class<T> entity) {
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

    private Coordinates getRandomEmptyCell() {
        Random random = new Random();
        List<Coordinates> emptyCells = gameMap.getAllEmptyCells();
        int randomIndex = random.nextInt(emptyCells.size());
        return emptyCells.get(randomIndex);
    }
}
