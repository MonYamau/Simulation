package main.java.core;

import main.java.action.Action;
import main.java.action.ActionManager;
import main.java.entity.EntityFactory;
import main.java.map.GameMap;
import main.java.map.GameMapRenderer;
import main.java.utils.ScriptRenderer;

public class Simulation {
    private static int counter;
    private final GameMapRenderer gameMapRenderer;
    private final ActionManager actionManager;
    private volatile boolean isRunning = false;
    private volatile boolean isPaused = true;
    private Thread simulationThread;

    public Simulation(GameMap gameMap, GameMapRenderer gameMapRenderer, EntityFactory entityFactory) {
        counter = 0;
        this.gameMapRenderer = gameMapRenderer;
        this.actionManager = new ActionManager(gameMap, entityFactory);
    }

    public void initSimulation() {
        ScriptRenderer.clearScreen();
        ScriptRenderer.printWelcomeScript();
        ScriptRenderer.printInstructionScript();
        ScriptRenderer.printCounter(counter);
        for (Action action : actionManager.getInitActions()) {
            action.perform();
        }
        gameMapRenderer.printMapSimulation();
    }

    public void nextTurn() {
        ScriptRenderer.clearScreen();
        ScriptRenderer.printInstructionScript();
        ScriptRenderer.printCounter(++counter);
        for (Action action : actionManager.getTurnActions()) {
            action.perform();
        }
        gameMapRenderer.printMapSimulation();
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

    @SuppressWarnings("BusyWait")
    public Thread getThread() {
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
                nextTurn();
                try {
                    Thread.sleep(1800);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    isRunning = false;
                    break;
                }
            }
        });
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
}