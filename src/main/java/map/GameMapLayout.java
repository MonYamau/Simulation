package main.java.map;

import main.java.utils.Coordinates;
import main.java.entity.Entity;
import main.java.entity.EntityFactory;
import main.java.entity.Cat;
import main.java.entity.Mouse;
import main.java.entity.Basket;
import main.java.entity.Box;
import main.java.entity.Cheese;
import main.java.entity.Yarn;

public class GameMapLayout {
    GameMap gameMap;
    EntityFactory entityFactory;

    public GameMapLayout(GameMap gameMap, EntityFactory entityFactory) {
        this.gameMap = gameMap;
        this.entityFactory = entityFactory;
    }

    public void setupStartMap() {
        setupStartResources();
        setupStartCreatures();
        setupObstacles();
    }

    public <T extends Entity> void setupNewEntity(Coordinates coordinates, Class<T> entityClass) {
        T entity = entityFactory.createEntity(coordinates, entityClass);
        gameMap.putEntity(coordinates, entity);
    }

    public void setupStartCreatures() {
        setupNewEntity(new Coordinates(10, 14), Cat.class);
        setupNewEntity(new Coordinates(10, 13), Cat.class);
        setupNewEntity(new Coordinates(9, 14), Cat.class);

        setupNewEntity(new Coordinates(1, 1), Mouse.class);
        setupNewEntity(new Coordinates(2, 0), Mouse.class);
        setupNewEntity(new Coordinates(1, 2), Mouse.class);
        setupNewEntity(new Coordinates(2, 1), Mouse.class);
        setupNewEntity(new Coordinates(0, 2), Mouse.class);
    }

    public void setupStartResources() {
        setupNewEntity(new Coordinates(4, 2), Cheese.class);
        setupNewEntity(new Coordinates(8, 7), Cheese.class);
        setupNewEntity(new Coordinates(4, 2), Cheese.class);
        setupNewEntity(new Coordinates(5, 10), Cheese.class);
        setupNewEntity(new Coordinates(6, 13), Cheese.class);
        setupNewEntity(new Coordinates(2, 7), Cheese.class);
        setupNewEntity(new Coordinates(11, 1), Cheese.class);
    }

    public void setupObstacles() {
        setupNewEntity(new Coordinates(3, 1), Yarn.class);
        setupNewEntity(new Coordinates(1, 4), Yarn.class);
        setupNewEntity(new Coordinates(7, 9), Yarn.class);
        setupNewEntity(new Coordinates(0, 13), Yarn.class);
        setupNewEntity(new Coordinates(2, 9), Yarn.class);
        setupNewEntity(new Coordinates(7, 0), Yarn.class);
        setupNewEntity(new Coordinates(9, 13), Yarn.class);
        setupNewEntity(new Coordinates(10, 3), Yarn.class);

        setupNewEntity(new Coordinates(3, 4), Box.class);
        setupNewEntity(new Coordinates(4, 6), Box.class);
        setupNewEntity(new Coordinates(3, 5), Box.class);
        setupNewEntity(new Coordinates(4, 5), Box.class);
        setupNewEntity(new Coordinates(8, 11), Box.class);
        setupNewEntity(new Coordinates(9, 11), Box.class);
        setupNewEntity(new Coordinates(9, 10), Box.class);
        setupNewEntity(new Coordinates(6, 2), Box.class);
        setupNewEntity(new Coordinates(6, 3), Box.class);
        setupNewEntity(new Coordinates(10, 5), Box.class);
        setupNewEntity(new Coordinates(9, 6), Box.class);
        setupNewEntity(new Coordinates(10, 7), Box.class);
        setupNewEntity(new Coordinates(3, 11), Box.class);
        setupNewEntity(new Coordinates(2, 12), Box.class);
        setupNewEntity(new Coordinates(3, 12), Box.class);
        setupNewEntity(new Coordinates(7, 14), Box.class);
        setupNewEntity(new Coordinates(6, 15), Box.class);
        setupNewEntity(new Coordinates(7, 15), Box.class);
        setupNewEntity(new Coordinates(8, 15), Box.class);

        setupNewEntity(new Coordinates(5, 8), Basket.class);
        setupNewEntity(new Coordinates(8, 4), Basket.class);
        setupNewEntity(new Coordinates(0, 10), Basket.class);
        setupNewEntity(new Coordinates(4, 13), Basket.class);
        setupNewEntity(new Coordinates(9, 1), Basket.class);
        setupNewEntity(new Coordinates(0, 10), Basket.class);
        setupNewEntity(new Coordinates(11, 9), Basket.class);
        setupNewEntity(new Coordinates(2, 14), Basket.class);
        setupNewEntity(new Coordinates(11, 15), Basket.class);
    }
}
