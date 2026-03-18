package main.java.gamemap;

import main.java.entity.Basket;
import main.java.entity.Box;
import main.java.entity.Cheese;
import main.java.entity.Entity;
import main.java.entity.creature.Cat;
import main.java.entity.creature.Mouse;
import main.java.service.Coordinates;

import java.util.Optional;

public class GameMapRenderer {
    private static final String ANSI_GREY_BACKGROUND_COLOR = "\u001B[;100m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String EMPTY_CELL = "  　";
    private static final String CELL_WITH_ENTITY = " %s ";

    private final int maxColumnValue;
    private final int maxRowValue;

    public GameMapRenderer(int maxColumnValue, int maxRowValue) {
        this.maxColumnValue = maxColumnValue;
        this.maxRowValue = maxRowValue;
    }

    public void printGameMap(GameMap gameMap) {
        StringBuilder renderMap = new StringBuilder();
        for (int col = 0; col < maxColumnValue; col++) {
            renderMap.append("\n");
            for (int row = 0; row < maxRowValue; row++) {
                String colorCell = renderCell(gameMap, new Coordinates(col, row));
                renderMap.append(colorCell);
            }
        }
        System.out.println(renderMap);
    }

    private String renderCell(GameMap gameMap, Coordinates coordinates) {
        if (!gameMap.isCellEmpty(coordinates)) {
            Optional<Entity> entity = gameMap.getEntity(coordinates);
            if (entity.isEmpty()) {
                throw new IllegalArgumentException("invalid entity received: " + entity);
            }
            return formatEntityCellWithColoring(coordinates, entity.get());
        }
        return formatEmptyCellWithColoring(coordinates);
    }

    private String formatEmptyCellWithColoring(Coordinates coordinates) {
        if (isEvenCell(coordinates)) {
            return "%s%s%s".formatted(ANSI_GREY_BACKGROUND_COLOR, EMPTY_CELL, ANSI_RESET);
        } else {
            return "%s".formatted(EMPTY_CELL);
        }
    }

    private String formatEntityCellWithColoring(Coordinates coordinates, Entity entity) {
        if (isEvenCell(coordinates)) {
            return "%s%s%s".formatted(ANSI_GREY_BACKGROUND_COLOR, getEntitySprite(entity), ANSI_RESET);
        } else {
            return "%s".formatted(getEntitySprite(entity));
        }
    }

    private String getEntitySprite(Entity entity) {
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

    private boolean isEvenCell(Coordinates coordinates) {
        return (coordinates.col() + coordinates.row()) % 2 == 0;
    }
}
