package main.java.gamemap;

import main.java.entity.Entity;

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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Optional<Entity> getEntity(Coordinates coordinates) {
        validate(coordinates);
        return Optional.ofNullable(entities.get(coordinates));
    }

    public Optional<Coordinates> getCoordinates(Entity entity) {
        for (Map.Entry<Coordinates, Entity> entry : entities.entrySet()) {
            if (entry.getValue() == entity) {
                return Optional.ofNullable(entry.getKey());
            }
        }
        throw new IllegalArgumentException("invalid entity received: " + entity);
    }

    public void putEntity(Coordinates coordinates, Entity entity) {
        validate(coordinates);
        entities.put(coordinates, entity);
    }

    public void removeEntity(Coordinates coordinates) {
        validate(coordinates);
        if (isCellEmpty(coordinates)) {
            throw new IllegalStateException("The entity cannot be deleted");
        }
        entities.remove(coordinates);
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
        validate(coordinates);
        return !entities.containsKey(coordinates);
    }

    public boolean isValidCoordinates(Coordinates coordinates) {
        if (!(coordinates.column() < height && coordinates.column() >= 0)) {
            return false;
        }
        return coordinates.row() < width && coordinates.row() >= 0;
    }

    private void validate(Coordinates coordinates){
        if (!isValidCoordinates(coordinates)){
            throw new IllegalArgumentException("invalid coordinates received: " + coordinates);
        }
    }
}
