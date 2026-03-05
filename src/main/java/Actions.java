package main.java;

import main.java.entities.Entity;
import main.java.entities.animate.Creature;
import main.java.entities.animate.Mouse;
import main.java.entities.inanimate.Cheese;
import main.java.map.GameMap;
import main.java.map.GameMapLayout;

import java.util.List;

public class Actions {
    public final static int MIN_NUM_OF_ENTITY = 3;

    GameMap gameMap;
    GameMapLayout gameMapLayout;

    public Actions(GameMap gameMap) {
        this.gameMap = gameMap;
        this.gameMapLayout = new GameMapLayout(gameMap);
    }

    public void initActions() {
        gameMapLayout.setupStartMap();
    }

    public void turnActions() {
        replenishFood(Mouse.class);
        replenishFood(Cheese.class);
        List<Creature> creatures = gameMap.getEntitiesOfAnyType(Creature.class);
        for (Creature creature : creatures) {
            creature.makeMove(gameMap);
        }
    }

    private <T extends Entity> void replenishFood(Class<T> entity) {
        if (isSmallAmountOfEntity(entity)) {
            Coordinates randomCell = gameMap.getRandomEmptyCell();
            gameMapLayout.setupNewEntity(randomCell, entity);
        }
    }

    private <T extends Entity> boolean isSmallAmountOfEntity(Class<T> entityClass) {
        int entityCounter = 0;
        for (Entity entity : gameMap.getAllEntities()) {
            if (entityClass.isInstance(entity)) {
                entityCounter++;
            }
        }
        return entityCounter < MIN_NUM_OF_ENTITY;
    }
}
