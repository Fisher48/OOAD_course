package ru.fisher.powerset;

import ru.fisher.hashtable.HashTable;

abstract class PowerSet<T> extends HashTable<T> {

    // Конструктор - создано пустое множество заданного размера
    protected PowerSet(int maxSize) {
        super(maxSize);
    }

    // Запросы:

    // Постусловие - возвращает пересечение множества и set2
    public abstract PowerSet<T> intersection(PowerSet<T> set2); // возвращает пересечение исходного и set множеств

    // Постусловие - возвращает объединение множества и set2
    public abstract PowerSet<T> union(PowerSet<T> set2); // возвращает объединение исходного и set множеств

    // Постусловие - возвращает разницу множества и set2
    public abstract PowerSet<T> difference(PowerSet<T> set2); // возвращает разницу исходного и set множеств

    public abstract boolean isSubset(PowerSet<T> set2); // проверка, является ли set подмножеством исходного множества

}



class PowerSetImpl<T> extends PowerSet<T> {

    private T[] values; // Массив для хранения элементов
    private int count; // Текущее количество элементов

    private int removeStatus;
    private int putStatus;

    public PowerSetImpl(int size) {
        super(size);
        this.count = 0;
        this.values = (T[]) new Object[size];
    }


    @Override
    public void put(T value) {
        if (count >= this.values.length) {
            putStatus = PUT_ERR;
            throw new IllegalStateException("Множество переполнено");
        }
        int index = hashFun(value);
        if (values[index] == null) {
            values[index] = value;
            count++;
            putStatus = PUT_OK;
        }
    }

    // Предусловие - элемент есть в таблице
    // Постусловие - элемент удален из таблицы
    @Override
    public void remove(T value) {
        int index = hashFun(value);
        if (get(value)) {
            values[index] = null;
            removeStatus = REMOVE_OK;
            count--;
        } else {
            removeStatus = REMOVE_ERR;
        }
    }

    // Проверка наличия элемента
    @Override
    public boolean get(T value) {
        int index = hashFun(value);
        return values[index] != null;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public int getPutStatus() {
        return putStatus;
    }

    @Override
    public int getRemoveStatus() {
        return removeStatus;
    }

    @Override
    public PowerSet<T> intersection(PowerSet<T> set2) {
        PowerSetImpl<T> intersectionSet = new PowerSetImpl<>(this.size());
        for (T value : values) {
            if (value != null && set2.get(value)) {
                intersectionSet.put(value);
            }
        }
        return intersectionSet;
    }

    @Override
    public PowerSetImpl<T> union(PowerSet<T> set2) {
        PowerSetImpl<T> unionSet = new PowerSetImpl<>(this.size() + set2.size());
        for(T slot : this.values) {
            if (slot != null && !set2.get(slot))
                unionSet.put(slot);
        }
        for(T slot : ((PowerSetImpl<T>) set2).values) {
            if (slot != null && !this.get(slot))
                unionSet.put(slot);
        }
        return unionSet;
    }

    @Override
    public PowerSet<T> difference(PowerSet<T> set2) {
        PowerSetImpl<T> differenceSet = new PowerSetImpl<>(this.size());
        for (T value : values) {
            if (value != null && !set2.get(value)) {
                differenceSet.put(value);
            }
        }
        return differenceSet;
    }

    @Override
    public boolean isSubset(PowerSet<T> set2) {
        for (T value : ((PowerSetImpl<T>) set2).values) {
            if (value != null && !this.get(value)) {
                return false;
            }
        }
        return true;
    }

    public int hashFun(Object value) {
        return Math.abs(value.hashCode() % values.length); // всегда возвращает корректный индекс слота
    }

}


