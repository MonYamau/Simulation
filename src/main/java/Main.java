package main.java;

import main.java.core.SimulationLauncher;

public class Main {
    public static void main(String[] args) {
        SimulationLauncher simulationLauncher = new SimulationLauncher();
        try {
            simulationLauncher.startSimulationLauncher();
        } catch (IllegalArgumentException e) {
            System.err.println("Получено некорректное значение");
            System.err.println(e.getMessage());
        }

    }
}