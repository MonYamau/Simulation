package main.java.core;

import main.java.map.GameMap;
import main.java.map.GameMapRenderer;
import main.java.utils.ScriptRenderer;

public class SimulationInitializer {
    public static void init(GameMap gameMap, ActionManager actionManager, GameMapRenderer gameMapRenderer) {
        ScriptRenderer.printWelcomeMessages();
        actionManager.executeInitActions();
        gameMapRenderer.printGameMap(gameMap);
    }
}