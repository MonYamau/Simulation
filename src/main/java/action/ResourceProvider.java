package main.java.action;

import main.java.core.EntityFactory;
import main.java.entity.Entity;
import main.java.entity.creature.Mouse;
import main.java.entity.staticobject.Cheese;
import main.java.gamemap.GameMap;
import main.java.utils.EntitySpawner;

public class ResourceProvider extends SpawnAction {
    public ResourceProvider(EntityFactory entityFactory) {
        super(entityFactory);
    }

    @Override
    public void perform(GameMap gameMap) {
        replenishEntity(Mouse.class, DEFAULT_MOUSE_COUNT, gameMap);
        replenishEntity(Cheese.class, DEFAULT_CHEESE_COUNT, gameMap);
    }

    private <T extends Entity> void replenishEntity(Class<T> entity, int minNumOfEntity, GameMap gameMap) {
        int amount = countAmountOfEntity(entity, gameMap);
        for (int i = amount; i < minNumOfEntity; i++) {
            EntitySpawner.spawnEntity(entity, entityFactory, gameMap);
        }
    }

    private <T extends Entity> int countAmountOfEntity(Class<T> entityClass, GameMap gameMap) {
        int entityCounter = 0;
        for (Entity entity : gameMap.getEntitiesByType(Entity.class)) {
            if (entityClass.isInstance(entity)) {
                entityCounter++;
            }
        }
        return entityCounter;
    }
}
