package main.java.core;

import main.java.action.Action;
import main.java.gamemap.GameMap;
import main.java.gamemap.GameMapRenderer;
import main.java.utils.MessagePrinter;

import java.util.List;

public class Simulation {
    public static final int THREAD_SLEEP = 1500;

    private final List<Action> initActions;
    private final List<Action> turnActions;
    private final GameMap gameMap;
    private final GameMapRenderer gameMapRenderer;
    private int counter;
    private volatile boolean isRunning;

    public Simulation(GameMap gameMap, GameMapRenderer gameMapRenderer, List<Action> initActions, List<Action> turnActions) {
        isRunning = false;
        this.initActions = initActions;
        this.turnActions = turnActions;
        this.gameMap = gameMap;
        this.gameMapRenderer = gameMapRenderer;
        this.counter = 0;
        init();
    }

    public void nextTurn() {
        MessagePrinter.printTurnMessages(++counter);
        executeTurnActions();
        gameMapRenderer.render(gameMap);
    }

    @SuppressWarnings("BusyWait")
    public void startSimulation() {
        isRunning = true;
        while (isRunning) {
            try {
                nextTurn();
            } catch (Exception e) {
                System.err.println("Error received: " + e.getMessage());
                isRunning = false;
                break;
            }
            try {
                Thread.sleep(THREAD_SLEEP);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                isRunning = false;
                break;
            }
        }
    }

    public void pauseSimulation() {
        isRunning = false;
    }

    private void executeInitActions() {
        executeActions(initActions);
    }

    private void executeTurnActions() {
        executeActions(turnActions);
    }

    private void executeActions(List<Action> actions) {
        for (Action action : actions) {
            action.perform();
        }
    }

    private void init() {
        MessagePrinter.printWelcomeMessages();
        executeInitActions();
        gameMapRenderer.render(gameMap);
    }
}