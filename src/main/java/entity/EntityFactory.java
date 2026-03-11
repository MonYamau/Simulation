package main.java.entity;

import main.java.entity.creature.Cat;
import main.java.entity.creature.Mouse;
import main.java.service.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EntityFactory {
    private static final int CAT_HP = 10;
    private static final int MOUSE_HP = 6;
    private static final int CAT_SPEED = 3;
    private static final int MOUSE_SPEED = 2;
    private static final int CAT_ATTACK = 2;

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
        registerEntityCreator(Cat.class, () -> new Cat(CAT_HP, CAT_SPEED, Mouse.class, bfsPathFinder, predatorFeeder, CAT_ATTACK));
        registerEntityCreator(Mouse.class, () -> new Mouse(MOUSE_HP, MOUSE_SPEED, Cheese.class, bfsPathFinder, survivorFeeder));
    }

    private <T extends Entity> void registerEntityCreator(Class<T> entityClass, Supplier<Entity> creator) {
        entityCreators.put(entityClass, creator);
    }
}
