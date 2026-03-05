package main.java;

import java.util.Scanner;

public class SimulationLauncher {
    public final static Scanner SCANNER = new Scanner(System.in);
    public final static String MOTION = "Х";
    public final static String START = "Н";
    public final static String PAUSE = "П";
    public final static String EXIT = "В";

    public void startSimulationLauncher(){
        ScriptRenderer scriptRenderer = new ScriptRenderer();
        Simulation simulation = new Simulation();
        scriptRenderer.printWelcomeScript();
        scriptRenderer.printInstructionScript();
        simulation.initSimulation();
        startGameLoop(simulation, scriptRenderer);
    }

    public void startGameLoop(Simulation simulation, ScriptRenderer scriptRenderer){
        while (true) switch (SCANNER.nextLine().toUpperCase()) {
            case MOTION:
                simulation.nextTurn(scriptRenderer);
                break;
            case START:
                simulation.startSimulation(scriptRenderer);
                break;
            case PAUSE:
                simulation.pauseSimulation(scriptRenderer);
                break;
            case EXIT:
                return;
            default:
                scriptRenderer.printIncorrectInputScript();
        }
    }
}
