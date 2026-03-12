package main.java.utils;

public final class SimulationConstants {
    private SimulationConstants() {
    }

    public static final int THREAD_SLEEP = 1800;

    public static final String ANSI_CLEAR_SCREEN = "\033[H\033[2J";

    public static final int MAX_COLUMN_VALUE = 14;
    public static final int MAX_ROW_VALUE = 16;

    public static final String ANSI_GREY_BACKGROUND_COLOR = "\u001B[;100m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String EMPTY_CELL = "  　";
    public static final String CELL_WITH_ENTITY = " %s ";

    public static final int SATURATION = 2;
    public static final int MIN_HP = 0;

    public static final int CAT_HP = 10;
    public static final int MOUSE_HP = 6;
    public static final int CAT_SPEED = 3;
    public static final int MOUSE_SPEED = 2;
    public static final int CAT_ATTACK = 2;

    public static final String MOTION = "Х";
    public static final String START = "Н";
    public static final String PAUSE = "П";
    public static final String EXIT = "В";

    public static final int CAT_COUNT = 3;
    public static final int MOUSE_COUNT = 5;
    public static final int CHEESE_COUNT = 7;
    public static final int BOX_COUNT = 25;
    public static final int BASKET_COUNT = 20;
}
