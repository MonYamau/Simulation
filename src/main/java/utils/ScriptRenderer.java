package main.java.utils;

import static main.java.core.SimulationLauncher.*;

public final class ScriptRenderer {
    private ScriptRenderer(){}

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printCounter(int counter) {
        System.out.printf("Количество произведённых ходов: " + counter);
    }

    public static void printWelcomeScript() {
        System.out.println("""
                Добро пожаловать в симуляцию!
                Карта заполнена препятствиями и существами. Существа: кошки и мышки!
                Кошки намереваются выловить всех мышей, а мыши хотят выискать весь сыр в помещении.
                Давайте понаблюдаем за этим процессом!""");
    }

    public static void printInstructionScript() {
        System.out.printf("""
                Введите [%s], чтобы выполнить один ход
                Введите [%s], чтобы запустить/продолжить бесконечную симуляцию
                Введите [%s], чтобы приостановить бесконечную симуляцию
                Введите [%s], чтобы выйти%n""", MOTION, START, PAUSE, EXIT);
    }

    public static void printIncorrectInputScript() {
        System.out.println("Некорректный ввод! Введите одну букву кириллицы");
    }
}
