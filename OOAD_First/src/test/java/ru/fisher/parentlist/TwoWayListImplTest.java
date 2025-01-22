package ru.fisher.parentlist;

import org.junit.Test;

import java.util.Collections;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TwoWayListImplTest {

    Random random = new Random();

    @Test
    public void twoWayListTest() {
        TwoWayListImpl<Object> twoWayList = new TwoWayListImpl<>();


        System.out.println("Вставляем 1 число в пустой список:");
        twoWayList.addToEmpty(555);
        System.out.println(twoWayList.getAllValues());


        System.out.println("Вставка в конец списка");
        for (int i = 0; i < 10; i++) {
            twoWayList.addTail(random.nextInt(100) + 1);
        }
        System.out.println(twoWayList.getAllValues());
        System.out.println("Указатель на: " + twoWayList.get());
        assertEquals(11, twoWayList.size());


        System.out.println("Вставка в справа от указателя ");
        for (int i = 0; i < 10; i++) {
            twoWayList.putRight(random.nextInt(100) + 1);
        }
        System.out.println(twoWayList.getAllValues());
        System.out.println("Указатель на: " + twoWayList.get());


        System.out.println("Вставка в слева от указателя ");
        for (int i = 0; i < 10; i++) {
            twoWayList.putLeft(random.nextInt(100) + 1);
        }
        System.out.println(twoWayList.getAllValues());
        System.out.println("Указатель на: " + twoWayList.get());

        System.out.println("Двигаем курсор влево 5 раз");
        for (int i = 0; i < 5; i++) {
            twoWayList.left();
        }
        System.out.println("Указатель на: " + twoWayList.get());

    }

    @Test
    public void borderLineCasesTest() {
        TwoWayListImpl<Object> twoWayList = new TwoWayListImpl<>();

        System.out.println("Список пустой: " + twoWayList.getAllValues());
        System.out.println("Двигаем курсор влево");
        twoWayList.left();
        System.out.println("Курсор в начале списка? - " + twoWayList.isHead());
        System.out.println("Двигаем курсор вправо");
        twoWayList.right();
        System.out.println("Курсор в конце списка? - " + twoWayList.isTail());
        System.out.println("Статусы команд left() - " + twoWayList.getLeftStatus() + " и right() - " + twoWayList.getRightStatus());

        System.out.println("Вставка в конец списка 5 значений");
        for (int i = 0; i < 5; i++) {
            twoWayList.addTail(random.nextInt(100) + 1);
        }
        System.out.println("Список: " + twoWayList.getAllValues());
        System.out.println("Курсор в начале списка? - " + twoWayList.isHead());
        System.out.println("Курсор в конце списка? - " + twoWayList.isTail());
        assertTrue(twoWayList.isTail());

        System.out.println("Курсор на - " + twoWayList.get());
        System.out.println("Двигаем курсор влево до конца");
        for (int i = 0; i < 6; i++) {
            twoWayList.left();
        }
        System.out.println("Курсор на - " + twoWayList.get());

        System.out.println("Двигаем курсор вправо до конца");
        for (int i = 0; i < 6; i++) {
            twoWayList.right();
        }
        System.out.println("Курсор на - " + twoWayList.get());

    }



    @Test
    public void simpleTest() {
        TwoWayListImpl<Object> linkedList = new TwoWayListImpl<>();

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

        System.out.println("Удаляем все узлы где значение 0");
        linkedList.removeAll(0);
        System.out.println(linkedList.getAllValues());
        linkedList.clear();
        assertEquals(Collections.emptyList(), linkedList.getAllValues());

    }

}