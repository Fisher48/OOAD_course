package ru.fisher.dynarray;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;


public class DynArrImplTest {


    void printArray(DynArrImpl<Object> dynArr) {
        System.out.println("Вывести весь массив:");
        for (int i = 0; i < dynArr.getCapacity(); i++) {
            System.out.print(dynArr.getItem(i) + " ");
        }
        System.out.println();
    }

    @Test
    public void simpleTest() {
        DynArrImpl<Object> dynArr = new DynArrImpl<>(Object.class);

        for (int i = 0; i < 17; i++) {
            dynArr.append(i);
        }

        printArray(dynArr);

        System.out.print("Число с индексом 5: ");
        System.out.println(dynArr.getItem(5));
        System.out.println("Статус получения элемента: " + dynArr.getGetItemStatus());
        assertEquals(1, dynArr.getGetItemStatus());


        System.out.print("Число с несуществующим индексом: ");
        System.out.print(dynArr.getItem(100) + " ");
        System.out.println(dynArr.getItem(-100));
        System.out.println("Статус получения элемента: " + dynArr.getGetItemStatus());
        assertEquals(2, dynArr.getGetItemStatus());


        System.out.print("Попытка вставки в недопустимую позицию - ");
        dynArr.insert(111, 26);
        dynArr.insert(333, 22);
        dynArr.insert(555, 19);
        System.out.println("Статус вставки элемента: " + dynArr.getGetItemStatus());
        assertEquals(2,dynArr.getInsertStatus());


        System.out.print("Кол-во элементов в массиве: ");
        System.out.println(dynArr.size());

        System.out.print("Текущая ёмкость буфера: ");
        System.out.println(dynArr.getCapacity());
        dynArr.remove(10);
        dynArr.remove(10);
        dynArr.remove(10);
        assertEquals(1,dynArr.getRemoveStatus());
        System.out.print("Кол-во элементов в массиве, после удаления 3-х элементов: ");
        System.out.println(dynArr.size());
        System.out.print("Текущая ёмкость буфера: ");
        System.out.println(dynArr.getCapacity());

        printArray(dynArr);

        System.out.print("Получить значение вне границ массива: ");
        System.out.print(dynArr.getItem(100) + " ");
        System.out.println(dynArr.getItem(-200));
        System.out.println("Статус получения элемента: " + dynArr.getGetItemStatus());
        assertEquals(2, dynArr.getGetItemStatus());

        System.out.println("Удаляем все значения в массиве через сlear() ");
        dynArr.clear();
        printArray(dynArr);

    }

}