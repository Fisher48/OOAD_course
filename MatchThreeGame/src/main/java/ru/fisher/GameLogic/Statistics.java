package ru.fisher.GameLogic;

public class Statistics {

    protected Integer points = 0;
    protected Integer moves = 0;

    // Постусловие: увеличены очки
    public void addPoints(int amount) {
        points += amount;
    }

    // Постусловие: увеличены ходы
    public void addMoves(int amount) {
        moves += amount;
    }

    // Запрос получения кол-во очков
    public int getPoints() {
        return points;
    }

    // Запрос получения кол-во ходов
    public int getMoves() {
        return moves;
    }

    // Обновление очков/ходов
    public void updateStatistics() {

    }

    // Сбросить статистику
    public void resetStat() {
        this.moves = 0;
        this.points = 0;
    }

    // Отображение текущей статистики
    public void displayStatistics() {
        System.out.println("Очки: " + points);
        System.out.println("Ходы: " + moves);
    }
}
