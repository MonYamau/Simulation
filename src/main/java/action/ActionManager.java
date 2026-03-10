package main.java.action;

import main.java.entity.EntityFactory;
import main.java.map.GameMap;

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

    public List<Action> getInitActions() {
        return initActions;
    }

    public List<Action> getTurnActions() {
        return turnActions;
    }

    private void fillInitActions() {
        initActions.add(new GameMapCreator(gameMap, entityFactory));
    }

    private void fillTurnActions() {
        turnActions.add(new ResourceProvider(gameMap, entityFactory));
        turnActions.add(new TurnMovement(gameMap));
    }
}
