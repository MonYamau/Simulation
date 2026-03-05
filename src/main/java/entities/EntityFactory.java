package main.java.entities;

import main.java.Coordinates;
import main.java.entities.animate.Cat;
import main.java.entities.animate.Mouse;
import main.java.entities.inanimate.Basket;
import main.java.entities.inanimate.Box;
import main.java.entities.inanimate.Cheese;
import main.java.entities.inanimate.Yarn;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class EntityFactory {
    private final Map<Class<?>, Function<Coordinates, Entity>> entityCreators = new HashMap<>();

    public EntityFactory() {
        writeEntity(Box.class, coordinates -> new Box());
        writeEntity(Basket.class, coordinates -> new Basket());
        writeEntity(Yarn.class, coordinates -> new Yarn());
        writeEntity(Cheese.class, coordinates -> new Cheese());
        writeEntity(Cat.class, coordinates -> new Cat(10, 3, "Mouse", coordinates, 2));
        writeEntity(Mouse.class, coordinates -> new Mouse(6, 2, "Cheese", coordinates));
    }

    private <T extends Entity> void writeEntity(Class<T> entityClass, Function<Coordinates, Entity> creator) {
        entityCreators.put(entityClass, creator);
    }

    public <T extends Entity> T createEntity(Coordinates coordinates, Class<T> entityClass) {
        Function<Coordinates, T> creator = (Function<Coordinates, T>) entityCreators.get(entityClass);
        return creator.apply(coordinates);
    }
}
