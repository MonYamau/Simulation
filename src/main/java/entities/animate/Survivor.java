package main.java.entities.animate;

import main.java.Coordinates;
import main.java.map.GameMap;

public abstract class Survivor extends Creature {
    public Survivor(int hp, int speed, String food, Coordinates coordinates) {
        super(hp, speed, food, coordinates);
    }

    @Override
    public void eatFood(Coordinates coordinates, GameMap gameMap) {
        gameMap.removeEntity(coordinates);
        setHp(getHp() + getSatiety());
        System.out.println(getHp());
    }
}