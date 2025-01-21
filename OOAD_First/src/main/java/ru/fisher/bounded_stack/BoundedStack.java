package ru.fisher.bounded_stack;

import java.util.ArrayList;
import java.util.List;

abstract class BoundedStack<T> {

    // интерфейс класса, реализующий АТД Stack
    public static final int POP_NIL = 0; // pop() ещё не вызывался
    public static final int POP_OK = 1;  // Последний pop() выполнен успешно
    public static final int POP_ERR = 2; // Последний pop() выполнен с ошибкой (стек пуст)

    public static final int PEEK_NIL = 0; // peek() ещё не вызывался
    public static final int PEEK_OK = 1;  // Последний peek() выполнен успешно
    public static final int PEEK_ERR = 2; // Последний peek() выполнен с ошибкой (стек пуст)

    protected BoundedStack() {} // Конструктор

    // Команды

    // предусловие - стек не должен быть переполнен
    // постусловие - добавляем элемент в стек
    public abstract void push(T value);

    // предусловие - стек не пустой
    // постусловие - удален последний элемент из стека
    public abstract void pop(); // Удаляет элемент из стека

    public abstract void clear(); // Очищает стек

    // Запросы

    // предусловие - стек не пустой
    public abstract T peek(); // Возвращает верхний элемент стека без его удаления.
    public abstract int size(); // Возвращает текущее количество элементов в стеке

    // Запросы статусов
    public abstract int getPopStatus(); // Возвращает статус последней операции pop()
    public abstract int getPeekStatus(); // Возвращает статус последней операции peek()

}

class BoundedStackImpl<T> extends BoundedStack<T> {

    // Скрытые поля
    private List<T> stack;  // Основное хранилище стека
    private final int maxSize; // Максимальный размер стека по умолчанию 32
    private int popStatus; // Статус запроса pop()
    private int peekStatus; // Статус команды peek()

    // Конструктор с заданным размером стека
    // Постусловие - создан новый пустой стек с максимальным заданным размером
    public BoundedStackImpl(int maxSize) {
        this.maxSize = maxSize;
        clear();
    }

    // Конструктор по умолчанию
    // Постусловие - создан новый пустой стек с максимальным размером стека по умолчанию 32
    public BoundedStackImpl() {
        this(32);
        clear();
    }

    // Команды:

    // Предусловие - размер стека не больше maxSize
    // Постусловие - добавляется элемент в конец
    @Override
    public void push(T value) {
        if (size() < maxSize) {
            stack.add(value);
        }
    }

    // Предусловие - стек не пустой
    // Постусловие - из стека удален последний элемент
    @Override
    public void pop() {
        if (size() > 0) {
            stack.removeLast();
            popStatus = POP_OK;
        } else {
            popStatus = POP_ERR;
        }
    }

    // Постусловие: из стека удалятся все значения
    @Override
    public void clear() {
        stack = new ArrayList<>(); // Пустой список/стек

        // Начальные статусы для предусловий peek() и pop()
        peekStatus = PEEK_NIL;
        popStatus = POP_NIL;
    }

    // Запросы:

    // Предусловие: стек не пустой
    @Override
    public T peek() {
        if (size() > 0) {
            T result = stack.getLast();
            peekStatus = PEEK_OK;
            return result;
        } else {
            peekStatus = PEEK_ERR;
            return null;
        }
    }

    @Override
    public int size() {
        return stack.size();
    }

    // Запросы статусов

    @Override
    public int getPopStatus() {
        return popStatus;
    }

    @Override
    public int getPeekStatus() {
        return peekStatus;
    }

}
