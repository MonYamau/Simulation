package main.java.map;

import main.java.Coordinates;
import main.java.entities.Entity;
import main.java.entities.animate.Creature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GameMap {
    public static final int MAX_COLUMN_VALUE = 12;
    public static final int MAX_ROW_VALUE = 16;

    HashMap<Coordinates, Entity> entities = new HashMap<>();

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public List<Creature> getAllCreatures() {
        List<Creature> creatures = new ArrayList<>();
        for (Entity entity : entities.values()) {
            if (entity instanceof Creature) {
                creatures.add((Creature) entity);
            }
        }
        return creatures;
    }

    public List<Entity> getAllEntities() {
        return new ArrayList<>(entities.values());
    }

    public <T extends Entity> void setEntity(Coordinates coordinates, T entity) {
        entities.put(coordinates, entity);
        if (entity instanceof Creature) {
            ((Creature) entity).setCoordinates(coordinates);
        }
    }

    public void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public void replaceEntity(Coordinates from, Coordinates to) {
        Entity entity = getEntity(from);
        removeEntity(from);
        setEntity(to, entity);
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates) || getEntity(coordinates) == null;
    }

    public boolean isCellWithinBoundaries(Coordinates coordinates) {
        if (!(coordinates.col() < MAX_COLUMN_VALUE && coordinates.col() >= 0)) return false;
        return coordinates.row() < MAX_ROW_VALUE && coordinates.row() >= 0;
    }

    public boolean isCellOccupied(Coordinates coordinates, String food) {
        if (!isCellEmpty(coordinates)) {
            Entity entity = getEntity(coordinates);
            return !entity.getClass().getSimpleName().equals(food);
        }
        return false;
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

    public Coordinates getRandomEmptyCell() {
        Random random = new Random();
        List<Coordinates> emptyCells = getAllEmptyCells();
        int randomIndex = random.nextInt(emptyCells.size());
        return emptyCells.get(randomIndex);
    }
}
