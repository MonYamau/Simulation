package main.java;

import main.java.entities.Entity;
import main.java.entities.animate.Creature;
import main.java.entities.animate.Mouse;
import main.java.entities.inanimate.Cheese;
import main.java.map.GameMap;
import main.java.map.GameMapLayout;
import main.java.map.GameMapRenderer;

import java.util.List;

/*
Действия, совершаемые над миром. Итерация всех действий всех существ (makeMove)
Каждое действие описывается отдельным классом и совершает операции над картой
Основные действия:
initActions - расстановка существ и объектов по карте
turnActions - цикл каждого хода. Пополнение ресурса, существ и тд
 */
public class Actions {
    GameMap gameMap;
    GameMapRenderer gameMapRenderer;

    public Actions(GameMap gameMap, GameMapRenderer gameMapRenderer) {
        this.gameMap = gameMap;
        this.gameMapRenderer = gameMapRenderer;
    }

    public void initActions() {
        GameMapLayout gameMapLayout = new GameMapLayout(gameMap);
        gameMapLayout.setupStartMap();
        gameMapRenderer.printMapSimulation();
    }

    public void turnActions() {
        List<Creature> creatures = gameMap.getAllCreatures();
        addResources();
        for (Creature creature : creatures) {
            creature.makeMove(gameMap);
        }
        gameMapRenderer.printMapSimulation();
    }

    private void addResources() {
        if (isSmallAmountOfCheese()) {

        }
        if (isSmallAmountOfMouse()) {

        }
    }

    private boolean isSmallAmountOfMouse() {
        int mouseCounter = 0;
        for (Entity entity : gameMap.getAllEntities()) {
            if (entity instanceof Mouse) {
                mouseCounter++;
            }
        }
        return mouseCounter < 1;
    }

    private boolean isSmallAmountOfCheese() {
        int cheeseCounter = 0;
        for (Entity entity : gameMap.getAllEntities()) {
            if (entity instanceof Cheese) {
                cheeseCounter++;
            }
        }
        return cheeseCounter < 1;
    }
}
