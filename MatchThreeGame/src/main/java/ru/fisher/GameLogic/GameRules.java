package ru.fisher.GameLogic;

import ru.fisher.GameField.Coordinates;
import ru.fisher.GameField.Element;
import ru.fisher.GameField.ElementsFactory;
import ru.fisher.GameField.Field;

import java.util.HashSet;
import java.util.Set;

public class GameRules implements Rules {

    Set<Coordinates> coordinates = new HashSet<>();
    ElementsFactory elementsFactory = new ElementsFactory();
    BonusSystem bonusSystem = new BonusSystem();

    @Override
    public boolean bonusIsActivate() {
        return false;
    }

    @Override
    public boolean hasMatches(Field field) {
        coordinates.clear(); // очищаем перед каждой проверкой
        boolean vertical = checkVerticalMatches(field);
        boolean horizontal = checkHorizontalMatches(field);
        return vertical || horizontal;
    }

    public boolean checkVerticalMatches(Field field) {
        boolean isMatch = false;
        for (int i = 0; i < field.getRows() - 1; i++) {
            for (int j = 0; j < field.getColumns() - 2; j++) {
                Element e1 = field.getElement(i, j);
                Element e2 = field.getElement(i, j+1);
                Element e3 = field.getElement(i, j+2);
                if (e1.equals(e2) && e2.equals(e3)) {
                    coordinates.add(e1.getCoordinates());
                    coordinates.add(e2.getCoordinates());
                    coordinates.add(e3.getCoordinates());
                    isMatch = true;
                }
            }
        }
        return isMatch;
    }

    public boolean checkHorizontalMatches(Field field) {
        boolean isMatch = false;
        for (int i = 0; i < field.getRows() - 2; i++) {
            for (int j = 0; j < field.getColumns() - 1; j++) {
                Element e1 = field.getElement(i, j);
                Element e2 = field.getElement(i+1, j);
                Element e3 = field.getElement(i+2, j);
                if (e1.equals(e2) && e2.equals(e3)) {
                    coordinates.add(e1.getCoordinates());
                    coordinates.add(e2.getCoordinates());
                    coordinates.add(e3.getCoordinates());
                    isMatch = true;
                }
            }
        }
        return isMatch;
    }

    @Override
    public void removeMatch(Field field) {
        if (hasMatches(field)) {
            for (int i = 0; i < field.getRows(); i++) {
                for (int j = 0; j < field.getColumns(); j++) {
                    Element element = field.getElement(i, j);
                    if (coordinates.contains(element.getCoordinates())) {
                        element = new Element("*", new Coordinates(i, j));
                        field.setElement(element, i, j);
                    }
                }
            }
        }
    }

    public void removeMatch(Field field, Statistics statistics) {
        coordinates.clear();
        int pointsEarned = 0;

        pointsEarned += collectVerticalMatches(field);
        pointsEarned += collectHorizontalMatches(field);

        if (!coordinates.isEmpty()) {
            System.out.println();
            System.out.println("Найдены совпадения!");
            field.renderFiled();
            sleep(1500);

            // Удалить
            for (Coordinates coord : coordinates) {
                int x = coord.getX();
                int y = coord.getY();
                field.setElement(new Element("*", new Coordinates(x, y)), x, y);
            }
            System.out.println();

            // Показать удаление
            System.out.println("Удаление элементов...");
            field.renderFiled();
            sleep(1000);
            System.out.println();

            statistics.addPoints(pointsEarned);
        }
    }

    // Задержка для вывода
    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int collectVerticalMatches(Field field) {
        int totalPoints = 0;
        for (int i = 0; i < field.getRows(); i++) {
            int count = 1;
            for (int j = 1; j < field.getColumns(); j++) {
                Element prev = field.getElement(i, j - 1);
                Element curr = field.getElement(i, j);
                if (prev.equals(curr)) {
                    count++;
                } else {
                    if (count >= 3) {
                        totalPoints += scoreForMatch(count);
                        for (int k = 0; k < count; k++) {
                            coordinates.add(new Coordinates(i, j - 1 - k));
                        }
                    }
                    count = 1;
                }
            }
            if (count >= 3) {
                totalPoints += scoreForMatch(count);
                for (int k = 0; k < count; k++) {
                    coordinates.add(new Coordinates(i, field.getColumns() - 1 - k));
                }
            }
        }
        return totalPoints;
    }

    public int collectHorizontalMatches(Field field) {
        int totalPoints = 0;
        for (int j = 0; j < field.getColumns(); j++) {
            int count = 1;
            for (int i = 1; i < field.getRows(); i++) {
                Element prev = field.getElement(i - 1, j);
                Element curr = field.getElement(i, j);
                if (prev.equals(curr)) {
                    count++;
                } else {
                    if (count >= 3) {
                        totalPoints += scoreForMatch(count);
                        for (int k = 0; k < count; k++) {
                            coordinates.add(new Coordinates(i - 1 - k, j));
                        }
                    }
                    count = 1;
                }
            }
            if (count >= 3) {
                totalPoints += scoreForMatch(count);
                for (int k = 0; k < count; k++) {
                    coordinates.add(new Coordinates(field.getRows() - 1 - k, j));
                }
            }
        }
        return totalPoints;
    }

    private int scoreForMatch(int count) {
        return bonusSystem.calculatePoints(count);
    }

    @Override
    public void shiftElementsDown(Field field) {
        for (int col = 0; col < field.getColumns(); col++) {
            int emptyRow = field.getRows() - 1;

            // Идем снизу вверх
            for (int row = field.getRows() - 1; row >= 0; row--) {
                Element current = field.getElement(row, col);
                Element empty = new Element("*");
                // Сдвигаем вниз
                if (!current.equals(empty)) {
                    if (row != emptyRow) {
                        field.setElement(current, emptyRow, col);
                        current.setCoordinates(emptyRow, col);
                        // Очищаем старую позицию
                        field.setElement(new Element("*", new Coordinates(row, col)), row, col);

                    }
                    emptyRow--;
                }
            }
        }
    }

    public void fillEmptyCells(Field field) {
        for (int i = 0; i < field.getRows(); i++) {
            for (int j = 0; j < field.getColumns(); j++) {
                Element current = field.getElement(i, j);
                if (current.equals(new Element("*"))) {
                    Element e = elementsFactory.createNewRandomElement();
                    e.setCoordinates(i, j);
                    field.grid[i][j] = e;
                }
            }
        }
    }

    @Override
    public boolean hasValidMoves(Field field) {
        int rows = field.getRows();
        int cols = field.getColumns();
        GameCommand command = new GameCommand();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Element current = field.getElement(i, j);

                // Проверка вправо
                if (j + 1 < cols) {
                    Element right = field.getElement(i, j + 1);
                    if (command.swapIsValid(field, current, right)) {
                        command.swapElements(field, current, right);
                        boolean match = hasMatches(field);
                        command.swapElements(field, current, right); // откат
                        if (match) return true;
                    }
                }

                // Проверка вниз
                if (i + 1 < rows) {
                    Element down = field.getElement(i + 1, j);
                    if (command.swapIsValid(field, current, down)) {
                        command.swapElements(field, current, down);
                        boolean match = hasMatches(field);
                        command.swapElements(field, current, down); // откат
                        if (match) return true;
                    }
                }
            }
        }

        return false;
    }
}
