package ru.fisher.GameLogic;

import ru.fisher.GameField.Field;

// Интерфейс логики игры
public interface Rules {

    // Проверка активации бонуса
    boolean bonusIsActivate();

    // Проверка есть ли совпадения?
    boolean hasMatches(Field field);

    // Предусловие: есть совпадения
    // Постусловие: удалены совпавшие элементы
    void removeMatch(Field field);

    // Предусловие: есть пустые клетки
    // Постусловие: элементы сдвинуты вниз
    void shiftElementsDown(Field field);

    // Запрос: есть ли возможные ходы?
    boolean hasValidMoves(Field field);
}
