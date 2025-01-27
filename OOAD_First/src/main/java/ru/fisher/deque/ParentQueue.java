package ru.fisher.deque;

import java.util.ArrayList;
import java.util.List;

abstract class ParentQueue<T> {

    // Интерфейс класса, реализующий АТД ParentQueue
    public static final int REMOVE_FRONT_OK = 1; // последняя removeFront() отработала нормально
    public static final int REMOVE_FRONT_ERR = 2; // последняя removeFront() отработала с ошибкой (очередь пуста)

    public static final int GET_FRONT_OK = 1; // Последний getFront() выполнен успешно (успешно получен элемент)
    public static final int GET_FRONT_ERR = 2; // Последний getFront() выполнен с ошибкой (очередь пуста)


    // Конструктор, создана пустая очередь
    protected ParentQueue() {}

    public abstract T getFront(); // Возвращает элемент с головы очереди без его удаления
    public abstract void addTail(T value); // Добавляет элемент в хвост очереди
    public abstract void removeFront(); // Удаляет элемент из начала очереди

    public abstract int size(); // Возвращает текущий размер очереди

    public abstract int getGetFrontStatus(); // возвращает значение GET_FRONT_*
    public abstract int getRemoveFrontStatus(); // возвращает значение REMOVE_FRONT_*


}

abstract class Dequeue<T> extends ParentQueue<T> {

    // Интерфейс класса, реализующий АТД Dequeue
    public static final int REMOVE_TAIL_OK = 1; // последняя removeTail() отработала нормально
    public static final int REMOVE_TAIL_ERR = 2; // последняя removeTail() отработала с ошибкой (очередь пуста)

    public static final int GET_TAIL_OK = 1; // Последний getTail() выполнен ок (успешно получен элемент с хвоста)
    public static final int GET_TAIL_ERR = 2; // Последний getTail() выполнен с ошибкой (очередь пуста)

    // Конструктор, создана пустая очередь
    protected Dequeue() {
        super();
    }

    public abstract T getTail(); // Возвращает элемент из хвоста очереди без его удаления
    public abstract void addFront(T value); // Добавляет элемент в голову очереди
    public abstract void removeTail(); // Удаляет элемент из хвоста очереди

    public abstract int getGetTailStatus(); // возвращает значение GET_TAIL_*
    public abstract int getRemoveTailStatus(); // возвращает значение REMOVE_TAIL_*

}

class DequeImpl<T> extends Dequeue<T> {

    private final List<T> deque; // Основное хранилище очереди

    private int removeFrontStatus; // Получить статус removeFront()
    private int removeTailStatus; // Получить статус removeTail()
    private int getFrontStatus; // Получить статус getFront()
    private int getTailStatus; // Получить статус getTail()


    // Конструктор - создана пустая очередь
    public DequeImpl() {
        deque = new ArrayList<>();
    }

    // Команды:

    // Постусловие - добавлен элемент в хвост очереди
    @Override
    public void addTail(T value) {
        deque.addLast(value);
    }

    // Постусловие - добавлен элемент в начало очереди
    @Override
    public void addFront(T value) {
        deque.addFirst(value);
    }

    // Предусловие - очередь не пуста
    // Постусловие - удален элемент из начала очереди
    @Override
    public void removeFront() {
        if (deque.isEmpty()) {
            removeFrontStatus = REMOVE_FRONT_ERR;
        }
        deque.removeFirst();
        removeFrontStatus = REMOVE_FRONT_OK;
    }

    // Предусловие - очередь не пуста
    // Постусловие - удален элемент из хвоста очереди
    @Override
    public void removeTail() {
        if (deque.isEmpty()) {
            removeTailStatus = REMOVE_TAIL_ERR;
        }
        deque.removeLast();
        removeTailStatus = REMOVE_TAIL_OK;
    }

    // Предусловие - очередь не пуста
    @Override
    public T getTail() {
        if (deque.isEmpty()) {
            getTailStatus = GET_TAIL_ERR;
            return null;
        }
        getTailStatus = GET_TAIL_OK;
        return deque.getLast();
    }

    // Предусловие - очередь не пуста
    @Override
    public T getFront() {
        if (deque.isEmpty()) {
            getFrontStatus = GET_FRONT_ERR;
            return null;
        }
        getFrontStatus = GET_FRONT_OK;
        return deque.getFirst();
    }

    // Доп. запросы
    // Возвращает текущий размер очереди
    @Override
    public int size() {
        return deque.size();
    }

    // Доп. статусы
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



