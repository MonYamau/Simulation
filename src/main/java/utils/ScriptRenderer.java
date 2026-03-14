package main.java.utils;

import static main.java.core.SimulationLauncher.*;

public final class ScriptRenderer {
    public static final String ANSI_CLEAR_SCREEN = "\033[H\033[2J";

    private ScriptRenderer() {
    }

    public static void printWelcomeMessages() {
        clearScreen();
        printWelcomeScript();
        printInstructionScript();
    }

    public static void printTurnMessages(int counter) {
        clearScreen();
        printInstructionScript();
        printCounter(counter);
    }

    public static void printIncorrectInputScript() {
        System.out.println("Некорректный ввод! Введите одну букву кириллицы");
    }

    private static void clearScreen() {
        System.out.print(ANSI_CLEAR_SCREEN);
        System.out.flush();
    }

    private static void printCounter(int counter) {
        System.out.println("Количество произведённых ходов: " + counter);
    }

    private static void printWelcomeScript() {
        System.out.println("""
                Добро пожаловать в симуляцию!
                Карта заполнена препятствиями и существами. Существа: кошки и мышки!
                Кошки намереваются выловить всех мышей, а мыши хотят выискать весь сыр в помещении.
                Давайте понаблюдаем за этим процессом!""");
    }

    private static void printInstructionScript() {
        System.out.printf("""
                
                Введите [%s], чтобы выполнить один ход
                Введите [%s], чтобы запустить/продолжить бесконечную симуляцию
                Введите [%s], чтобы приостановить бесконечную симуляцию
                Введите [%s], чтобы выйти%n""", MOTION, START, PAUSE, EXIT);
    }
}
