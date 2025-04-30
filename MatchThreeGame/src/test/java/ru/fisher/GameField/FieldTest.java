package ru.fisher.GameField;

import org.junit.jupiter.api.Test;


class FieldTest {

    @Test
    void initFieldTest() {
        Field field = new Field();
        field.initField();
        field.renderFiled();
        System.out.println();
    }

    @Test
    void getElemetByCoordinates() {
        Field field = new Field();
        field.initField();
        field.renderFiled();
        System.out.println();
        System.out.println(field.getElement(7, 6));
    }


}