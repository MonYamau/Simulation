package main.java.entities.animate;

import main.java.Coordinates;
import main.java.PathFinder;
import main.java.entities.Entity;
import main.java.map.GameMap;

public abstract class Creature extends Entity {
    private final int speed;
    private final String food;
    private int hp;
    private Coordinates coordinates;

    public Creature(int hp, int speed, String food, Coordinates coordinates) {
        this.hp = hp;
        this.speed = speed;
        this.food = food;
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public String getFood() {
        return food;
    }

    public int getSatiety() {
        return 2;
    }

    public void makeMove(GameMap gameMap) {
        for (int i = 0; i < getSpeed(); i++) {
            PathFinder pathFinder = new PathFinder(gameMap);
            Coordinates move = pathFinder.getNextCellForMove(getCoordinates(), getFood());
            if (pathFinder.isEntityEatable(move, getFood())) {
                eatFood(move, gameMap);
            } else {
                gameMap.replaceEntity(getCoordinates(), move);
            }
        }
    }

    public abstract void eatFood(Coordinates coordinates, GameMap gameMap);
}
