package ru.fisher.GameField;

import java.util.Random;

public class ElementsFactory {

    Type type = new Type();
    Random random = new Random();

    public Element createNewRandomElement() {
        Element element = new Element(type.types[random.nextInt(type.types.length)]);
        return element;
    }

}
