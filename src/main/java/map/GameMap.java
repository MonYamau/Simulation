package main.java.map;

import main.java.Coordinates;
import main.java.entities.Entity;
import main.java.entities.animate.*;
import main.java.entities.inanimate.*;

import java.util.*;

public class GameMap {
    public static final int MAX_COLUMN_VALUE = 12;
    public static final int MAX_ROW_VALUE = 14;

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

    public boolean isCellOccupied(Coordinates coordinates, String food) {
        if (!isCellEmpty(coordinates)) {
            Entity entity = getEntity(coordinates);
            return !entity.getClass().getSimpleName().equals(food);
        }
        return false;
    }

    public boolean isCellWithinBoundaries(Coordinates coordinates) {
        if (!(coordinates.col() < MAX_COLUMN_VALUE && coordinates.col() >= 0)) return false;
        return coordinates.row() < MAX_ROW_VALUE && coordinates.row() >= 0;
    }


    public void setupCreatures() {
        setEntity(new Coordinates(10, 12), new Cat(2, "Mouse", new Coordinates(10, 12), 2));
        setEntity(new Coordinates(1, 1), new Mouse(1, "Cheese", new Coordinates(1, 1)));
    }

    public void setupResources() {
        setEntity(new Coordinates(4, 2), new Cheese());
        setEntity(new Coordinates(8, 7), new Cheese());
        setEntity(new Coordinates(4, 2), new Cheese());
        setEntity(new Coordinates(5, 10), new Cheese());
        setEntity(new Coordinates(6, 13), new Cheese());
        setEntity(new Coordinates(2, 7), new Cheese());
        setEntity(new Coordinates(11, 1), new Cheese());
    }

    public void setupDefaultMap() {
        setupResources();
        setupCreatures();
        setEntity(new Coordinates(3, 1), new Yarn());
        setEntity(new Coordinates(1, 4), new Yarn());
        setEntity(new Coordinates(7, 9), new Yarn());
        setEntity(new Coordinates(0, 13), new Yarn());
        setEntity(new Coordinates(2, 9), new Yarn());
        setEntity(new Coordinates(7, 0), new Yarn());
        setEntity(new Coordinates(9, 13), new Yarn());
        setEntity(new Coordinates(10, 3), new Yarn());

        setEntity(new Coordinates(3, 4), new Box());
        setEntity(new Coordinates(4, 6), new Box());
        setEntity(new Coordinates(3, 5), new Box());
        setEntity(new Coordinates(4, 5), new Box());
        setEntity(new Coordinates(8, 11), new Box());
        setEntity(new Coordinates(9, 11), new Box());
        setEntity(new Coordinates(9, 10), new Box());
        setEntity(new Coordinates(6, 2), new Box());
        setEntity(new Coordinates(6, 3), new Box());
        setEntity(new Coordinates(10, 5), new Box());
        setEntity(new Coordinates(9, 6), new Box());
        setEntity(new Coordinates(10, 7), new Box());
        setEntity(new Coordinates(3, 11), new Box());
        setEntity(new Coordinates(2, 12), new Box());
        setEntity(new Coordinates(3, 12), new Box());

        setEntity(new Coordinates(5, 8), new Basket());
        setEntity(new Coordinates(8, 4), new Basket());
        setEntity(new Coordinates(0, 10), new Basket());
        setEntity(new Coordinates(4, 13), new Basket());
        setEntity(new Coordinates(9, 1), new Basket());
        setEntity(new Coordinates(0, 10), new Basket());
        setEntity(new Coordinates(11, 9), new Basket());
    }
}
