package ru.fisher.GameLogic;

public class BonusSystem {

    // Метод для подсчета очков за удалённые элементы
    public int calculatePoints(int matchLength) {
        if (matchLength < 3) return 0;

        int basePoints = 10;
        int bonus = switch (matchLength) {
            case 3 -> 0;
            case 4 -> 5;
            case 5 -> 15;
            case 6 -> 30;
            default -> 50 + (matchLength - 6) * 10; // за 7+ символов — дополнительные очки
        };

        return basePoints * matchLength + bonus;
    }
}
