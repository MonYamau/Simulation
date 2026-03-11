package main.java.action;

import main.java.entity.*;
import main.java.entity.creature.Cat;
import main.java.entity.creature.Mouse;
import main.java.map.GameMap;
import main.java.utils.EntitySpawner;

import static main.java.utils.SimulationConstants.*;

public class GameMapCreator extends CreatingAction {

    public GameMapCreator(GameMap gameMap, EntityFactory entityFactory) {
        super(gameMap, entityFactory);
    }

    @Override
    public void perform() {
        initGameMap();
    }

    private void initGameMap() {
        createEntities(Cat.class, CAT_COUNT);
        createEntities(Mouse.class, MOUSE_COUNT);
        createEntities(Cheese.class, CHEESE_COUNT);
        createEntities(Box.class, BOX_COUNT);
        createEntities(Basket.class, BASKET_COUNT);
    }

    private <T extends Entity> void createEntities(Class<T> entityClass, int entityCount) {
        for (int i = 0; i < entityCount; i++) {
            EntitySpawner.spawnEntity(entityClass, entityFactory, gameMap);
        }
    }
}
