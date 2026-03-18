package main.java.action;

import main.java.core.EntityFactory;
import main.java.entity.Entity;
import main.java.entity.creature.Cat;
import main.java.entity.creature.Mouse;
import main.java.entity.staticobject.Basket;
import main.java.entity.staticobject.Box;
import main.java.entity.staticobject.Cheese;
import main.java.gamemap.GameMap;
import main.java.utils.EntitySpawner;

public class GameMapInitializer extends SpawnAction {

    public GameMapInitializer(GameMap gameMap, EntityFactory entityFactory) {
        super(gameMap, entityFactory);
    }

    @Override
    public void perform() {
        initGameMap();
    }

    private void initGameMap() {
        spawnEntities(Cat.class, DEFAULT_CAT_COUNT);
        spawnEntities(Mouse.class, DEFAULT_MOUSE_COUNT);
        spawnEntities(Cheese.class, DEFAULT_CHEESE_COUNT);
        spawnEntities(Box.class, DEFAULT_BOX_COUNT);
        spawnEntities(Basket.class, DEFAULT_BASKET_COUNT);
    }

    private <T extends Entity> void spawnEntities(Class<T> entityClass, int entityCount) {
        for (int i = 0; i < entityCount; i++) {
            EntitySpawner.spawnEntity(entityClass, entityFactory, gameMap);
        }
    }
}
