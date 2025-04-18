package ru.fisher.Task8;

// Элемент в игровом поле
public class Element {
    Type type;
    Coordinates coordinates;
}

// Тип элемента
class Type {
    String[] types = {"A", "B", "C", "D", "E", "F", "G", "H"} ;
}

// Координаты элемента в игровом поле
class Coordinates {
    Integer x;
    Integer y;
}
