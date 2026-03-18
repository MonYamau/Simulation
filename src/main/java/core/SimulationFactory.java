package main.java.core;

import main.java.gamemap.GameMap;
import main.java.gamemap.GameMapRenderer;
import main.java.movement.BfsPathFinder;
import main.java.movement.PathFinder;


public class SimulationFactory {
    private static final int DEFAULT_MAX_COLUMN_VALUE = 14;
    private static final int DEFAULT_MAX_ROW_VALUE = 16;

    public Simulation create() {
        PathFinder bfsPathFinder = new BfsPathFinder();
        EntityFactory entityFactory = new EntityFactory(bfsPathFinder);
        GameMap gameMap = new GameMap(DEFAULT_MAX_COLUMN_VALUE, DEFAULT_MAX_ROW_VALUE);
        GameMapRenderer gameMapRenderer = new GameMapRenderer();
        return new Simulation(gameMap, gameMapRenderer, entityFactory);
    }
}
