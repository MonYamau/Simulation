package main.java.action;

import main.java.entity.*;
import main.java.entity.creature.Cat;
import main.java.entity.creature.Mouse;
import main.java.map.GameMap;
import main.java.utils.EntitySpawner;

public class GameMapCreator extends SpawnAction {

    public GameMapCreator(GameMap gameMap, EntityFactory entityFactory) {
        super(gameMap, entityFactory);
    }

    @Override
    public void perform() {
        initGameMap();
    }

    private void initGameMap() {
        createEntities(Cat.class, DEFAULT_CAT_COUNT);
        createEntities(Mouse.class, DEFAULT_MOUSE_COUNT);
        createEntities(Cheese.class, DEFAULT_CHEESE_COUNT);
        createEntities(Box.class, DEFAULT_BOX_COUNT);
        createEntities(Basket.class, DEFAULT_BASKET_COUNT);
    }

    private <T extends Entity> void createEntities(Class<T> entityClass, int entityCount) {
        for (int i = 0; i < entityCount; i++) {
            EntitySpawner.spawnEntity(entityClass, entityFactory, gameMap);
        }
    }
}
