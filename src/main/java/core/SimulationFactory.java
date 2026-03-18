package main.java.core;

import main.java.action.Action;
import main.java.action.GameMapInitializer;
import main.java.action.ResourceProvider;
import main.java.action.TurnMovement;
import main.java.gamemap.GameMap;
import main.java.gamemap.GameMapRenderer;
import main.java.movement.BfsPathFinder;
import main.java.movement.PathFinder;

import java.util.ArrayList;
import java.util.List;


public class SimulationFactory {
    private static final int DEFAULT_MAX_COLUMN_VALUE = 14;
    private static final int DEFAULT_MAX_ROW_VALUE = 16;

    public Simulation create() {
        PathFinder bfsPathFinder = new BfsPathFinder();
        EntityFactory entityFactory = new EntityFactory(bfsPathFinder);
        GameMap gameMap = new GameMap(DEFAULT_MAX_COLUMN_VALUE, DEFAULT_MAX_ROW_VALUE);
        GameMapRenderer gameMapRenderer = new GameMapRenderer();
        List<Action> initActions = getInitActions(gameMap, entityFactory);
        List<Action> turnActions = getTurnActions(gameMap, entityFactory);
        return new Simulation(gameMap, gameMapRenderer, initActions, turnActions);
    }

    private List<Action> getInitActions(GameMap gameMap, EntityFactory entityFactory) {
        List<Action> initActions = new ArrayList<>();
        initActions.add(new GameMapInitializer(gameMap, entityFactory));
        return initActions;
    }

    private List<Action> getTurnActions(GameMap gameMap, EntityFactory entityFactory) {
        List<Action> turnActions = new ArrayList<>();
        turnActions.add(new ResourceProvider(gameMap, entityFactory));
        turnActions.add(new TurnMovement(gameMap));
        return turnActions;
    }
}
