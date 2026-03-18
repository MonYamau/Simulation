package main.java.core;

import main.java.action.Action;
import main.java.action.GameMapInitializer;
import main.java.action.ResourceProvider;
import main.java.action.TurnMovement;
import main.java.gamemap.GameMap;
import main.java.gamemap.GameMapRenderer;
import main.java.utils.EntitySpawner;
import main.java.utils.MessagePrinter;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    public static final int THREAD_SLEEP = 1500;

    private final List<Action> initActions;
    private final List<Action> turnActions;
    private int counter;
    private volatile boolean isRunning;
    private volatile boolean isPaused;
    private Thread simulationThread;
    private final GameMap gameMap;
    private final GameMapRenderer gameMapRenderer;

    public Simulation(GameMap gameMap, GameMapRenderer gameMapRenderer, List<Action> initActions, List<Action> turnActions) {
        isRunning = false;
        isPaused = true;
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

    public synchronized void startSimulation() {
        if (simulationThread != null && simulationThread.isAlive()) {
            isPaused = false;
            notifyAll();
            return;
        }
        isRunning = true;
        isPaused = false;
        simulationThread = getThread();
        simulationThread.start();
    }

    public synchronized void pauseSimulation() {
        isPaused = true;
        notifyAll();
    }

    public synchronized void stopSimulation() {
        isRunning = false;
        isPaused = false;
        notifyAll();
        if (simulationThread != null) {
            try {
                simulationThread.join(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @SuppressWarnings("BusyWait")
    private Thread getThread() {
        return new Thread(() -> {
            while (isRunning) {
                synchronized (this) {
                    while (isPaused) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            isRunning = false;
                            break;
                        }
                    }
                }
                if (!isRunning) break;
                try {
                    nextTurn();
                } catch (Exception e) {
                    System.err.println("Error was received when executing the flow: " + e.getMessage());
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
        });
    }

    private void executeInitActions() {
        executeActions(initActions);
    }

    private void executeTurnActions() {
        executeActions(turnActions);
    }

    private void executeActions(List<Action> actions) {
        for (Action action: actions) {
            action.perform();
        }
    }

    private void init() {
        MessagePrinter.printWelcomeMessages();
        executeInitActions();
        gameMapRenderer.render(gameMap);
    }
}