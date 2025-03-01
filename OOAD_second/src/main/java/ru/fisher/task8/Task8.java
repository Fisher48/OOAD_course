package ru.fisher.task8;


import java.util.ArrayList;
import java.util.List;

class Animal {

    protected Animal() {}

    public Animal createAnimal() {
        return new Animal();
    }

    void scream() { System.out.println("I'm Animal!"); }

}

class Bird extends Animal {

    protected Bird() {
        super();
    }

    // Пример ковариантности:
    // переопределённый метод родительского класса может возвращать значение типа,
    // который есть потомок типа значения, возвращаемого родительским методом
    @Override
    public Bird createAnimal() {
        return new Bird();
    }

    @Override
    void scream() {
        System.out.println("I'm a Bird!");
    }

}

class Crow extends Bird {

    protected Crow() {
        super();
    }

    @Override
    void scream() {
        System.out.println("I'm a Crow!");
    }

}

// Интерфейс для обработки животных
interface AnimalProcessor {
    void process(Animal animal); // принимает объект типа Animal
}

// Реализация интерфейса для обработки животных
class AnimalHandler implements AnimalProcessor {
    @Override
    public void process(Animal animal) {
        animal.scream(); // Вызываем метод scream для животного
    }
}

// Класс, который демонстрирует контравариантность
class AnimalList<T extends Animal> {
    private List<T> animals = new ArrayList<>();

    public void addAnimal(T animal) {
        animals.add(animal);
    }

    // Метод, который принимает контравариантный тип
    public void processAnimals(AnimalProcessor processor) {
        for (T animal : animals) {
            processor.process(animal); // Обработка каждого животного
        }
    }
}

public class Task8 {

    public static void main(String[] args) {

        // Создаем список животных
        AnimalList<Bird> birdList = new AnimalList<>();
        birdList.addAnimal(new Bird());
        birdList.addAnimal(new Crow()); // Crow является подтипом Bird

        // Создаем обработчик животных
        AnimalProcessor processor = new AnimalHandler();

        // Обрабатываем животных в списке
        birdList.processAnimals(processor); // Вывод: I'm a Bird! и I'm a Crow!

    }

}
