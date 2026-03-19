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

    public GameMapInitializer(EntityFactory entityFactory) {
        super(entityFactory);
    }

    @Override
    public void perform(GameMap gameMap) {
        initGameMap(gameMap);
    }

    private void initGameMap(GameMap gameMap) {
        spawnEntities(Cat.class, DEFAULT_CAT_COUNT, gameMap);
        spawnEntities(Mouse.class, DEFAULT_MOUSE_COUNT, gameMap);
        spawnEntities(Cheese.class, DEFAULT_CHEESE_COUNT, gameMap);
        spawnEntities(Box.class, DEFAULT_BOX_COUNT, gameMap);
        spawnEntities(Basket.class, DEFAULT_BASKET_COUNT, gameMap);
    }

    private <T extends Entity> void spawnEntities(Class<T> entityClass, int entityCount, GameMap gameMap) {
        for (int i = 0; i < entityCount; i++) {
            EntitySpawner.spawnEntity(entityClass, entityFactory, gameMap);
        }
    }
}
