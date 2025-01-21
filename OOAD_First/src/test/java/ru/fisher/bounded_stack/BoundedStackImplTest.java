package ru.fisher.bounded_stack;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class BoundedStackImplTest {

    Logger log = Logger.getLogger(BoundedStackImplTest.class.getName());

    @Test
    public void normalWorkOfStackTest() {
        BoundedStackImpl<Integer> stack = new BoundedStackImpl<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        assertEquals(10, stack.size());
        log.info("Стек заполнен + " + stack.size() + " элементами");
        assertEquals(9, stack.peek());
        log.info("Статус peek() " + stack.getPeekStatus());
        log.info("Статус pop() " + stack.getPopStatus());
        log.info("Вытаскиваем 1 элемент");

        stack.pop();
        log.info("Статус pop() после: " + stack.getPopStatus());
        assertEquals(8, stack.peek());
    }

    @Test
    public void stackOverflow() {
        BoundedStackImpl<Integer> stack = new BoundedStackImpl<>(3);
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        assertEquals(0, stack.getPopStatus());
        assertEquals(0,stack.getPeekStatus());
        assertEquals(3, stack.size());

        stack.clear();
        assertEquals(0, stack.size());
        log.info("Стек после очистки - " + stack.size());
    }

    @Test
    public void popWithDifferTypes() {
        BoundedStackImpl<Object> stack = new BoundedStackImpl<>();
        stack.push(1);
        stack.push("2");
        stack.push(3.14);
        stack.push("Template");
        log.info("Удаляем по 1-му элементу");
        while (stack.size() > 0) {
            log.info(stack.peek() + "");
            stack.pop();
        }
        stack.pop();
        log.info("Статус pop() при пустом " + stack.getPopStatus());
        stack.peek();
        log.info("Статус peek() при пустом " + stack.getPeekStatus());
        assertEquals(0, stack.size());
    }

}