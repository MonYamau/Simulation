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
    List<Action> initActions;
    List<Action> turnActions;

    public Simulation create() {
        List<Action> initActions = new ArrayList<>();
        List<Action> turnActions = new ArrayList<>();
        PathFinder bfsPathFinder = new BfsPathFinder();
        EntityFactory entityFactory = new EntityFactory(bfsPathFinder);
        GameMap gameMap = new GameMap(DEFAULT_MAX_COLUMN_VALUE, DEFAULT_MAX_ROW_VALUE);
        GameMapRenderer gameMapRenderer = new GameMapRenderer();
        fillInitActions(gameMap, entityFactory);
        fillTurnActions(gameMap, entityFactory);
        return new Simulation(gameMap, gameMapRenderer, initActions, turnActions);
    }

    private void fillInitActions(GameMap gameMap, EntityFactory entityFactory) {
        initActions.add(new GameMapInitializer(gameMap, entityFactory));
    }

    private void fillTurnActions(GameMap gameMap, EntityFactory entityFactory) {
        turnActions.add(new ResourceProvider(gameMap, entityFactory));
        turnActions.add(new TurnMovement(gameMap));
    }
}
