package ru.fisher.deque;

import java.util.ArrayList;
import java.util.List;

abstract class ParentQueue<T> {

    // Интерфейс класса, реализующий АТД ParentQueue
    public static final int REMOVE_FRONT_OK = 1;
    public static final int REMOVE_FRONT_ERR = 2;

    public static final int GET_FRONT_OK = 2;
    public static final int GET_FRONT_ERR = 2;



    // Конструктор
    protected ParentQueue() {}

    public abstract T getFront();
    public abstract void addTail(T value);
    public abstract void removeFront();

    public abstract int size();

    public abstract int getGetFrontStatus();
    public abstract int getRemoveFrontStatus();


}

abstract class Dequeue<T> extends ParentQueue<T> {

    // Интерфейс класса, реализующий АТД Dequeue
    public static final int REMOVE_TAIL_OK = 1;
    public static final int REMOVE_TAIL_ERR = 2;

    public static final int GET_TAIL_OK = 2;
    public static final int GET_TAIL_ERR = 2;

    protected Dequeue() {
        super();
    }

    public abstract T getTail();
    public abstract void addFront(T value);
    public abstract void removeTail();

    public abstract int getGetTailStatus();
    public abstract int getRemoveTailStatus();

}

class DequeImpl<T> extends Dequeue<T> {

    private final List<T> deque; // Основное хранилище очереди

    private int removeFrontStatus; // Получить статус getFront()
    private int removeTailStatus; // Получить статус getFront()
    private int getFrontStatus; // Получить статус getFront()
    private int getTailStatus; // Получить статус getTail()


    public DequeImpl() {
        deque = new ArrayList<>();
    }

    @Override
    public T getFront() {
        if (deque.isEmpty()) {
            getFrontStatus = GET_FRONT_ERR;
            return null;
        }
        getFrontStatus = GET_FRONT_OK;
        return deque.getFirst();
    }

    @Override
    public void addTail(T value) {
        deque.addLast(value);
    }

    @Override
    public void removeFront() {
        if (deque.isEmpty()) {
            removeFrontStatus = REMOVE_FRONT_ERR;
        }
        deque.removeFirst();
        removeFrontStatus = REMOVE_FRONT_OK;
    }

    @Override
    public T getTail() {
        if (deque.isEmpty()) {
            getTailStatus = GET_TAIL_ERR;
            return null;
        }
        getTailStatus = GET_TAIL_OK;
        return deque.getLast();
    }

    @Override
    public void addFront(T value) {
        deque.addFirst(value);
    }

    @Override
    public void removeTail() {
        if (deque.isEmpty()) {
            removeTailStatus = REMOVE_TAIL_ERR;
        }
        deque.removeLast();
        removeTailStatus = REMOVE_TAIL_OK;
    }

    @Override
    public int size() {
        return deque.size();
    }

    @Override
    public int getGetFrontStatus() {
        return getFrontStatus;
    }

    @Override
    public int getRemoveFrontStatus() {
        return removeFrontStatus;
    }

    @Override
    public int getGetTailStatus() {
        return getTailStatus;
    }

    @Override
    public int getRemoveTailStatus() {
        return removeTailStatus;
    }

    // Вспомогательный метод для тестов
    public void printDeque() {
        if (deque.isEmpty()) {
            System.out.println("Очередь пуста");
            return;
        }
        System.out.print("Элементы в очереди: ");
        for (T element : deque) {
            System.out.print(element + " ");
        }
        System.out.println(); // Переход на новую строку после вывода всех элементов
    }


}



