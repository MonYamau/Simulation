package main.java.core;

import main.java.action.ActionManager;
import main.java.map.GameMap;

public class Simulation {
    TurnExecutor turnExecutor;
    SimulationThreadManager simulationThreadManager;

    public Simulation(GameMap gameMap, ActionManager actionManager) {
        this.turnExecutor = new TurnExecutor(gameMap, actionManager);
        this.simulationThreadManager = new SimulationThreadManager(turnExecutor);
        SimulationCreator.init(gameMap, actionManager);
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