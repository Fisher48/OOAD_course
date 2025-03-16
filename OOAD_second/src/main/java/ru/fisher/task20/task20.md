Решение 20-го задания:

1). Наследования вариаций

````java
class Animal {

    String name;
    Animal() {}

    public void scream() {
        System.out.println("I'm animal!");
    }
}

class Bird extends Animal {

    Bird() { super(); }

    // Наследование с функциональной вариацией
    @Override
    public void scream() {
        System.out.println("I'm bird");
    }
}

class Cat extends Animal {

    Cat () { super(); }

    // Наследование с вариацией типа
    public void scream(String scream) {
        System.out.println("I'm cat and " + scream);
    }
}
````
2). Наследование с конкретизацией (reification inheritance)

````java
abstract class Vehicle {
    Vehicle() {}
    abstract void drive();
}

class Car extends Vehicle {
    // Пример наследования с конкретизацией
    @Override
    void drive() {
        System.out.println("Car start driving!");
    }
}
````

3). Структурное наследование (structure inheritance)

````java

// Создаем интерфейс сложения
interface Summable<T> {
    T sum(T item1, T item2);
}

class List<T> implements Summable<T> {
    int size;
    int pos;
    T[] array;

    List(int size) {
        this.size = size;
        this.pos = 0;
        this.array = (T[]) new Object[size];
    }
    
    // Реализуем интерфейс сложения в списке
    @Override
    public T sum(T item1, T item2) {
        if (item1 instanceof Integer && item2 instanceof Integer) {
            return (T) (Integer) ((int) item1 + (int) item2);
        }
        if (item1 instanceof Double && item2 instanceof Double) {
            return (T) (Double) ((double) item1 + (double) item2);
        }
        if (item1 instanceof String && item2 instanceof String) {
            return (T) ((String) item1 + (String) item2);
        }
        return null;
    }
}

````
