package main.java.entity;

import main.java.entity.creature.Cat;
import main.java.entity.creature.Mouse;
import main.java.service.*;
import main.java.utils.Coordinates;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class EntityFactory {
    private final Map<Class<?>, Function<Coordinates, Entity>> entityCreators = new HashMap<>();

    public EntityFactory() {
        PathFindingService bfsPathFinder = new BfsPathFinder();
        FeedingService survivorFeeder = new SurvivorFeeder();
        FeedingService predatorFeeder = new PredatorFeeder();
        initializeEntityCreators(bfsPathFinder, survivorFeeder, predatorFeeder);
    }

    public <T extends Entity> T createEntity(Coordinates coordinates, Class<T> entityClass) {
        @SuppressWarnings("unchecked")
        Function<Coordinates, T> entityCreator = (Function<Coordinates, T>) entityCreators.get(entityClass);
        return entityCreator.apply(coordinates);
    }

    private void initializeEntityCreators(PathFindingService bfsPathFinder, FeedingService survivorFeeder, FeedingService predatorFeeder) {
        registerEntityCreator(Box.class, coordinates -> new Box());
        registerEntityCreator(Basket.class, coordinates -> new Basket());
        registerEntityCreator(Cheese.class, coordinates -> new Cheese());
        registerEntityCreator(Cat.class, coordinates -> new Cat(10, 3, Mouse.class, coordinates, bfsPathFinder, predatorFeeder, 2));
        registerEntityCreator(Mouse.class, coordinates -> new Mouse(6, 2, Cheese.class, coordinates, bfsPathFinder, survivorFeeder));
    }

    private <T extends Entity> void registerEntityCreator(Class<T> entityClass, Function<Coordinates, Entity> creator) {
        entityCreators.put(entityClass, creator);
    }
}
