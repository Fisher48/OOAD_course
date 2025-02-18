package ru.fisher.task1;

import java.util.ArrayList;
import java.util.List;

abstract class Animal {

    String name;
    int age;
    String voice;

    protected Animal() {}

    protected Animal(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public int getAge() { return this.age; }
    public String getVoice() { return this.voice; }


    // Пример полиморфизма
    // Переопределенный метод голоса для класса Cat
    public abstract void setVoice();
}

// Пример наследования
// класс Cat является потомком (наследником) класса Animal
class Cat extends Animal {

    protected Cat(int age, String name) {
        super(age, name);
    }

    public Cat() {}

    // Частота мурлыкания свойственна только коту, поэтому определятся уже не в классе родителе, а в потомке
    int purr;

    public int getPurr() { return purr; }

    public void setPurr(int purr) { this.purr = purr; }

    // Пример полиморфизма
    // Переопределенный метод голоса для класса Cat
    @Override
    public void setVoice() { this.voice = "Meow"; }

}

// Пример наследования
// класс Dog является потомком (наследником) класса Animal
class Dog extends Animal {

    protected Dog(int age, String name) {
        super(age, name);
    }

    public Dog() {}

    // Переопределенный метод голоса для класса Dog
    @Override
    public void setVoice() { this.voice = "Woof"; }
}

// Пример Композиции, Зоопарк содержит животных
class Zoo {

    List<Animal> animalList;

    protected Zoo() { animalList = new ArrayList<>(); }

    public void addAnimalInZoo(Animal animal) { animalList.add(animal); }

    public void getAllAnimals() {
        System.out.println("Список животных в зоопарке:");
        for (Animal animal : animalList) {
            System.out.print(animal.getName() + " ");
        }
    }

}

public class Task1 {

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.setName("Стрелка");
        dog.setAge(3);
        dog.setVoice();

        Dog dog2 = new Dog(2, "Белка");
        dog2.setVoice();

        Cat cat = new Cat(14, "Барсик");
        cat.setVoice();
        cat.setPurr(10);

        Cat cat2 = new Cat();
        cat2.setName("Сержик");
        cat2.setAge(1);
        cat2.setVoice();
        cat2.setPurr(8);

        // Пример композиции - зоопарк содержит животных
        Zoo zoo = new Zoo();
        zoo.addAnimalInZoo(dog);
        zoo.addAnimalInZoo(dog2);
        zoo.addAnimalInZoo(cat);
        zoo.addAnimalInZoo(cat2);
        zoo.getAllAnimals();
        System.out.println();

        // Пример полиморфизма, при установке метода setVoice() голос устанавливается в зависимости от того, к какому классу применяется метод
        System.out.println(dog.getName() + " " + dog.getVoice());
        System.out.println(cat.getName() + " " + cat.getVoice());
        System.out.println("Частота мурлыкания - " + cat.getPurr());
        System.out.println(dog2.getName() + " " + dog2.getVoice());
        System.out.println(cat2.getName() + " " + cat2.getVoice());

    }

}


