package ru.fisher.GameLogic;

import org.junit.jupiter.api.Test;
import ru.fisher.GameField.Coordinates;
import ru.fisher.GameField.Element;
import ru.fisher.GameField.Field;

import static org.junit.jupiter.api.Assertions.*;

class GameRulesTest {

    @Test
    void hasMatchesTest() {
        Field field = new Field(8, 8);
        GameRules rules = new GameRules();
        field.initField();
        field.renderFiled();
        Element e1 = new Element("F", new Coordinates(3, 3));
        Element e2 = new Element("F", new Coordinates(3, 4));
        Element e3 = new Element("F", new Coordinates(3, 5));
        field.setElement(e1, 3, 3);
        field.setElement(e2, 3, 4);
        field.setElement(e3, 3, 5);
        System.out.println();
        field.renderFiled();
        assertTrue(rules.hasMatches(field));
    }

    @Test
    void removeMatchesTest() {
        Field field = new Field(8, 8);
        GameRules rules = new GameRules();
        System.out.println("Создано поле:");
        field.initField();
        field.renderFiled();
        Element e1 = new Element("F", new Coordinates(3, 3));
        Element e2 = new Element("F", new Coordinates(3, 4));
        Element e3 = new Element("F", new Coordinates(3, 5));
        field.setElement(e1, 3, 3);
        field.setElement(e2, 3, 4);
        field.setElement(e3, 3, 5);
        System.out.println("Вставлены 3 F элемента");
        field.renderFiled();
        rules.removeMatch(field);
        System.out.println("Удалены совпадения:");
        field.renderFiled();
        rules.shiftElementsDown(field);
        System.out.println("Сдвиг вниз после удаления совпадений:");
        field.renderFiled();
        rules.fillEmptyCells(field);
        System.out.println("Поле заполнено после сдвига элементов");
        field.renderFiled();
    }

    @Test
    void testHasValidMoves_true_whenSwapCreatesMatch() {
        GameRules rules = new GameRules();

        // Поле 3x3:
        // A B A
        // B A B
        // A B A
        Field field = new Field(3, 3);
        field.setElement(new Element("A", new Coordinates(0, 0)), 0, 0);
        field.setElement(new Element("B", new Coordinates(0, 1)), 0, 1);
        field.setElement(new Element("A", new Coordinates(0, 2)), 0, 2);
        field.setElement(new Element("B", new Coordinates(1, 0)), 1, 0);
        field.setElement(new Element("A", new Coordinates(1, 1)), 1, 1);
        field.setElement(new Element("B", new Coordinates(1, 2)), 1, 2);
        field.setElement(new Element("A", new Coordinates(2, 0)), 2, 0);
        field.setElement(new Element("B", new Coordinates(2, 1)), 2, 1);
        field.setElement(new Element("A", new Coordinates(2, 2)), 2, 2);

        // Если поменять (0,1) B и (1,1) A — будет матч "A A A" в столбце 1
        assertTrue(rules.hasValidMoves(field));
    }

    @Test
    void testHasValidMoves_false_whenNoPossibleMatch() {
        GameRules rules = new GameRules();

        // Поле 3x3 без возможных ходов:
        // A B C
        // C A B
        // B C A
        Field field = new Field(3, 3);
        field.setElement(new Element("A", new Coordinates(0, 0)), 0, 0);
        field.setElement(new Element("B", new Coordinates(0, 1)), 0, 1);
        field.setElement(new Element("C", new Coordinates(0, 2)), 0, 2);
        field.setElement(new Element("C", new Coordinates(1, 0)), 1, 0);
        field.setElement(new Element("A", new Coordinates(1, 1)), 1, 1);
        field.setElement(new Element("B", new Coordinates(1, 2)), 1, 2);
        field.setElement(new Element("B", new Coordinates(2, 0)), 2, 0);
        field.setElement(new Element("C", new Coordinates(2, 1)), 2, 1);
        field.setElement(new Element("A", new Coordinates(2, 2)), 2, 2);

        assertFalse(rules.hasValidMoves(field));
    }


}