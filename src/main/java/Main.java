package main.java;

import main.java.core.Simulation;
import main.java.core.SimulationFactory;
import main.java.core.SimulationLauncher;

public class Main {
    public static void main(String[] args) {
        SimulationFactory simulationFactory = new SimulationFactory();
        try {
            Simulation simulation = simulationFactory.create();
            SimulationLauncher simulationLauncher = new SimulationLauncher(simulation);

        } catch (IllegalArgumentException e) {
            System.err.println("incorrect value received");
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Unknown error received");
            System.err.println(e.getMessage());
        }
    }
}