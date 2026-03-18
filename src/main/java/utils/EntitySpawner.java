package main.java.utils;

import main.java.core.EntityFactory;
import main.java.entity.Entity;
import main.java.gamemap.Coordinates;
import main.java.gamemap.GameMap;

import java.util.ArrayList;
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
        List<Coordinates> emptyCells = getEmptyCells(gameMap);
        int randomIndex = random.nextInt(emptyCells.size());
        return emptyCells.get(randomIndex);
    }

    public static List<Coordinates> getEmptyCells(GameMap gameMap) {
        List<Coordinates> emptyCells = new ArrayList<>();
        for (int col = 0; col < gameMap.getHeight(); col++) {
            for (int row = 0; row < gameMap.getWidth(); row++) {
                if (gameMap.isCellEmpty(new Coordinates(col, row))) {
                    emptyCells.add(new Coordinates(col, row));
                }
            }
        }
        return emptyCells;
    }
}
