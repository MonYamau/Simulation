package main.java;

import main.java.entities.Entity;
import main.java.entities.animate.Creature;
import main.java.map.GameMap;

import java.util.ArrayList;
import java.util.List;

/*
Действия, совершаемые над миром. Итерация всех действий всех существ (makeMove)
Каждое действие описывается отдельным классом и совершает операции над картой
Основные действия:
initActions - расстановка существ и объектов по карте
turnActions - цикл каждого хода. Пополнение ресурса, существ и тд
 */
public class Actions {
    public void initActions(GameMap gameMap, MapRenderer mapRenderer) {
        gameMap.setupDefaultMap();
        mapRenderer.printMapSimulation(gameMap);
    }

    public void turnActions(GameMap gameMap, MapRenderer mapRenderer) {
        List<Creature> creatures = gameMap.getAllCreatures();
        for (int i = 0; i < creatures.size(); i++) {
            creatures.get(i).makeMove(gameMap);
        }
        mapRenderer.printMapSimulation(gameMap);
    }
}
