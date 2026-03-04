package main.java;

import main.java.map.GameMap;
import main.java.map.MapRenderer;

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
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        GameMap gameMap = new GameMap();
        MapRenderer mapRenderer = new MapRenderer(gameMap);
        Actions actions = new Actions(gameMap, mapRenderer);

        actions.initActions();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                return;
            }
            if (input.equals(" ")) {
                actions.turnActions();
            }
        }
    }
}
