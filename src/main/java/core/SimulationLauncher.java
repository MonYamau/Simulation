package main.java.core;

import main.java.utils.ScriptRenderer;

import java.util.Scanner;

public class SimulationLauncher {
    public final static Scanner SCANNER = new Scanner(System.in);
    public final static String MOTION = "Х";
    public final static String START = "Н";
    public final static String PAUSE = "П";
    public final static String EXIT = "В";

    public void startSimulationLauncher() {
        Simulation simulation = new Simulation();
        simulation.initSimulation();
        startGameLoop(simulation);
    }

    public void startGameLoop(Simulation simulation) {
        while (true) {
            switch (SCANNER.nextLine().toUpperCase()) {
                case MOTION:
                    simulation.nextTurn();
                    break;
                case START:
                    simulation.startSimulation();
                    break;
                case PAUSE:
                    simulation.pauseSimulation();
                    break;
                case EXIT:
                    simulation.stopSimulation();
                    return;
                default:
                    ScriptRenderer.printIncorrectInputScript();
            }
        }
    }
}