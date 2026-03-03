package main.java.entities.animate;

import main.java.PathFinder;
import main.java.Coordinates;
import main.java.entities.Entity;
import main.java.map.GameMap;

//существо имеет скорость(сколько клеток может пройти за ход), количество HP
//имеет метод make move - сделать ход
public abstract class Creature extends Entity {
    private int hp;
    private final int speed;
    private final String food;
    private Coordinates coordinates;

    public Creature(int speed, String food, Coordinates coordinates) {
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

    //инициирует движение
    //передаёт значение скорости
    public void makeMove(GameMap gameMap) {
        for (int i = 0; i < getSpeed(); i++) {
            PathFinder pathFinder = new PathFinder();
            Coordinates move = pathFinder.getNextCellForMove(getCoordinates(), getFood(), gameMap);
            if (!gameMap.isCellEmpty(move) && gameMap.getEntity(move).getClass().getSimpleName().equals(getFood())){
                eatFood(move, gameMap);
            } else {
                gameMap.replaceEntity(getCoordinates(), move);
            }
        }
    }

    public abstract void eatFood(Coordinates coordinates, GameMap gameMap);
}
