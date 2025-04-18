package ru.fisher.Task8;


// Команды управления для игрока
public abstract class GameCommand {

    // Предусловие: Элементы расположены рядом
    // Постусловие: Элементы переставлены местами
    public abstract void swapElements(Element e1, Element e2);

    // Запрос на проверку валидности перестановки элементов в поле
    public abstract boolean swapIsValid(Element e1, Element e2);

}
