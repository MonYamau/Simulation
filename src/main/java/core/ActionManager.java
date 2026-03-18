package main.java.core;

import main.java.action.Action;
import main.java.action.GameMapInitializer;
import main.java.action.ResourceProvider;
import main.java.action.TurnMovement;
import main.java.gamemap.GameMap;

import java.util.ArrayList;
import java.util.List;

public class ActionManager {
    private final List<Action> initActions;
    private final List<Action> turnActions;
    private final GameMap gameMap;
    private final EntityFactory entityFactory;

    public ActionManager(GameMap gameMap, EntityFactory entityFactory) {
        this.gameMap = gameMap;
        this.entityFactory = entityFactory;
        this.initActions = new ArrayList<>();
        this.turnActions = new ArrayList<>();
        fillInitActions();
        fillTurnActions();
    }

    public void executeInitActions() {
        executeActions(initActions);
    }

    public void executeTurnActions() {
        executeActions(turnActions);
    }

    private void executeActions(List<Action> actions) {
        for (Action action: actions) {
            action.perform();
        }
    }

    private void fillInitActions() {
        initActions.add(new GameMapInitializer(gameMap, entityFactory));
    }

    private void fillTurnActions() {
        turnActions.add(new ResourceProvider(gameMap, entityFactory));
        turnActions.add(new TurnMovement(gameMap));
    }
}
