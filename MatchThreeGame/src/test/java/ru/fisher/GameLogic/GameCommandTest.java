package ru.fisher.GameLogic;

import org.junit.jupiter.api.Test;
import ru.fisher.GameField.Coordinates;
import ru.fisher.GameField.Element;
import ru.fisher.GameField.Field;

import static org.junit.jupiter.api.Assertions.*;

class GameCommandTest {

    @Test
    void swapValidInHorizontalTest() {
        Field field = new Field(5, 5);
        Coordinates c1 = new Coordinates(4, 3);
        Coordinates c2 = new Coordinates(4, 4);

        Element e1 = new Element("*", c1);
        Element e2 = new Element("-", c2);

        field.setElement(e1, c1.getX(), c1.getY());
        field.setElement(e2, c2.getX(), c2.getY());

        GameCommand command = new GameCommand();

        field.renderFiled();  // чтобы увидеть поле до
        System.out.println();

        command.swapElements(field, e1, e2);
        assertTrue(command.swapIsValid(field, e1, e2));

        field.renderFiled();  // поле после перестановки
    }

    @Test
    void swapValidInVerticalTest() {
        Field field = new Field(5, 5);
        Coordinates c1 = new Coordinates(0, 0);
        Coordinates c2 = new Coordinates(1, 0);

        Element e1 = new Element("*", c1);
        Element e2 = new Element("-", c2);

        field.setElement(e1, c1.getX(), c1.getY());
        field.setElement(e2, c2.getX(), c2.getY());

        GameCommand command = new GameCommand();

        field.renderFiled();  // чтобы увидеть поле до
        System.out.println();

        command.swapElements(field, e1, e2);
        assertTrue(command.swapIsValid(field, e1, e2));

        field.renderFiled();  // поле после перестановки
    }

    @Test
    void swapNotValidTest() {
        Field field = new Field(5, 5);
        Coordinates c1 = new Coordinates(3, 3);
        Coordinates c2 = new Coordinates(4, 4);
        Element e1 = new Element("*", c1);
        Element e2 = new Element("^", c2);

        field.setElement(e1, c1.getX(), c1.getY());
        field.setElement(e2, c2.getX(), c2.getY());

        GameCommand command = new GameCommand();

        field.renderFiled();  // чтобы увидеть поле до
        System.out.println();

        command.swapElements(field, e1, e2);
        assertFalse(command.swapIsValid(field, e1, e2));

        field.renderFiled();  // поле после перестановки
    }

}