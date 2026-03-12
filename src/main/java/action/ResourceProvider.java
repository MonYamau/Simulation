package main.java.action;

import main.java.entity.Cheese;
import main.java.entity.Entity;
import main.java.entity.EntityFactory;
import main.java.entity.creature.Mouse;
import main.java.map.GameMap;
import main.java.utils.EntitySpawner;

import static main.java.utils.SimulationConstants.DEFAULT_CHEESE_COUNT;
import static main.java.utils.SimulationConstants.DEFAULT_MOUSE_COUNT;

public class ResourceProvider extends CreatingAction {

    public ResourceProvider(GameMap gameMap, EntityFactory entityFactory) {
        super(gameMap, entityFactory);
    }

    @Override
    public void perform() {
        replenishEntity(Mouse.class, DEFAULT_MOUSE_COUNT);
        replenishEntity(Cheese.class, DEFAULT_CHEESE_COUNT);
    }

    private <T extends Entity> void replenishEntity(Class<T> entity, int minNumOfEntity) {
        int amount = countAmountOfEntity(entity);
        for (int i = amount; i < minNumOfEntity; i++) {
            EntitySpawner.spawnEntity(entity, entityFactory, gameMap);
        }
    }

    private <T extends Entity> int countAmountOfEntity(Class<T> entityClass) {
        int entityCounter = 0;
        for (Entity entity : gameMap.getEntitiesByType(Entity.class)) {
            if (entityClass.isInstance(entity)) {
                entityCounter++;
            }
        }
        return entityCounter;
    }
}
