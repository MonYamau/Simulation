package main.java;


import static main.java.SimulationLauncher.*;

public class ScriptRenderer {
    public void printWelcomeScript(){
        System.out.println("""
                Добро пожаловать в симуляцию!
                Карта заполнена препятствиями и существами. Существа: кошки и мышки!
                Кошки намереваются выловить всех мышей, а мыши хотят выискать весь сыр в помещении.
                Давайте понаблюдаем за этим процессом!""");
    }

    public void printInstructionScript(){
        System.out.printf("""
                Введите [%s], чтобы запустить один ход
                Введите [%s], чтобы запустить бесконечную симуляцию
                Введите [%s], чтобы приостановить симуляцию
                Введите [%s], чтобы выйти%n""", MOTION, START, PAUSE, EXIT);
    }

    public void printIncorrectInputScript(){
        System.out.println("Некорректный ввод! Введите одну букву кириллицы");
    }
}
