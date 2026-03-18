package main.java.gamemap;

import main.java.entity.Entity;
import main.java.service.Coordinates;

import java.util.*;

public class GameMap {
    private final Map<Coordinates, Entity> entities;

    private final int height;
    private final int width;

    public GameMap(int height, int width) {
        entities = new HashMap<>();
        this.height = height;
        this.width = width;
    }

    public Optional<Entity> getEntity(Coordinates coordinates) {
        if (isValidCoordinates(coordinates)) {
            return Optional.ofNullable(entities.get(coordinates));
        }
        throw new IllegalArgumentException("invalid coordinates received: " + coordinates);
    }

    public Optional<Coordinates> getCoordinates(Entity entity) {
        for (Map.Entry<Coordinates, Entity> entry : entities.entrySet()) {
            if (entry.getValue() == entity) {
                return Optional.ofNullable(entry.getKey());
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
        if (!(coordinates.column() < height && coordinates.column() >= 0)) return false;
        return coordinates.row() < width && coordinates.row() >= 0;
    }

    public List<Coordinates> getEmptyCells() {
        List<Coordinates> emptyCells = new ArrayList<>();
        for (int col = 0; col < height; col++) {
            for (int row = 0; row < width; row++) {
                if (isCellEmpty(new Coordinates(col, row))) {
                    emptyCells.add(new Coordinates(col, row));
                }
            }
        }
        return emptyCells;
    }
}
