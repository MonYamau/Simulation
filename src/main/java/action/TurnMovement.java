package main.java.action;

import main.java.entity.Creature;
import main.java.map.GameMap;

import java.util.List;

public class TurnMovement extends Action {

    public TurnMovement(GameMap gameMap) {
        super(gameMap);
    }

    @Override
    public void perform() {
        List<Creature> creatures = gameMap.getEntitiesOfAnyType(Creature.class);
        for (Creature creature : creatures) {
            creature.makeMove(gameMap);
        }
    }
}
