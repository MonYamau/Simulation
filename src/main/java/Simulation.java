package main.java;

import main.java.map.GameMap;

import java.util.Objects;
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
        MapRenderer mapRenderer = new MapRenderer();
        Actions actions = new Actions();

        actions.initActions(gameMap, mapRenderer);
        while (true){
            String input = scanner.nextLine();
            if (input.equals("0")){
                return;
            }
            if(input.equals(" ")){
                actions.turnActions(gameMap, mapRenderer);
            }
        }
    }
}
