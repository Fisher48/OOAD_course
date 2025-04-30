package ru.fisher.GameLogic;

import ru.fisher.GameField.Coordinates;
import ru.fisher.GameField.Element;
import ru.fisher.GameField.Field;


public class GameCommand {

    // Предусловие: элементы соседние?
    // Постусловие: элементы поменялись местами
    public void swapElements(Field field, Element e1, Element e2) {
        if (swapIsValid(field, e1, e2)) {
            // Получаем координаты
            Coordinates c1 = e1.getCoordinates();
            Coordinates c2 = e2.getCoordinates();

            // Меняем элементы местами в массиве поля
            field.setElement(e2, c1.getX(), c1.getY());
            field.setElement(e1, c2.getX(), c2.getY());

            // Обновляем координаты в самих элементах
            e1.setCoordinates(c2.getX(), c2.getY());
            e2.setCoordinates(c1.getX(), c1.getY());
        }
    }

    // Проверка валидности перестановки
    public boolean swapIsValid(Field field, Element e1, Element e2) {
        if (e1 == null || e2 == null) {
            return false;
        }

        Coordinates c1 = e1.getCoordinates();
        Coordinates c2 = e2.getCoordinates();

        // Проверка границ поля
        if (!field.isInBounds(c1.getX(), c1.getY()) || !field.isInBounds(c2.getX(), c2.getY())) {
            return false;
        }

        int dx = Math.abs(c1.getX() - c2.getX());
        int dy = Math.abs(c1.getY() - c2.getY());

        // Элементы считаются соседними, если они стоят рядом по горизонтали или вертикали
        return (dx == 1 && dy == 0) || (dx == 0 && dy == 1);
    }

}
