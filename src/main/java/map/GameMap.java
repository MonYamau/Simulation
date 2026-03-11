package main.java.map;

import main.java.entity.Entity;
import main.java.utils.Coordinates;

import java.util.*;

public class GameMap {
    public static final int MAX_COLUMN_VALUE = 12;
    public static final int MAX_ROW_VALUE = 16;

    private final Map<Coordinates, Entity> entities = new HashMap<>();

    public Optional<Entity> getEntity(Coordinates coordinates) {
        if (isCellWithinBoundaries(coordinates)) {
            return Optional.ofNullable(entities.get(coordinates));
        }
        throw new IllegalArgumentException("invalid coordinates received: " + coordinates);
    }

    public Coordinates getCoordinates(Entity entity) {
        for (Map.Entry<Coordinates, Entity> entry : entities.entrySet()) {
            if (entry.getValue() == entity) {
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException("invalid entity received: " + entity);
    }

    public <T extends Entity> void putEntity(Coordinates coordinates, T entity) {
        if (isCellWithinBoundaries(coordinates)) {
            entities.put(coordinates, entity);
            return;
        }
        throw new IllegalArgumentException("invalid coordinates received: " + coordinates);
    }

    public void removeEntity(Coordinates coordinates) {
        if (isCellWithinBoundaries(coordinates)) {
            entities.remove(coordinates);
            return;
        }
        throw new IllegalArgumentException("invalid coordinates received: " + coordinates);
    }

    public <T extends Entity> List<T> getEntitiesOfAnyType(Class<T> entityClass) {
        List<T> anyEntities = new ArrayList<>();
        for (Entity entity : entities.values()) {
            if (entityClass.isInstance(entity)) {
                anyEntities.add(entityClass.cast(entity));
            }
        }
        return anyEntities;
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        if (isCellWithinBoundaries(coordinates)) {
            return !entities.containsKey(coordinates);
        }
        throw new IllegalArgumentException("invalid coordinates received: " + coordinates);
    }

    public boolean isCellWithinBoundaries(Coordinates coordinates) {
        if (!(coordinates.col() < MAX_COLUMN_VALUE && coordinates.col() >= 0)) return false;
        return coordinates.row() < MAX_ROW_VALUE && coordinates.row() >= 0;
    }

    public List<Coordinates> getAllEmptyCells() {
        List<Coordinates> emptyCells = new ArrayList<>();
        for (int col = 0; col < MAX_COLUMN_VALUE; col++) {
            for (int row = 0; row < MAX_ROW_VALUE; row++) {
                if (isCellEmpty(new Coordinates(col, row))) {
                    emptyCells.add(new Coordinates(col, row));
                }
            }
        }
        return emptyCells;
    }
}
