package main.java.entities.animate;

import main.java.Coordinates;
import main.java.entities.Entity;
import main.java.map.GameMap;

//имеет силу атаки
//может потратить ход на движение в сторону Survivor, либо его атаку
//здоровье Survivor снимается на значение атаки Predator
public abstract class Predator extends Creature {
    private final int attack;

    public Predator(int speed, String food, Coordinates coordinates, int attack) {
        super(speed, food, coordinates);
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }

    public void makeAttack(Creature creature) {
        creature.setHp(creature.getHp() - getAttack());
        System.out.println(creature.getHp());
    }

    @Override
    public void eatFood(Coordinates coordinates, GameMap gameMap) {
        Creature creature = (Creature) gameMap.getEntity(coordinates);
        if (creature.getHp() > 0) {
            makeAttack(creature);
        } else {
            gameMap.removeEntity(coordinates);
            setHp(getHp() + 2);
            System.out.println(getHp());
            System.out.println(coordinates);
        }
    }
}
