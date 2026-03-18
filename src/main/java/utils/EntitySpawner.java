package main.java.utils;

import main.java.entity.Entity;
import main.java.entity.EntityFactory;
import main.java.gamemap.GameMap;
import main.java.service.Coordinates;

import java.util.List;
import java.util.Random;

public final class EntitySpawner {
    private EntitySpawner() {
    }

    public static <T extends Entity> void spawnEntity(Class<T> entityClass, EntityFactory entityFactory, GameMap gameMap) {
        Coordinates emptyCell = getRandomEmptyCell(gameMap);
        T entity = entityFactory.create(entityClass);
        gameMap.putEntity(emptyCell, entity);
    }

    private static Coordinates getRandomEmptyCell(GameMap gameMap) {
        Random random = new Random();
        List<Coordinates> emptyCells = gameMap.getEmptyCells();
        int randomIndex = random.nextInt(emptyCells.size());
        return emptyCells.get(randomIndex);
    }
}
