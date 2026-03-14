package main.java.core;

import main.java.action.ActionManager;
import main.java.map.GameMap;
import main.java.map.GameMapRenderer;

public class Simulation {
    TurnExecutor turnExecutor;
    SimulationThreadManager simulationThreadManager;

    public Simulation(GameMap gameMap, GameMapRenderer gameMapRenderer, ActionManager actionManager) {
        this.turnExecutor = new TurnExecutor(gameMap, actionManager, gameMapRenderer);
        this.simulationThreadManager = new SimulationThreadManager(turnExecutor);
        SimulationInitializer.init(gameMap, actionManager, gameMapRenderer);
    }

    public void executeTurn() {
        turnExecutor.nextTurn();
    }

    public void startSimulation() {
        simulationThreadManager.start();
    }

    public void pauseSimulation() {
        simulationThreadManager.pause();
    }

    public void stopSimulation() {
        simulationThreadManager.stop();
    }
}