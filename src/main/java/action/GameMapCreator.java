package main.java.action;

import main.java.entity.*;
import main.java.entity.creature.Cat;
import main.java.entity.creature.Mouse;
import main.java.map.GameMap;
import main.java.utils.EntitySpawner;

public class GameMapCreator extends CreatingAction {
    private final static int QUANTITY_CAT = 3;
    private final static int QUANTITY_MOUSE = 5;
    private final static int QUANTITY_CHEESE = 7;
    private final static int QUANTITY_BOX = 25;
    private final static int QUANTITY_BASKET = 20;

    public GameMapCreator(GameMap gameMap, EntityFactory entityFactory) {
        super(gameMap, entityFactory);
    }

    @Override
    public void perform() {
        setupStartMap();
    }

    private void setupStartMap() {
        setupStartEntities(Cat.class, QUANTITY_CAT);
        setupStartEntities(Mouse.class, QUANTITY_MOUSE);
        setupStartEntities(Cheese.class, QUANTITY_CHEESE);
        setupStartEntities(Box.class, QUANTITY_BOX);
        setupStartEntities(Basket.class, QUANTITY_BASKET);
    }

    private <T extends Entity> void setupStartEntities(Class<T> entityClass, int quantityOfEntity) {
        for (int i = 0; i < quantityOfEntity; i++) {
            EntitySpawner.spawnEntity(entityClass, entityFactory, gameMap);
        }
    }
}
