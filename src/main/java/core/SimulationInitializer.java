package main.java.core;

import main.java.gamemap.GameMap;
import main.java.gamemap.GameMapRenderer;
import main.java.utils.MessagePrinter;

public class SimulationInitializer {
    public static void init(GameMap gameMap, ActionManager actionManager, GameMapRenderer gameMapRenderer) {
        MessagePrinter.printWelcomeMessages();
        actionManager.executeInitActions();
        gameMapRenderer.render(gameMap);
    }
}