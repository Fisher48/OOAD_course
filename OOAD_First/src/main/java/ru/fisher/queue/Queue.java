package ru.fisher.queue;

import java.util.ArrayList;
import java.util.List;

abstract class Queue<T> {

    // Интерфейс класса, реализующий АТД Queue
    public static final int DEQUEUE_OK = 1;  // Последний dequeue() выполнен успешно (успешно добавлен элемент)
    public static final int DEQUEUE_ERR = 2; // Последний dequeue() выполнен с ошибкой (очередь пуста)

    public static final int GET_OK = 1;  // Последний get() выполнен успешно (успешно получен элемент)
    public static final int GET_ERR = 2; // Последний get() выполнен с ошибкой (очередь пуста)

    // Постусловие - Создана пустой список
    protected Queue() {} // Конструктор

    // Команды

    // Постусловие - Добавлен элемент в конец очереди
    public abstract void enqueue(T value);

    // Предусловие - Очередь не пустая
    // Постусловие - Удален элемент из начала очереди
    public abstract void dequeue();


    // Запросы

    // Предусловие - Очередь не пустая
    public abstract T get(); // Возвращает первый элемент из очереди без его удаления
    public abstract int size(); // Возвращает текущее количество элементов в очереди

    // Запросы статусов
    public abstract int getDequeueStatus(); // Возвращает статус последней операции dequeue()
    public abstract int getGetStatus(); // Возвращает статус последней операции get()

}

class QueueImpl<T> extends Queue<T> {

    // Скрытые поля
    private final List<T> queue;  // Основное хранилище очереди
    private int dequeueStatus; // Статус команды dequeue()
    private int getStatus; // Статус запроса get()


    public QueueImpl() {
        queue = new ArrayList<>();
    }


    @Override
    public void enqueue(T value) {
        queue.addLast(value);
    }

    @Override
    public void dequeue() {
        if (queue.isEmpty()) {
            dequeueStatus = DEQUEUE_ERR;
            return;
        }
        queue.removeFirst();
        dequeueStatus = DEQUEUE_OK;
    }

    @Override
    public T get() {
        if (!queue.isEmpty()) {
            getStatus = GET_OK;
            return queue.getFirst();
        }
        getStatus = GET_ERR;
        return null;
    }

    // Доп. запросы
    // возвращает текущий размер очереди
    @Override
    public int size() {
        return queue.size();
    }


    // Запросы статусов
    @Override
    public int getDequeueStatus() {
        return dequeueStatus;
    }

    @Override
    public int getGetStatus() {
        return getStatus;
    }

}
