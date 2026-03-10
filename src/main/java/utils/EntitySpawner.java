package main.java.utils;

import main.java.entity.Entity;
import main.java.entity.EntityFactory;
import main.java.map.GameMap;

import java.util.List;
import java.util.Random;

public final class EntitySpawner {
    private EntitySpawner() {
    }

    public static <T extends Entity> void setupNewEntity(Class<T> entityClass, EntityFactory entityFactory, GameMap gameMap) {
        Coordinates emptyCell = getRandomEmptyCell(gameMap);
        T entity = entityFactory.createEntity(emptyCell, entityClass);
        gameMap.putEntity(emptyCell, entity);
    }

    private static Coordinates getRandomEmptyCell(GameMap gameMap) {
        Random random = new Random();
        List<Coordinates> emptyCells = gameMap.getAllEmptyCells();
        int randomIndex = random.nextInt(emptyCells.size());
        return emptyCells.get(randomIndex);
    }
}
