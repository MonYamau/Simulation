package main.java.map;

import main.java.entity.Creature;
import main.java.entity.Entity;
import main.java.utils.Coordinates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameMap {
    public static final int MAX_COLUMN_VALUE = 12;
    public static final int MAX_ROW_VALUE = 16;

    HashMap<Coordinates, Entity> entities = new HashMap<>();

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public <T extends Entity> void putEntity(Coordinates coordinates, T entity) {
        if (isCellWithinBoundaries(coordinates)) {
            entities.put(coordinates, entity);
            if (entity instanceof Creature) {
                ((Creature) entity).setCoordinates(coordinates);
            }
            return;
        }
        throw new IllegalArgumentException("invalid coordinates received");
    }

    public void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates);
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
        return !entities.containsKey(coordinates) || getEntity(coordinates) == null;
    }

    public boolean isCellWithinBoundaries(Coordinates coordinates) {
        if (!(coordinates.col() <= MAX_COLUMN_VALUE && coordinates.col() >= 0)) return false;
        return coordinates.row() <= MAX_ROW_VALUE && coordinates.row() >= 0;
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
