package ru.fisher.nativedictionary;

import java.util.Arrays;

abstract class NativeDictionary<T> {

    // Интерфейс класса, реализующий АТД NativeDictionary
    public static final int REMOVE_OK = 1;  // Последний remove() выполнен успешно
    public static final int REMOVE_ERR = 2; // Последний remove() выполнен с ошибкой (ключа не найдено)

    public static final int PUT_OK = 1;  // Добавлена новая пара ключ-значение
    public static final int PUT_ERR = 2; // Обновлено значение существующего ключа

    // Конструктор - создан пустой словарь
    public NativeDictionary() {}

    // Команды:

    // Постусловие - Вставлено значение по ключу и ключ
    // Если ключ бы, то обновляется значение
    public abstract void put(String key, T value);

    // Предусловие - Словарь не пуст
    // Постусловие - Удален элемент из словаря
    public abstract void remove(String key);


    // Запросы:

    public abstract T getValue(String key); // Получить значение из словаря по ключу

    // Доп. запросы
    public abstract int size();  // возвращает количество элементов в словаре
    public abstract int getPutStatus(); // Возвращает статус последней операции put()
    public abstract int getRemoveStatus(); // Возвращает статус последней операции remove()

}

class Map<T> extends NativeDictionary<T> {

    private int count;
    private String[] keys;
    private T[] values;

    private int putStatus;
    private int removeStatus;

    // Конструктор
    protected Map(int sz) {
        count = 0;
        keys = new String[sz];
        values = (T[]) new Object[sz];
        Arrays.fill(values, null);
    }

    // Команды:

    // Постусловие - вставлено значение по ключу и ключ
    @Override
    public void put(String key, T value) {
        int index = hashFun(key);
        if (values[index] != null) {
            values[index] = value;
            keys[index] = key;
            putStatus = PUT_ERR; // Произошла перезапись ключа и значения
            return;
        }
        values[index] = value;
        keys[index] = key;
        putStatus = PUT_OK; // Добавлена новая запись ключ-значение
        count++;
    }

    // Предусловие - в словаре есть ключ key
    // Постусловие - значение удалено по ключу и сам ключ
    @Override
    public void remove(String key) {
        int index = hashFun(key);
        if (values[index] != null) {
            values[index] = null;
            keys[index] = null;
            removeStatus = REMOVE_OK;
            count--;
            return;
        }
        removeStatus = REMOVE_ERR;
    }

    // Запросы

    // Предусловие - есть такой ключ key в словаре
    @Override
    public T getValue(String key) {
        int index = hashFun(key);
        return values[index]; // Получить значение по ключу
    }

    public boolean isKey(String key) {
        int index = hashFun(key);
        return key.equals(keys[index]); // Проверка наличия ключа
    }

    // Доп. запросы
    @Override
    public int size() {
        return count; // возвращает кол-во элементов в словаре
    }

    public int hashFun(String key) {
        return Math.abs(key.hashCode() % values.length);
    }

    // Статусы

    @Override
    public int getPutStatus() {
        return putStatus; // возвращает статус вставки значения по ключу PUT_*
    }

    @Override
    public int getRemoveStatus() {
        return removeStatus; // возвращает статус удаления значения REMOVE_*
    }

}