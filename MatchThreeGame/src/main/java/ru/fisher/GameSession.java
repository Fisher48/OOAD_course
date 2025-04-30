package ru.fisher;

import ru.fisher.GameField.Element;
import ru.fisher.GameField.Field;
import ru.fisher.GameLogic.GameCommand;
import ru.fisher.GameLogic.GameRules;
import ru.fisher.GameLogic.Statistics;

import java.util.Scanner;

public class GameSession {

    private Field field;
    private GameRules rules;
    private GameCommand command;
    private Statistics statistics;
    private Scanner scanner;

    public GameSession() {
        this.field = new Field(8, 8);
        this.rules = new GameRules();
        this.command = new GameCommand();
        this.statistics = new Statistics();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=== Добро пожаловать в Match-3 ===");
        System.out.println("Команды:");
        System.out.println("  x1 y1 x2 y2  — сделать ход (например 2 3 2 4)");
        System.out.println("  restart      — начать заново");
        System.out.println("  exit         — выйти из игры");
        System.out.println("  help         — показать справку");
        System.out.println();

        field.renderFiled();

        while (!gameOver()) {
            handlePlayerMove();
            field.renderFiled();
            statistics.displayStatistics();
        }

        System.out.println("Игра окончена!");
    }

    public void handlePlayerMove() {
        System.out.print("Введите координаты (x1 y1 x2 y2) или команду (restart/exit/help): ");
        String input = scanner.nextLine().trim();

        switch (input.toLowerCase()) {
            case "exit":
                exit();
                return;
            case "restart":
                restart();
                return;
            case "help":
                System.out.println("Формат: x1 y1 x2 y2 — обмен двух элементов.");
                System.out.println("Команды: restart — перезапуск, exit — выход.");
                return;
        }

        String[] coords = input.split("\\s+");
        if (coords.length != 4) {
            showError("Ожидается 4 числа или команда.");
            return;
        }

        try {
            int x1 = Integer.parseInt(coords[0]);
            int y1 = Integer.parseInt(coords[1]);
            int x2 = Integer.parseInt(coords[2]);
            int y2 = Integer.parseInt(coords[3]);

            Element e1 = field.getElement(x1, y1);
            Element e2 = field.getElement(x2, y2);

            if (command.swapIsValid(field, e1, e2)) {
                command.swapElements(field, e1, e2);
                statistics.addMoves(1);

                if (rules.hasMatches(field)) {
                    rules.removeMatch(field, statistics);
                    rules.shiftElementsDown(field);
                    rules.fillEmptyCells(field);
                    statistics.addPoints(10); // пока просто
                } else {
                    command.swapElements(field, e1, e2); // отмена
                    showError("Нет совпадений. Ход отменён.");
                }
            } else {
                showError("Невалидный ход. Элементы не соседние.");
            }

        } catch (NumberFormatException e) {
            showError("Ошибка ввода. Используйте четыре числа или команду.");
        } catch (Exception e) {
            showError("Ошибка: " + e.getMessage());
        }
    }

    public boolean gameOver() {
        return !rules.hasValidMoves(field);
    }

    public void restart() {
        this.field = new Field(8, 8);
        this.field.initField();
        this.statistics.resetStat();
        System.out.println("Игра перезапущена!");
    }

    public void exit() {
        System.out.println("Выход из игры...");
        System.out.println("Спасибо за игру!");
        statistics.displayStatistics();
        System.exit(0);
    }

    public void showError(String message) {
        System.err.println("[ОШИБКА]: " + message);
    }


}
