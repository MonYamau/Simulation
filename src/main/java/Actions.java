package main.java;

import main.java.entities.Entity;
import main.java.entities.animate.Creature;
import main.java.entities.animate.Mouse;
import main.java.entities.inanimate.Cheese;
import main.java.map.GameMap;
import main.java.map.GameMapLayout;

import java.util.List;

public class Actions {
    GameMap gameMap;

    public Actions(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public final static int MIN_NUM_OF_ENTITY = 3;

    public void initActions() {
        GameMapLayout gameMapLayout = new GameMapLayout(gameMap);
        gameMapLayout.setupStartMap();
    }

    public void turnActions() {
        GameMapLayout gameMapLayout = new GameMapLayout(gameMap);
        List<Creature> creatures = gameMap.getEntitiesOfAnyType(Creature.class);
        addResources(gameMapLayout);
        for (Creature creature : creatures) {
            creature.makeMove(gameMap);
        }
    }

    private void addResources(GameMapLayout gameMapLayout) {
        if (isSmallAmountOfEntity(Cheese.class)) {
            gameMapLayout.setupNewEntity(gameMap.getRandomEmptyCell(), Cheese.class);
        }
        if (isSmallAmountOfEntity(Mouse.class)) {
            gameMapLayout.setupNewEntity(gameMap.getRandomEmptyCell(), Mouse.class);
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
