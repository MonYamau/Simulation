package main.java;

import main.java.map.GameMap;
import main.java.map.GameMapRenderer;

//ГЛАВНЫЙ КЛАСС ПРИЛОЖЕНИЯ
//содержит счётчик ходов, map, renderer, actions
/*
Ключевые методы:
nextTurn - просимулировать и отрендерить ОДИН ход
startSimulation - запустить бесконечный цикл симуляции и рендеринга
pauseSimulation - приостановить бесконечный цикл симуляции и рендеринга
 */
public class Simulation {
    private volatile boolean isRunning = true;
    private volatile boolean isPaused = true;

    public static int counter = 0;

    GameMap gameMap = new GameMap();
    GameMapRenderer gameMapRenderer = new GameMapRenderer(gameMap);
    Actions actions = new Actions(gameMap);

    public void initSimulation() {
        ScriptRenderer.clearScreen();
        ScriptRenderer.printWelcomeScript();
        ScriptRenderer.printInstructionScript();
        ScriptRenderer.printCounter(counter);
        actions.initActions();
        gameMapRenderer.printMapSimulation();
    }

    public void nextTurn(){
        ScriptRenderer.clearScreen();
        ScriptRenderer.printInstructionScript();
        ScriptRenderer.printCounter(++counter);
        actions.turnActions();
        gameMapRenderer.printMapSimulation();
    }

    public synchronized void startSimulation(){
        isRunning = true;
        isPaused = false;
        Thread simulationThread = getThread();
        simulationThread.start();
    }

    public Thread getThread(){
        return new Thread(() -> {
            while (isRunning) {
                synchronized (this) {
                    while (isPaused) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                }
                if (!isRunning) break;
                nextTurn();
                try {
                    Thread.sleep(1800);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
    }

    public synchronized void pauseSimulation(){
        isPaused = true;
    }

    public synchronized void stopSimulation() {

    }
}
