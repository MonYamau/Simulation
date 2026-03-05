package main.java;

import main.java.entities.Entity;
import main.java.entities.animate.Creature;
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
        List<Creature> creatures = gameMap.getAllCreatures();
        addResources(gameMapLayout);
        for (Creature creature : creatures) {
            creature.makeMove(gameMap);
        }
    }

    private void addResources(GameMapLayout gameMapLayout) {
        if (isSmallAmountOfEntity("Cheese")) {
            gameMapLayout.setupNewEntity(gameMap.getRandomEmptyCell(), "Cheese");
        }
        if (isSmallAmountOfEntity("Mouse")) {
            gameMapLayout.setupNewEntity(gameMap.getRandomEmptyCell(), "Mouse");
        }
    }

    private boolean isSmallAmountOfEntity(String typeOfEntity) {
        int entityCounter = 0;
        for (Entity entity : gameMap.getAllEntities()) {
            if (entity.getClass().getSimpleName().equals(typeOfEntity)) {
                entityCounter++;
            }
        }
        return entityCounter < MIN_NUM_OF_ENTITY;
    }
}
