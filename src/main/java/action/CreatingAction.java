package main.java.action;

import main.java.entity.EntityFactory;
import main.java.map.GameMap;

public abstract class CreatingAction extends Action {
    protected EntityFactory entityFactory;

    public CreatingAction(GameMap gameMap, EntityFactory entityFactory) {
        super(gameMap);
        this.entityFactory = entityFactory;
    }
}