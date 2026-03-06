package main.java;

import main.java.map.GameMap;
import main.java.map.GameMapRenderer;

public class Simulation {
    public static int counter = 0;
    GameMap gameMap = new GameMap();
    GameMapRenderer gameMapRenderer = new GameMapRenderer(gameMap);
    Actions actions = new Actions(gameMap);
    private volatile boolean isRunning = false;
    private volatile boolean isPaused = true;
    private Thread simulationThread;

    public void initSimulation() {
        ScriptRenderer.clearScreen();
        ScriptRenderer.printWelcomeScript();
        ScriptRenderer.printInstructionScript();
        ScriptRenderer.printCounter(counter);
        actions.initActions();
        gameMapRenderer.printMapSimulation();
    }

    public void nextTurn() {
        ScriptRenderer.clearScreen();
        ScriptRenderer.printInstructionScript();
        ScriptRenderer.printCounter(++counter);
        actions.turnActions();
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
                simulationThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}