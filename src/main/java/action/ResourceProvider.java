package main.java.action;

import main.java.entity.Cheese;
import main.java.entity.Entity;
import main.java.entity.EntityFactory;
import main.java.entity.creature.Mouse;
import main.java.map.GameMap;
import main.java.utils.EntitySpawner;

public class ResourceProvider extends CreatingAction {
    public final static int MIN_NUM_OF_CHEESE = 7;
    public final static int MIN_NUM_OF_MOUSE = 5;

    public ResourceProvider(GameMap gameMap, EntityFactory entityFactory) {
        super(gameMap, entityFactory);
    }

    @Override
    public void perform() {
        replenishEntity(Mouse.class, MIN_NUM_OF_MOUSE);
        replenishEntity(Cheese.class, MIN_NUM_OF_CHEESE);
    }

    private <T extends Entity> void replenishEntity(Class<T> entity, int minNumOfEntity) {
        int amount = countAmountOfEntity(entity);
        for (int i = amount; i < minNumOfEntity; i++) {
            EntitySpawner.setupNewEntity(entity, entityFactory, gameMap);
        }
    }

    private <T extends Entity> int countAmountOfEntity(Class<T> entityClass) {
        int entityCounter = 0;
        for (Entity entity : gameMap.getEntitiesOfAnyType(Entity.class)) {
            if (entityClass.isInstance(entity)) {
                entityCounter++;
            }
        }
        return entityCounter;
    }
}
