package main.java.map;

import main.java.entity.*;
import main.java.utils.Coordinates;

import static main.java.map.GameMap.MAX_COLUMN_VALUE;
import static main.java.map.GameMap.MAX_ROW_VALUE;

public class GameMapRenderer {
    public static final String ANSI_GREY_BACKGROUND_COLOR = "\u001B[;100m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String CELL = "  　";
    public static String CELL_WITH_ENTITY = " %s ";

    GameMap gameMap;

    public GameMapRenderer(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void printMapSimulation() {
        StringBuilder renderMap = new StringBuilder();
        for (int col = 0; col < MAX_COLUMN_VALUE; col++) {
            renderMap.append("\n");
            for (int row = 0; row < MAX_ROW_VALUE; row++) {
                String colorCell;
                if (!gameMap.isCellEmpty(new Coordinates(col, row))) {
                    colorCell = colorizeEntityCell(new Coordinates(col, row), gameMap.getEntity(new Coordinates(col, row)));
                    renderMap.append(colorCell);
                } else {
                    colorCell = colorizeEmptyCell(new Coordinates(col, row));
                    renderMap.append(colorCell);
                }
            }
        }
        System.out.println(renderMap);
    }

    private String colorizeEmptyCell(Coordinates coordinates) {
        if (isEvenCell(coordinates)) {
            return "%s%s%s".formatted(ANSI_GREY_BACKGROUND_COLOR, CELL, ANSI_RESET);
        } else {
            return "%s".formatted(CELL);
        }
    }

    private String colorizeEntityCell(Coordinates coordinates, Entity entity) {
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
        return CELL;
    }

    private boolean isEvenCell(Coordinates coordinates) {
        return (coordinates.col() + coordinates.row()) % 2 == 0;
    }
}
