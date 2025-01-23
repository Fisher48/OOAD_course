package ru.fisher.dynarray;


import java.lang.reflect.Array;


abstract class DynArray<T> {

    // Интерфейс класса, реализующий АТД DynArray
    public static final int GET_ITEM_OK = 1; // Объекта получен по его индексу
    public static final int GET_ITEM_ERR = 2; // Объекта не получен (выход за границы массива)

    public static final int INSERT_OK = 1; // Вставлен в i-ю позицию объект item, сдвигая вперёд все последующие элементы
    public static final int INSERT_ERR = 2; // Новый объект не вставлен в i-ю позицию (ошибка)

    public static final int REMOVE_OK = 1; // Объект успешно удален из i-й позиции, при необходимости выполняя сжатие буфера
    public static final int REMOVE_ERR = 2; // Объект не удален из i-й позиции


    // Конструктор
    // Постусловие - создан новый пустой динамический массив емкостью 16
    protected DynArray() { }


    // Команды

    // Постусловие - Размер массива array изменен (увеличен или уменьшен),
    // при необходимости скопированы элементы предыдущего массива
    public abstract void makeArray(int new_capacity);

    // Постусловие - добавлен новый элемент в конец массива
    public abstract void append(T itm);

    // Предусловие - индекс находится в пределах границы массива
    // Постусловие - добавлен новый элемент в указанное место массива,
    // сдвигая вперёд все последующие элементы, при необходимости буфер увеличен
    public abstract void insert(T itm, int index);

    // Предусловие - индекс находится в пределах границы массива
    // Постусловие - Объект удален из i-й позиции, при необходимости сжат буфера.
    public abstract void remove(int index);

    // Постусловие - из массива удаляются все значения
    public abstract void clear();


    // Запросы

    // Предусловие - индекс находится в пределах границы массива
    public abstract T getItem(int index); // получить объект по его индексу
    public abstract int size(); // посчитать количество элементов в массиве


    // Доп. запросы

    public abstract int getGetItemStatus(); // возвращает значение GET_ITEM_*
    public abstract int getInsertStatus(); // возвращает значение INSERT_*
    public abstract int getRemoveStatus(); // возвращает значение REMOVE_*

}

class DynArrImpl<T> extends DynArray<T> {

    private T [] array; // Поле для хранения массива
    private int count; // Текущее количество элементов в массиве
    private int capacity; // Текущая ёмкость буфера (исходно 16 единиц)
    private Class clazz; // Поле для хранения класса

    public static final int DEFAULT_CAPACITY = 16; // Ёмкость буфера по умолчанию 16

    private int insertStatus;
    private int removeStatus;
    private int getItemStatus;

    protected DynArrImpl(Class clz) {
        clazz = clz; // нужен для безопасного приведения типов
        capacity = DEFAULT_CAPACITY;
        count = 0;
        makeArray(capacity);
    }


    // Команды:

    @Override
    public void makeArray(int new_capacity) {
        T[] newArray = (T[]) Array.newInstance(clazz, new_capacity);
        if (array != null) {
            System.arraycopy(array, 0, newArray, 0, count);
        }
        array = newArray;
        capacity = new_capacity;
    }

    @Override
    public void append(T itm) {
        if (count == capacity) {
            growArr();
        }
        array[count++] = itm;
    }

    @Override
    public void insert(T itm, int index) {
        if (index < 0 || index > count) {
            insertStatus = INSERT_ERR;
            return;
        }
        if (count == capacity) {
            growArr();
        }
        System.arraycopy(array, index, array, index + 1, count - index);
        array[index] = itm;
        count++;
        insertStatus = INSERT_OK;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= count) {
            removeStatus = REMOVE_ERR;
            return;
        }
        System.arraycopy(array, index + 1, array, index, count - index - 1);
        array[count - 1] = null; // Очищаем последний элемент
        count--;
        removeStatus = REMOVE_OK;
        if (count < (capacity / 2)) {
            capacity = (int) (capacity / 1.5);
            if (capacity < DEFAULT_CAPACITY) {
                capacity = DEFAULT_CAPACITY;
            }
            makeArray(capacity); // Уменьшаем емкость массива
        }
    }

    @Override
    public void clear() {
        count = 0;
        makeArray(DEFAULT_CAPACITY);
    }

    // Доп. метод увеличения размера массива
    // Постусловие - размер буфера увеличен
    public void growArr() {
        capacity *= 2;
        makeArray(capacity);
    }

    // Запросы:

    @Override
    public int size() {
        return count;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public T getItem(int index) {
        if (index >= 0 && index < count) {
            getItemStatus = GET_ITEM_OK;
            return array[index];
        }
        getItemStatus = GET_ITEM_ERR;
        return null;
    }


    // Доп. запросы

    @Override
    public int getGetItemStatus() {
        return getItemStatus;
    }

    @Override
    public int getInsertStatus() {
        return insertStatus;
    }

    @Override
    public int getRemoveStatus() {
        return removeStatus;
    }

}
