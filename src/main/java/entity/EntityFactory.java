package main.java.entity;

import main.java.utils.Coordinates;
import main.java.service.FeedingService;
import main.java.service.PathFindingService;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class EntityFactory {
    private final Map<Class<?>, Function<Coordinates, Entity>> entityCreators = new HashMap<>();

    public EntityFactory(PathFindingService bfsPathFinder, FeedingService survivorFeeder, FeedingService predatorFeeder) {
        writeEntity(Box.class, coordinates -> new Box());
        writeEntity(Basket.class, coordinates -> new Basket());
        writeEntity(Yarn.class, coordinates -> new Yarn());
        writeEntity(Cheese.class, coordinates -> new Cheese());
        writeEntity(Cat.class, coordinates -> new Cat(10, 3, "Mouse", coordinates, bfsPathFinder, predatorFeeder, 2));
        writeEntity(Mouse.class, coordinates -> new Mouse(6, 2, "Cheese", coordinates, bfsPathFinder, survivorFeeder));
    }

    private <T extends Entity> void writeEntity(Class<T> entityClass, Function<Coordinates, Entity> creator) {
        entityCreators.put(entityClass, creator);
    }

    public <T extends Entity> T createEntity(Coordinates coordinates, Class<T> entityClass) {
        @SuppressWarnings("unchecked")
        Function<Coordinates, T> entityCreator = (Function<Coordinates, T>) entityCreators.get(entityClass);
        return entityCreator.apply(coordinates);
    }
}
