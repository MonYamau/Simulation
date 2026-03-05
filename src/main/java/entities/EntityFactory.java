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
        saveEntity(Box.class, coordinates -> new Box());
        saveEntity(Basket.class, coordinates -> new Basket());
        saveEntity(Yarn.class, coordinates -> new Yarn());
        saveEntity(Cheese.class, coordinates -> new Cheese());
        saveEntity(Cat.class, coordinates -> new Cat(10, 3, "Mouse", coordinates, 2));
        saveEntity(Mouse.class, coordinates -> new Mouse(6, 2, "Cheese", coordinates));
    }

    private <T extends Entity> void saveEntity(Class<T> entityClass, Function<Coordinates, Entity> creator) {
        entityCreators.put(entityClass, creator);
    }

    public <T extends Entity> Entity createEntity(Coordinates coordinates, Class<T> entityClass) {
        Function<Coordinates, Entity> creator = entityCreators.get(entityClass);
        return creator.apply(coordinates);
    }
}
