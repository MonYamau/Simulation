package main.java;

import main.java.core.SimulationLauncher;

public class Main {
    public static void main(String[] args) {
        SimulationLauncher simulationLauncher = new SimulationLauncher();
        try {
            simulationLauncher.start();
        } catch (IllegalArgumentException e) {
            System.err.println("incorrect value received");
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Unknown error received");
            System.err.println(e.getMessage());
        }
    }
}