package ru.fisher.hashtable;

import java.util.Arrays;

abstract class HashTable<T> {

    // интерфейс класса, реализующий АТД HashTable
    public static final int REMOVE_OK = 1;  // Последний remove() выполнен успешно
    public static final int REMOVE_ERR = 2; // Элемента нет в таблице (ошибка)

    public static final int PUT_OK = 1;  // Последний put() выполнен успешно
    public static final int PUT_ERR = 2; // Таблица заполнена
    public static final int PUT_COLLISION = 3; // Ошибка разрешения коллизий


    // Конструктор - создана пустая хеш-таблица
    public HashTable(int maxSize) {}


    // Команды:

    // Предусловие - в таблице имеется значение value
    // Постусловие - вставлено значение value в слот, вычисляемое с помощью функции поиска
    public abstract void put(T value);

    // Предусловие - таблица не пуста
    // Постусловие - удален элемент из таблицы
    public abstract void remove(T value);

    // Запросы:

    public abstract boolean get(T value); // есть ли значение в таблице

    // Доп. запросы
    public abstract int size();  // возвращает количество элементов в хэш-таблице
    public abstract int getPutStatus(); // Возвращает статус последней операции put()
    public abstract int getRemoveStatus(); // Возвращает статус последней операции remove()

}

class HashTableImpl<T> extends HashTable<T> {

    protected int count;
    protected int step;

    private T [] slots;

    private int putStatus;

    private int removeStatus;


    protected HashTableImpl(int maxSize, int stp) {
        super(maxSize);
        count = 0;
        step = stp;
        slots = (T[]) new Object[maxSize];
        Arrays.fill(slots, null);
    }

    // Команды:

    // Постусловие - возврат индекса слота для элемента, либо -1 если нет места
    public int seekSlot(T value) {
        int index = hashFun(value); // по входному значению сперва рассчитывает индекс слота хэш-функцией
        int startIndex = index;
        while (slots[index] != null) {
            if (slots[index].equals(value)) {
                return index; // отыскивает подходящий слот с учётом коллизий
            }
            index = (index + step) % slots.length;
            if (index == startIndex) {
                return -1; // возвращает неудачу, если это не удалось
            }
        }
        return index;
    }

    // Предусловие - в таблице есть место
    // Постусловие - вставлено значение value в слот, вычисляемое с помощью функции поиска
    @Override
    public void put(T value) {
        int index = seekSlot(value);
        if (index == -1) {
            putStatus = PUT_ERR;
            return;
        }
        if (slots[index] != null) {
            putStatus = PUT_COLLISION; // Обработка коллизии
        } else {
            putStatus = PUT_OK;
            slots[index] = value;
            count++;
        }
    }

    // Предусловие - элемент есть в таблице
    // Постусловие - элемент удален из таблицы
    @Override
    public void remove(T value) {
        int index = seekSlot(value);
        if (get(value)) {
            slots[index] = null;
            removeStatus = REMOVE_OK;
            count--;
        } else {
            removeStatus = REMOVE_ERR;
        }
    }

    // Запросы:

    public int hashFun(Object value) {
        return Math.abs(value.hashCode() % slots.length); // всегда возвращает корректный индекс слота
    }

    // Проверка наличия элемента
    @Override
    public boolean get(T value) {
        int index = seekSlot(value);
        return index != -1 && slots[index] != null;
    }

    @Override
    public int size() {
        return count; // возвращает количество элементов в хэш-таблице
    }

    // Доп. статусы

    @Override
    public int getPutStatus() {
        return putStatus;
    }

    @Override
    public int getRemoveStatus() {
        return removeStatus;
    }

}


