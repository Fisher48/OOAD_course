package ru.fisher.task14;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


// Интерфейс для объектов, которые можно складывать
interface Summable<T> {
    T add(T other);
}

// Абстрактный базовый класс General
abstract class General<T extends General<T>> implements Serializable {

}

// класс Vector в котором определена операция сложения
class Vector<T extends General<T> & Summable<T>> extends General<Vector<T>> implements Summable<Vector<T>> {

    private List<T> vector;

    public Vector(List<T> vector) {
        this.vector = vector;
    }

    public Vector() {
        this.vector = new ArrayList<>();
    }

    public void set(T value) {
        vector.add(value);
    }

    public Vector<T> add(Vector<T> other) {
        if (!this.getClass().isAssignableFrom(other.getClass())) {
            return null;
        }
        if (this.vector.size() != other.vector.size()) {
            return null;
        }
        List<T> result = new ArrayList<>();
        for (int i = 0; i < vector.size(); i++) {
            T element1 = this.vector.get(i);
            T element2 = other.vector.get(i);
            T sum = element1.add(element2);
            result.add(sum);
        }
        return new Vector<>(result);
    }

    @Override
    public String toString() {
        return vector.toString();
    }

}

// Пример класса, который реализует интерфейс Summable
class IntegerValue extends General<IntegerValue> implements Summable<IntegerValue> {
    private int value;

    public IntegerValue(int value) {
        this.value = value;
    }

    @Override
    public IntegerValue add(IntegerValue other) {
        return new IntegerValue(this.value + other.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}


public class Task14 {

    public static void main(String[] args) {
        // Создаем два вектора IntegerValue
        Vector<IntegerValue> vector1 = new Vector<>();
        vector1.set(new IntegerValue(10));
        vector1.set(new IntegerValue(20));
        vector1.set(new IntegerValue(30));

        Vector<IntegerValue> vector2 = new Vector<>();
        vector2.set(new IntegerValue(5));
        vector2.set(new IntegerValue(15));
        vector2.set(new IntegerValue(25));

        // Складываем векторы
        Vector<IntegerValue> sumVector = vector1.add(vector2);

        // Выводим результат
        System.out.println("Вектор 1: " + vector1);
        System.out.println("Вектор 2: " + vector2);
        System.out.println("Сумма Векторов: " + sumVector);

        // Создаем векторы векторов IntegerValue
        Vector<Vector<IntegerValue>> firstVector2D = new Vector<>();
        firstVector2D.set(new Vector<>(List.of(new IntegerValue(100), new IntegerValue(200), new IntegerValue(300))));
        firstVector2D.set(new Vector<>(List.of(new IntegerValue(400), new IntegerValue(500), new IntegerValue(600))));

        Vector<Vector<IntegerValue>> secondVector2D = new Vector<>();
        secondVector2D.set(new Vector<>(List.of(new IntegerValue(700), new IntegerValue(800), new IntegerValue(900))));
        secondVector2D.set(new Vector<>(List.of(new IntegerValue(1000), new IntegerValue(100), new IntegerValue(200))));

        // Складываем векторы векторов
        Vector<Vector<IntegerValue>> sumVector2D = firstVector2D.add(secondVector2D);

        // Выводим результат
        System.out.println("Вектор 2D 1: " + firstVector2D);
        System.out.println("Вектор 2D 2: " + secondVector2D);
        System.out.println("Сумма Вектор 2D: " + sumVector2D);



        // Создаем векторы векторов, векторов IntegerValue
        Vector<Vector<Vector<IntegerValue>>> firstVector3D = new Vector<>();
        firstVector3D.set(firstVector2D);
        firstVector3D.set(secondVector2D);

        Vector<Vector<Vector<IntegerValue>>> secondVector3D = new Vector<>();
        secondVector3D.set(secondVector2D);
        secondVector3D.set(firstVector2D);

        // Складываем векторы векторов, векторов
        Vector<Vector<Vector<IntegerValue>>> sumVector3D = firstVector3D.add(secondVector3D);

        // Выводим результат
        System.out.println("Вектор 3D 1: " + firstVector3D);
        System.out.println("Вектор 3D 2: " + secondVector3D);
        System.out.println("Сумма Вектор 3D: " + sumVector3D);



        // Проверка на null при сложении векторов с разной длинной
        Vector<IntegerValue> vector3 = new Vector<>();
        vector3.set(new IntegerValue(1));

        Vector<IntegerValue> sumVector2 = vector1.add(vector3);

        if (sumVector2 != null) {
            System.out.println("sumVector2 : " + sumVector2);
        } else {
            System.out.println("Векторы с разными размерами");
        }

    }

}
