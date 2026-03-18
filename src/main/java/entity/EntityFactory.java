package main.java.entity;

import main.java.entity.creature.Cat;
import main.java.entity.creature.Mouse;
import main.java.entity.creature.Survivor;
import main.java.entity.staticobject.Basket;
import main.java.entity.staticobject.Box;
import main.java.entity.staticobject.Cheese;
import main.java.movement.MovementService;
import main.java.movement.PathFinder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EntityFactory {
    private static final int CAT_HP = 10;
    private static final int MOUSE_HP = 6;
    private static final Class<?> CAT_TYPE_OF_FOOD = Survivor.class;
    private static final Class<?> MOUSE_TYPE_OF_FOOD = Cheese.class;
    private static final int CAT_SPEED = 3;
    private static final int MOUSE_SPEED = 2;
    private static final int CAT_ATTACK = 2;

    private final Map<Class<?>, Supplier<Entity>> entityCreators;

    public EntityFactory(PathFinder pathFinder) {
        entityCreators = new HashMap<>();
        MovementService movementService = new MovementService(pathFinder);
        initializeEntityCreators(movementService);
    }

    public <T extends Entity> T create(Class<T> entityClass) {
        @SuppressWarnings("unchecked")
        Supplier<T> entityCreator = (Supplier<T>) entityCreators.get(entityClass);
        return entityCreator.get();
    }

    private void initializeEntityCreators(MovementService movementService) {
        registerEntityCreator(Box.class, Box::new);
        registerEntityCreator(Basket.class, Basket::new);
        registerEntityCreator(Cheese.class, Cheese::new);
        registerEntityCreator(Cat.class, () -> new Cat(CAT_HP, CAT_SPEED, CAT_TYPE_OF_FOOD, movementService, CAT_ATTACK));
        registerEntityCreator(Mouse.class, () -> new Mouse(MOUSE_HP, MOUSE_SPEED, MOUSE_TYPE_OF_FOOD, movementService));
    }

    private <T extends Entity> void registerEntityCreator(Class<T> entityClass, Supplier<Entity> creator) {
        entityCreators.put(entityClass, creator);
    }
}
