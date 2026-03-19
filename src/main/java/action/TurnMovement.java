package main.java.action;

import main.java.entity.creature.Creature;
import main.java.gamemap.GameMap;

import java.util.List;

public class TurnMovement extends Action {
    @Override
    public void perform(GameMap gameMap) {
        List<Creature> creatures = gameMap.getEntitiesByType(Creature.class);
        for (Creature creature : creatures) {
            if (creature.isAlive()) {
                creature.makeMove(gameMap);
            }
        }
    }
}
