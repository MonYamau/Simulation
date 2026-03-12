package main.java.map;

import main.java.entity.Basket;
import main.java.entity.Box;
import main.java.entity.Cheese;
import main.java.entity.Entity;
import main.java.entity.creature.Cat;
import main.java.entity.creature.Mouse;
import main.java.utils.Coordinates;

import java.util.Optional;

import static main.java.utils.SimulationConstants.*;

public final class GameMapRenderer {
    private GameMapRenderer() {
    }

    public static void printGameMap(GameMap gameMap) {
        StringBuilder renderMap = new StringBuilder();
        for (int col = 0; col < MAX_COLUMN_VALUE; col++) {
            renderMap.append("\n");
            for (int row = 0; row < MAX_ROW_VALUE; row++) {
                String colorCell = renderCell(gameMap, new Coordinates(col, row));
                renderMap.append(colorCell);
            }
        }
        System.out.println(renderMap);
    }

    private static String renderCell(GameMap gameMap, Coordinates coordinates) {
        if (!gameMap.isCellEmpty(coordinates)) {
            Optional<Entity> entity = gameMap.getEntity(coordinates);
            if (entity.isEmpty()) {
                throw new IllegalArgumentException("invalid entity received: " + entity);
            }
            return formatEntityCellWithColoring(coordinates, entity.get());
        }
        return formatEmptyCellWithColoring(coordinates);
    }

    private static String formatEmptyCellWithColoring(Coordinates coordinates) {
        if (isEvenCell(coordinates)) {
            return "%s%s%s".formatted(ANSI_GREY_BACKGROUND_COLOR, EMPTY_CELL, ANSI_RESET);
        } else {
            return "%s".formatted(EMPTY_CELL);
        }
    }

    private static String formatEntityCellWithColoring(Coordinates coordinates, Entity entity) {
        if (isEvenCell(coordinates)) {
            return "%s%s%s".formatted(ANSI_GREY_BACKGROUND_COLOR, getEntitySprite(entity), ANSI_RESET);
        } else {
            return "%s".formatted(getEntitySprite(entity));
        }
    }

    private static String getEntitySprite(Entity entity) {
        if (entity instanceof Basket) {
            return CELL_WITH_ENTITY.formatted("🧺");
        }
        if (entity instanceof Box) {
            return CELL_WITH_ENTITY.formatted("📦");
        }
        if (entity instanceof Cheese) {
            return CELL_WITH_ENTITY.formatted("🧀");
        }
        if (entity instanceof Mouse) {
            return CELL_WITH_ENTITY.formatted("🐭");
        }
        if (entity instanceof Cat) {
            return CELL_WITH_ENTITY.formatted("🐱");
        }
        throw new IllegalArgumentException("Incorrect type of object");
    }

    private static boolean isEvenCell(Coordinates coordinates) {
        return (coordinates.col() + coordinates.row()) % 2 == 0;
    }
}
