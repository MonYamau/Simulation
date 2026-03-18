package main.java.core;

import main.java.utils.MessagePrinter;

import java.util.Scanner;

public class SimulationLauncher {
    public static final String MOTION = "Х";
    public static final String START = "Н";
    public static final String PAUSE = "П";
    public static final String EXIT = "В";

    private Thread simulationThread;

    public void startGameLoop(Simulation simulation) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            switch (scanner.nextLine().toUpperCase()) {
                case MOTION:
                    simulation.nextTurn();
                    break;
                case START:
                    startThread(simulation);
                    break;
                case PAUSE:
                    pause(simulation);
                    break;
                case EXIT:
                    stop(simulation);
                    scanner.close();
                    return;
                default:
                    MessagePrinter.printIncorrectInputScript();
            }
        }
    }

    private void startThread(Simulation simulation) {
        if (simulationThread != null && simulationThread.isAlive()) {
            return;
        }

        simulationThread = new Thread(simulation::startSimulation);
        simulationThread.start();
    }

    private void pause(Simulation simulation) {
        simulation.pauseSimulation();
        if (simulationThread != null) {
            simulationThread.interrupt();
        }
    }

    private void stop(Simulation simulation) {
        simulation.pauseSimulation();
        if (simulationThread != null) {
            try {
                simulationThread.join(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}