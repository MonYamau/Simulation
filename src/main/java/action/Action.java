package main.java.action;

import main.java.gamemap.GameMap;

public abstract class Action {
    protected GameMap gameMap;

    public Action(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public abstract void perform();
}