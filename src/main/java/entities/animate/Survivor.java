package main.java.entities.animate;

import main.java.Coordinates;
import main.java.map.GameMap;

//стремится найти ресурс
//может потратить ход на движение в сторону ресурса, либо на её поглощение
public abstract class Survivor extends Creature{
    public Survivor(int speed, String food, Coordinates coordinates) {
        super(speed, food, coordinates);
    }

    @Override
    public void eatFood(Coordinates coordinates, GameMap gameMap) {
        gameMap.removeEntity(coordinates);
        setHp(getHp() + 10);
        System.out.println(getHp());
    }
}