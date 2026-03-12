package main.java.core;

public class SimulationThreadManager {
    public static final int THREAD_SLEEP = 1500;

    private final TurnExecutor turnExecutor;
    private volatile boolean isRunning;
    private volatile boolean isPaused;
    private Thread simulationThread;

    public SimulationThreadManager(TurnExecutor turnExecutor) {
        isRunning = false;
        isPaused = true;
        this.turnExecutor = turnExecutor;
    }

    public synchronized void start() {
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

    public synchronized void pause() {
        isPaused = true;
        notifyAll();
    }

    public synchronized void stop() {
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
