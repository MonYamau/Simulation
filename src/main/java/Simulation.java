package main.java;

import main.java.map.GameMap;
import main.java.map.GameMapRenderer;

import java.util.Scanner;

//ГЛАВНЫЙ КЛАСС ПРИЛОЖЕНИЯ
//содержит счётчик ходов, map, renderer, actions
/*
Ключевые методы:
nextTurn - просимулировать и отрендерить ОДИН ход
startSimulation - запустить бесконечный цикл симуляции и рендеринга
pauseSimulation - приостановить бесконечный цикл симуляции и рендеринга
 */
public class Simulation {
    public static int counter = 0;

    GameMap gameMap = new GameMap();
    GameMapRenderer gameMapRenderer = new GameMapRenderer(gameMap);
    Actions actions = new Actions(gameMap);

    public void initSimulation() {
        actions.initActions();
        gameMapRenderer.printMapSimulation();
    }

    public void nextTurn(ScriptRenderer scriptRenderer){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        scriptRenderer.printInstructionScript();
        actions.turnActions();
        gameMapRenderer.printMapSimulation();
    }

    public void startSimulation(ScriptRenderer scriptRenderer){
        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            scriptRenderer.printInstructionScript();
            actions.turnActions();
            gameMapRenderer.printMapSimulation();
            try {
                Thread.sleep(1800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void pauseSimulation(ScriptRenderer scriptRenderer){
    }
}
