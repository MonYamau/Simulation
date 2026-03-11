package main.java.core;

import main.java.action.Action;
import main.java.action.ActionManager;
import main.java.map.GameMap;
import main.java.map.GameMapRenderer;
import main.java.utils.ScriptRenderer;

public class SimulationCreator {

    public static void init(GameMap gameMap, ActionManager actionManager) {
        ScriptRenderer.clearScreen();
        ScriptRenderer.printWelcomeScript();
        ScriptRenderer.printInstructionScript();
        for (Action action : actionManager.getInitActions()) {
            action.perform();
        }
        GameMapRenderer.printMapSimulation(gameMap);
    }
}
