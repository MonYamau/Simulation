package main.java.map;

import main.java.entity.Entity;
import main.java.utils.Coordinates;

import java.util.*;

import static main.java.utils.SimulationConstants.DEFAULT_MAX_COLUMN_VALUE;
import static main.java.utils.SimulationConstants.DEFAULT_MAX_ROW_VALUE;

public class GameMap {
    private final Map<Coordinates, Entity> entities = new HashMap<>();

    public Optional<Entity> getEntity(Coordinates coordinates) {
        if (isValidCoordinates(coordinates)) {
            return Optional.ofNullable(entities.get(coordinates));
        }
        throw new IllegalArgumentException("invalid coordinates received: " + coordinates);
    }

    public Coordinates getCoordinatesOf(Entity entity) {
        for (Map.Entry<Coordinates, Entity> entry : entities.entrySet()) {
            if (entry.getValue() == entity) {
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException("invalid entity received: " + entity);
    }

    public <T extends Entity> void putEntity(Coordinates coordinates, T entity) {
        if (isValidCoordinates(coordinates)) {
            entities.put(coordinates, entity);
            return;
        }
        throw new IllegalArgumentException("invalid coordinates received: " + coordinates);
    }

    public void removeEntity(Coordinates coordinates) {
        if (isValidCoordinates(coordinates)) {
            entities.remove(coordinates);
            return;
        }
        throw new IllegalArgumentException("invalid coordinates received: " + coordinates);
    }

    public <T extends Entity> List<T> getEntitiesByType(Class<T> entityClass) {
        List<T> anyEntities = new ArrayList<>();
        for (Entity entity : entities.values()) {
            if (entityClass.isInstance(entity)) {
                anyEntities.add(entityClass.cast(entity));
            }
        }
        return anyEntities;
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        if (isValidCoordinates(coordinates)) {
            return !entities.containsKey(coordinates);
        }
        throw new IllegalArgumentException("invalid coordinates received: " + coordinates);
    }

    public boolean isValidCoordinates(Coordinates coordinates) {
        if (!(coordinates.col() < DEFAULT_MAX_COLUMN_VALUE && coordinates.col() >= 0)) return false;
        return coordinates.row() < DEFAULT_MAX_ROW_VALUE && coordinates.row() >= 0;
    }

    public List<Coordinates> getEmptyCells() {
        List<Coordinates> emptyCells = new ArrayList<>();
        for (int col = 0; col < DEFAULT_MAX_COLUMN_VALUE; col++) {
            for (int row = 0; row < DEFAULT_MAX_ROW_VALUE; row++) {
                if (isCellEmpty(new Coordinates(col, row))) {
                    emptyCells.add(new Coordinates(col, row));
                }
            }
        }
        return emptyCells;
    }
}
