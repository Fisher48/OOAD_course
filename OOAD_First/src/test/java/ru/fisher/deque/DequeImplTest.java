package ru.fisher.deque;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DequeImplTest {

    private final Random rand = new Random();

    @Test
    void simpleTest() {
        DequeImpl<Object> deque = new DequeImpl<>();
        assertEquals(0, deque.size());

        System.out.println("Заполняем в очередь:");
        for (int i = 0; i < 5; i++) {
            int front = rand.nextInt(100) + 1;
            int tail = rand.nextInt(100) + 1;
            deque.addFront(front);
            deque.addTail(tail);
        }
        assertEquals(10, deque.size());
        deque.printDeque();

        System.out.println("Получить элемент из начала - " + deque.getFront());
        System.out.println("Получить элемент из конца - " + deque.getTail());

        System.out.println("Удаляем элемент в начале и в конце");
        deque.removeFront();
        deque.removeTail();
        deque.printDeque();
        System.out.println("Получить элемент из начала - " + deque.getFront());
        System.out.println("Получить элемент из конца - " + deque.getTail());
    }
}