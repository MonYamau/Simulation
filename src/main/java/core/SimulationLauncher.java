package main.java.core;

import main.java.utils.MessagePrinter;

import java.util.Scanner;

public class SimulationLauncher {
    public static final String MOTION = "Х";
    public static final String START = "Н";
    public static final String PAUSE = "П";
    public static final String EXIT = "В";

    public void startGameLoop(Simulation simulation) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            switch (scanner.nextLine().toUpperCase()) {
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
                    scanner.close();
                    return;
                default:
                    MessagePrinter.printIncorrectInputScript();
            }
        }
    }
}