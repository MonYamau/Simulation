package main.java.core;

import main.java.entity.Cheese;
import main.java.entity.Creature;
import main.java.entity.Mouse;
import main.java.map.GameMap;
import main.java.map.GameMapLayout;

import java.util.List;

public class Actions {
    GameMap gameMap;
    GameMapLayout gameMapLayout;
    ResourceProvider resourceProvider;

    public Actions(GameMap gameMap, GameMapLayout gameMapLayout, ResourceProvider resourceProvider) {
        this.gameMap = gameMap;
        this.gameMapLayout = gameMapLayout;
        this.resourceProvider = resourceProvider;
    }

    public void initActions() {
        gameMapLayout.setupStartMap();
    }

    public void turnActions() {
        resourceProvider.replenishEntity(Mouse.class);
        resourceProvider.replenishEntity(Cheese.class);
        List<Creature> creatures = gameMap.getEntitiesOfAnyType(Creature.class);
        for (Creature creature : creatures) {
            creature.makeMove(gameMap);
        }
    }
}
