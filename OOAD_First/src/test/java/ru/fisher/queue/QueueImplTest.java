package ru.fisher.queue;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class QueueImplTest {

    @Test
    void simpleTest() {
        int N = 20;
        QueueImpl<Object> queue = new QueueImpl<>();
        System.out.println("Размер очереди: " + queue.size());
        assertEquals(0, queue.size());

        System.out.println("Заполняем очередь:");
        for (int i = 1; i < N; i++) {
            System.out.print(i + " ");
            queue.enqueue(i);
        }
        System.out.println();

        System.out.println("Размер очереди: " + queue.size());
        assertEquals(19, queue.size());

        System.out.println("Первый элемент - " + queue.get()); // 1 (первый элемент)
        assertEquals(1, queue.get());
        queue.dequeue(); // Удаляем первый элемент

        System.out.println("Новый первый элемент - " + queue.get()); // 2 (новый первый элемент)
        assertEquals(2, queue.get());
        queue.dequeue(); // Удаляем первый элемент

        System.out.println("Новый первый элемент - " + queue.get()); // 3 (новый первый элемент)
        assertEquals(3, queue.get());
        queue.dequeue(); // Удаляем первый элемент

        System.out.println("Извлекаем все из очереди: ");
        for (int i = 1; i < N; i++) {
            System.out.print(queue.get() + " ");
            queue.dequeue();
        }
        System.out.println();

        System.out.println("Размер очереди: " + queue.size());
        assertEquals(0, queue.size());

    }


}