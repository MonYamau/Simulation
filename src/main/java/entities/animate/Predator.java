package main.java.entities.animate;

import main.java.Coordinates;
import main.java.map.GameMap;

public abstract class Predator extends Creature {
    private final int attack;

    public Predator(int hp, int speed, String food, Coordinates coordinates, int attack) {
        super(hp, speed, food, coordinates);
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }

    @Override
    public void eatFood(Coordinates coordinates, GameMap gameMap) {
        Creature creature = (Creature) gameMap.getEntity(coordinates);
        if (creature.getHp() > 0) {
            makeAttack(creature);
        } else {
            gameMap.removeEntity(coordinates);
            setHp(getHp() + getSatiety());
            System.out.println(getHp());
            System.out.println(coordinates);
        }
    }

    public void makeAttack(Creature creature) {
        creature.setHp(creature.getHp() - getAttack());
        System.out.println(creature.getHp());
    }
}
