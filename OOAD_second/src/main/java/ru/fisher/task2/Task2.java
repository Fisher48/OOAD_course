package ru.fisher.task2;

public class Task2 {

    // Родительский класс
    class Transport {

        int speed;
        int fuelCapacity;

        public Transport() { super(); }

    }

    class Engine {}

    // Класс наследник
    // Расширение класса-родителя (наследник задаёт более общий случай родителя)
    class Motorcycle extends Transport {

        Engine engine; // Класс мотоцикла, содержит двигатель, делая общий случай класса транспорт

        Motorcycle() { super(); }

    }

    class Door {}

    // Класс наследник
    // Специализация класса-родителя (наследник задаёт более специализированный случай родителя)
    class Car extends Transport {

        Engine engine;

        Door door; // Задается специализированный случай,
                   // т.к у машины бывают двери, а у мотоциклов нет

        Car() { super(); }

    }

}
