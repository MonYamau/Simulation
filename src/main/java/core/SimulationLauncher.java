package main.java.core;

import main.java.action.ActionManager;
import main.java.entity.EntityFactory;
import main.java.map.GameMap;
import main.java.utils.ScriptRenderer;

import java.util.Scanner;

public class SimulationLauncher {
    public final static String MOTION = "Х";
    public final static String START = "Н";
    public final static String PAUSE = "П";
    public final static String EXIT = "В";

    public void start() {
        GameMap gameMap = new GameMap();
        EntityFactory entityFactory = new EntityFactory();
        ActionManager actionManager = new ActionManager(gameMap, entityFactory);
        Simulation simulation = new Simulation(gameMap, actionManager);
        startGameLoop(simulation);
    }

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
                    ScriptRenderer.printIncorrectInputScript();
            }
        }
    }
}