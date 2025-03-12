package ru.fisher.task16;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Weapon {

    String name;

    Weapon(String name) {
        this.name = name;
    }

    public void setName() {
        this.name = "Classic weapon";
    }

    public String getName() {
        return name;
    }
}

class Dagger extends Weapon {

    Dagger(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName() {
        this.name = "Dagger";
    }
}


class Person {

    Person() {  }

    public void speech() {
        System.out.println("This is Person");
    }

    public Weapon getWeapon() {
        return new Weapon("Person Weapon");
    }

}


class Hunter extends Person {

    @Override
    public void speech() {
        System.out.println("This is Hunter");
    }

    @Override
    public Dagger getWeapon() {
        return new Dagger("Dagger");
    }

}

public class Task16 {

    // Пример ковариантного вызова метода в списке
    // Мы заранее не знаем какой тип будет в списке
    static void process (List<? extends Person> list) {
        for (Person p : list) {
            p.speech();
            System.out.println(p.getWeapon().getName());
        }
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.speech();

        // Пример полиморфного присваивания
        person = new Hunter();
        person.speech(); // Пример полиморфного вызова метода

        System.out.println();

        // Пример ковариантного вызова в списке
        List<Person> list = new ArrayList<>();
        Person person1 = new Person();
        Hunter hunter1 = new Hunter();
        list.addAll(Arrays.asList(person1, hunter1));
        process(list);

    }

}
