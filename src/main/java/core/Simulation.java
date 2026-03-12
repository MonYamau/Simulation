package main.java.core;

import main.java.action.ActionManager;
import main.java.map.GameMap;

import static main.java.utils.SimulationConstants.THREAD_SLEEP;

public class Simulation {
    private volatile boolean isRunning;
    private volatile boolean isPaused;
    private Thread simulationThread;
    private final TurnExecutor turnExecutor;

    public Simulation(GameMap gameMap, ActionManager actionManager) {
        isRunning = false;
        isPaused = true;
        this.turnExecutor = new TurnExecutor(gameMap, actionManager);
        SimulationCreator.init(gameMap, actionManager);
    }

    public void executeTurn(){
        turnExecutor.nextTurn();
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
                    turnExecutor.nextTurn();
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
}