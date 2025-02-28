package ru.fisher.task7;

import java.util.ArrayList;
import java.util.List;

abstract class Animal {

    protected Animal() {}

    abstract void scream();

}

class Bird extends Animal {

    protected Bird() {
        super();
    }

    @Override
    void scream() {
        System.out.println("I'm a bird!");
    }
}

class Cat extends Animal {

    protected Cat() {
        super();
    }

    @Override
    void scream() {
        System.out.println("I'm a cat!");
    }
}

public class Task7 {

    public static void main(String[] args) {

        // Создаем сущности котика и птички
        Animal cat = new Cat();
        Animal bird = new Bird();

        // Создаем список, в котором будут животные
        List<Animal> animals = new ArrayList<>();

        // Заполняем список животными
        animals.add(cat);
        animals.add(bird);

        // Проходясь по списку мы вызываем метод scream(), при этом
        // мы не указываем явно метод какого конкретно типа (класса)
        // должен быть вызван, он будет определён автоматически.
        for (Animal a : animals) {
            a.scream();
        }

    }

}
