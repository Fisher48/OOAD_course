package ru.fisher.Task8;


// Поле с массивом элементов 8x8
public abstract class Field {

    Element[][] elements;

    public abstract boolean fieldIsEmpty();

    public abstract void getElement();

    public abstract void initField();

}
