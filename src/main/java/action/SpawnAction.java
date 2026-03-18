package main.java.action;

import main.java.entity.EntityFactory;
import main.java.gamemap.GameMap;

public abstract class SpawnAction extends Action {
    protected static final int DEFAULT_CAT_COUNT = 3;
    protected static final int DEFAULT_MOUSE_COUNT = 5;
    protected static final int DEFAULT_CHEESE_COUNT = 7;
    protected static final int DEFAULT_BOX_COUNT = 23;
    protected static final int DEFAULT_BASKET_COUNT = 15;

    protected EntityFactory entityFactory;

    public SpawnAction(GameMap gameMap, EntityFactory entityFactory) {
        super(gameMap);
        this.entityFactory = entityFactory;
    }
}