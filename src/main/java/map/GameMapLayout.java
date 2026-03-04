package main.java.map;

import main.java.Coordinates;
import main.java.entities.Entity;
import main.java.entities.animate.Cat;
import main.java.entities.animate.Mouse;
import main.java.entities.inanimate.Basket;
import main.java.entities.inanimate.Box;
import main.java.entities.inanimate.Cheese;
import main.java.entities.inanimate.Yarn;

public class GameMapLayout {
    GameMap gameMap;

    public GameMapLayout(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void setupStartMap() {
        setupStartResources();
        setupStartCreatures();
        setupObstacles();
    }

    public void setupNewEntity(Coordinates coordinates, String typeOfEntity) {
        gameMap.setEntity(coordinates, createEntity(coordinates, typeOfEntity));
    }

    public Entity createEntity(Coordinates coordinates, String typeOfEntity) {
        return switch (typeOfEntity) {
            case "Box" -> new Box();
            case "Yarn" -> new Yarn();
            case "Basket" -> new Basket();
            case "Cheese" -> new Cheese();
            case "Cat" -> new Cat(10, 3, "Mouse", coordinates, 2);
            case "Mouse" -> new Mouse(6, 2, "Cheese", coordinates);
            default -> null;
        };
    }

    public void setupStartCreatures() {
        setupNewEntity(new Coordinates(10, 14), "Cat");
        setupNewEntity(new Coordinates(10, 13), "Cat");
        setupNewEntity(new Coordinates(9, 14), "Cat");

        setupNewEntity(new Coordinates(1, 1), "Mouse");
        setupNewEntity(new Coordinates(2, 0), "Mouse");
        setupNewEntity(new Coordinates(1, 2), "Mouse");
        setupNewEntity(new Coordinates(2, 1), "Mouse");
        setupNewEntity(new Coordinates(0, 2), "Mouse");
    }

    public void setupStartResources() {
        setupNewEntity(new Coordinates(4, 2), "Cheese");
        setupNewEntity(new Coordinates(8, 7), "Cheese");
        setupNewEntity(new Coordinates(4, 2), "Cheese");
        setupNewEntity(new Coordinates(5, 10), "Cheese");
        setupNewEntity(new Coordinates(6, 13), "Cheese");
        setupNewEntity(new Coordinates(2, 7), "Cheese");
        setupNewEntity(new Coordinates(11, 1), "Cheese");
    }

    public void setupObstacles() {
        setupNewEntity(new Coordinates(3, 1), "Yarn");
        setupNewEntity(new Coordinates(1, 4), "Yarn");
        setupNewEntity(new Coordinates(7, 9), "Yarn");
        setupNewEntity(new Coordinates(0, 13), "Yarn");
        setupNewEntity(new Coordinates(2, 9), "Yarn");
        setupNewEntity(new Coordinates(7, 0), "Yarn");
        setupNewEntity(new Coordinates(9, 13), "Yarn");
        setupNewEntity(new Coordinates(10, 3), "Yarn");

        setupNewEntity(new Coordinates(3, 4), "Box");
        setupNewEntity(new Coordinates(4, 6), "Box");
        setupNewEntity(new Coordinates(3, 5), "Box");
        setupNewEntity(new Coordinates(4, 5), "Box");
        setupNewEntity(new Coordinates(8, 11), "Box");
        setupNewEntity(new Coordinates(9, 11), "Box");
        setupNewEntity(new Coordinates(9, 10), "Box");
        setupNewEntity(new Coordinates(6, 2), "Box");
        setupNewEntity(new Coordinates(6, 3), "Box");
        setupNewEntity(new Coordinates(10, 5), "Box");
        setupNewEntity(new Coordinates(9, 6), "Box");
        setupNewEntity(new Coordinates(10, 7), "Box");
        setupNewEntity(new Coordinates(3, 11), "Box");
        setupNewEntity(new Coordinates(2, 12), "Box");
        setupNewEntity(new Coordinates(3, 12), "Box");
        setupNewEntity(new Coordinates(7, 14), "Box");
        setupNewEntity(new Coordinates(6, 15), "Box");
        setupNewEntity(new Coordinates(7, 15), "Box");
        setupNewEntity(new Coordinates(8, 15), "Box");

        setupNewEntity(new Coordinates(5, 8), "Basket");
        setupNewEntity(new Coordinates(8, 4), "Basket");
        setupNewEntity(new Coordinates(0, 10), "Basket");
        setupNewEntity(new Coordinates(4, 13), "Basket");
        setupNewEntity(new Coordinates(9, 1), "Basket");
        setupNewEntity(new Coordinates(0, 10), "Basket");
        setupNewEntity(new Coordinates(11, 9), "Basket");
        setupNewEntity(new Coordinates(2, 14), "Basket");
        setupNewEntity(new Coordinates(11, 15), "Basket");
    }
}
