package main.java.entity;

import main.java.entity.creature.Cat;
import main.java.entity.creature.Mouse;
import main.java.map.Coordinates;
import main.java.service.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class EntityFactory {
    public static final int CAT_HP = 10;
    public static final int MOUSE_HP = 6;
    public static final int CAT_SPEED = 3;
    public static final int MOUSE_SPEED = 2;
    public static final int CAT_ATTACK = 2;

    Set<Coordinates> shifts = Set.of(
            new Coordinates(-1, 0),
                new Coordinates(1, 0),
                new Coordinates(0, -1),
                new Coordinates(0, 1)
        );

    private final Map<Class<?>, Supplier<Entity>> entityCreators = new HashMap<>();

    public EntityFactory() {
        MovementOptionsProvider movementOptionsProvider = new MovementOptionsProvider(shifts);
        PathFinder bfsPathFinder = new BfsPathFinder(movementOptionsProvider);
        MovementService movementService = new MovementService(movementOptionsProvider, bfsPathFinder);
        initializeEntityCreators(movementService);
    }

    public <T extends Entity> T createEntity(Class<T> entityClass) {
        @SuppressWarnings("unchecked")
        Supplier<T> entityCreator = (Supplier<T>) entityCreators.get(entityClass);
        return entityCreator.get();
    }

    private void initializeEntityCreators(MovementService movementService) {
        registerEntityCreator(Box.class, Box::new);
        registerEntityCreator(Basket.class, Basket::new);
        registerEntityCreator(Cheese.class, Cheese::new);
        registerEntityCreator(Cat.class, () -> new Cat(CAT_HP, CAT_SPEED, Mouse.class, movementService, CAT_ATTACK));
        registerEntityCreator(Mouse.class, () -> new Mouse(MOUSE_HP, MOUSE_SPEED, Cheese.class, movementService));
    }

    private <T extends Entity> void registerEntityCreator(Class<T> entityClass, Supplier<Entity> creator) {
        entityCreators.put(entityClass, creator);
    }
}
