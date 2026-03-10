package main.java.core;

import main.java.entity.EntityFactory;
import main.java.map.GameMap;
import main.java.map.GameMapRenderer;
import main.java.utils.ScriptRenderer;

import java.util.Scanner;

public class SimulationLauncher {
    public final static Scanner SCANNER = new Scanner(System.in);
    public final static String MOTION = "Х";
    public final static String START = "Н";
    public final static String PAUSE = "П";
    public final static String EXIT = "В";

    GameMap gameMap;
    GameMapRenderer gameMapRenderer;
    EntityFactory entityFactory;

    public SimulationLauncher() {
        this.gameMap = new GameMap();
        this.gameMapRenderer = new GameMapRenderer(gameMap);
        this.entityFactory = new EntityFactory();
    }

    public void start() {
        Simulation simulation = new Simulation(gameMap, gameMapRenderer, entityFactory);
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