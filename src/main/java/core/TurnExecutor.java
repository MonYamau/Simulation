package main.java.core;

import main.java.gamemap.GameMap;
import main.java.gamemap.GameMapRenderer;
import main.java.utils.MessagePrinter;

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
        MessagePrinter.printTurnMessages(++counter);
        actionManager.executeTurnActions();
        gameMapRenderer.render(gameMap);
    }
}
