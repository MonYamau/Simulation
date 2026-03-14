package main.java.core;

import main.java.action.ActionManager;
import main.java.map.GameMap;
import main.java.map.GameMapRenderer;
import main.java.utils.ScriptRenderer;

public class TurnExecutor {
    private final GameMap gameMap;
    private final ActionManager actionManager;
    private final GameMapRenderer gameMapRenderer;
    private int counter;

    public TurnExecutor(GameMap gameMap, ActionManager actionManager, GameMapRenderer gameMapRenderer) {
        this.counter = 0;
        this.gameMap = gameMap;
        this.actionManager = actionManager;
        this.gameMapRenderer = gameMapRenderer;
    }

    public void nextTurn() {
        ScriptRenderer.printTurnMessages(++counter);
        actionManager.executeTurnActions();
        gameMapRenderer.printGameMap(gameMap);
    }
}
