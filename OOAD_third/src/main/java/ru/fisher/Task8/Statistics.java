package ru.fisher.Task8;

// Класс Статистики игрока в игре
public abstract class Statistics {

    Integer points;
    Integer moves;

    // Добавить очки
    public abstract void addPoints();

    // Добавить ходы
    public abstract void addMoves();

    // Запрос возвращает кол-во очков для игрока
    public abstract void getPoints();

    // Запрос возвращает кол-во ходов для игрока
    public abstract void getMoves();

    // Вывод статистики
    public abstract void displayStatistics();

}
