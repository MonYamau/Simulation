package main.java.core;

import main.java.action.ActionManager;
import main.java.entity.EntityFactory;
import main.java.map.GameMap;
import main.java.map.GameMapRenderer;
import main.java.service.BfsPathFinder;
import main.java.service.PathFinder;


public class SimulationFactory {
    private static final int DEFAULT_MAX_COLUMN_VALUE = 14;
    private static final int DEFAULT_MAX_ROW_VALUE = 16;

    public Simulation create() {
        PathFinder bfsPathFinder = new BfsPathFinder();
        EntityFactory entityFactory = new EntityFactory(bfsPathFinder);
        GameMap gameMap = new GameMap(DEFAULT_MAX_COLUMN_VALUE, DEFAULT_MAX_ROW_VALUE);
        GameMapRenderer gameMapRenderer = new GameMapRenderer(DEFAULT_MAX_COLUMN_VALUE, DEFAULT_MAX_ROW_VALUE);
        ActionManager actionManager = new ActionManager(gameMap, entityFactory);
        return new Simulation(gameMap, gameMapRenderer, actionManager);
    }
}
