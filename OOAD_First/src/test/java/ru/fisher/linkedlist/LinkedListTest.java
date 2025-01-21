package ru.fisher.linkedlist;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Random;

import static org.junit.Assert.assertEquals;

class LinkedListTest {

    Random random = new Random();

    @Test
    void putTest() {
        DoubleLinkedList<Object> linkedList = new DoubleLinkedList<>();

        System.out.println("Вставляем 1 число в пустой список:");
        linkedList.addToEmpty(555);
        System.out.println(linkedList.getAllValues());


        System.out.println("Вставка в конец списка");
        for (int i = 0; i < 10; i++) {
            linkedList.addTail(random.nextInt(100) + 1);
        }
        System.out.println(linkedList.getAllValues());
        System.out.println("Указатель на: " + linkedList.get());


        System.out.println("Вставка в справа от указателя ");
        for (int i = 0; i < 10; i++) {
            linkedList.putRight(random.nextInt(100) + 1);
        }
        System.out.println(linkedList.getAllValues());
        System.out.println("Указатель на: " + linkedList.get());


        System.out.println("Вставка в слева от указателя ");
        for (int i = 0; i < 10; i++) {
            linkedList.putLeft(random.nextInt(100) + 1);
        }
        System.out.println(linkedList.getAllValues());
        System.out.println("Указатель на: " + linkedList.get());
    }


    @Test
    void simpleTest() {
        DoubleLinkedList<Object> linkedList = new DoubleLinkedList<>();

        System.out.println("Вставка в пустой список:" );
        linkedList.addToEmpty(100);
        System.out.println(linkedList.getAllValues());

        System.out.println("Заполнение в цикле влево и вправо");
        for (int i = 0; i < 10; i++) {
            linkedList.putRight(i);
            linkedList.putLeft(i);
        }
        System.out.println(linkedList.getAllValues());
        assertEquals(21, linkedList.size());

        System.out.println("Замена значения в курсоре на 200");
        linkedList.replace(200);
        System.out.println("Статус replace - " + linkedList.getReplaceStatus());
        System.out.println("Текущее значение курсора: " + linkedList.get());
        System.out.println(linkedList.getAllValues());

        System.out.println("Вставляем вправо значение 900 от курсора");
        linkedList.putRight(900);
        System.out.println(linkedList.getAllValues());

        System.out.println("Смещаем курсор вправо 3 раза");
        linkedList.right();
        linkedList.right();
        linkedList.right();

        System.out.print("Получаем значение курсора ");
        System.out.println(linkedList.get());

        linkedList.find(3);
        System.out.println("Ищем значение 3");
        System.out.println("Курсор после поиска указывает на: " + linkedList.get());

        System.out.println("Вставляем значение 500 влево от курсора");
        linkedList.putLeft(500);
        System.out.println(linkedList.getAllValues());

        System.out.println("Ищем число 999, которого нет в списке");
        linkedList.find(999);
        System.out.println("Статус поиска - " + linkedList.getFindStatus());

        linkedList.clear();
        assertEquals(Collections.emptyList(), linkedList.getAllValues());

    }

}