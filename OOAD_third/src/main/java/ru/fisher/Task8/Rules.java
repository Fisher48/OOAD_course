package ru.fisher.Task8;

// Класс с правилами игры и логикой проверки
public interface Rules {

    // Активен бонус или нет
    boolean bonusIsActivate();

    // Удаление комбинации
    void removeMatch();

    // Смещение элементов вниз
    void shiftElementsDown();

}
