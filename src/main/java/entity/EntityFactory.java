package main.java.entity;

import main.java.entity.creature.Cat;
import main.java.entity.creature.Mouse;
import main.java.service.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EntityFactory {
    private final Map<Class<?>, Supplier<Entity>> entityCreators = new HashMap<>();

    public EntityFactory() {
        PathFindingService bfsPathFinder = new BfsPathFinder();
        FeedingService survivorFeeder = new SurvivorFeeder();
        FeedingService predatorFeeder = new PredatorFeeder();
        initializeEntityCreators(bfsPathFinder, survivorFeeder, predatorFeeder);
    }

    public <T extends Entity> T createEntity(Class<T> entityClass) {
        @SuppressWarnings("unchecked")
        Supplier<T> entityCreator = (Supplier<T>) entityCreators.get(entityClass);
        return entityCreator.get();
    }

    private void initializeEntityCreators(PathFindingService bfsPathFinder, FeedingService survivorFeeder, FeedingService predatorFeeder) {
        registerEntityCreator(Box.class, Box::new);
        registerEntityCreator(Basket.class, Basket::new);
        registerEntityCreator(Cheese.class, Cheese::new);
        registerEntityCreator(Cat.class, () -> new Cat(10, 3, Mouse.class, bfsPathFinder, predatorFeeder, 2));
        registerEntityCreator(Mouse.class, () -> new Mouse(6, 2, Cheese.class, bfsPathFinder, survivorFeeder));
    }

    private <T extends Entity> void registerEntityCreator(Class<T> entityClass, Supplier<Entity> creator) {
        entityCreators.put(entityClass, creator);
    }
}
