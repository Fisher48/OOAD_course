package ru.fisher.parentlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class ParentList<T> {

    // Интерфейс класса, реализующий АТД LinkedList
    public static final int HEAD_OK = 1; // курсор на первом узле, последняя head() отработало корректно
    public static final int HEAD_ERR = 1; // курсор не установлен на первом узле

    public static final int TAIL_OK = 1; // курсор на последнем узле, последняя tail() отработало корректно
    public static final int TAIL_ERR = 1; // курсор не установлен на последнем узле

    public static final int PUT_LEFT_OK = 1;  // последняя putLeft() отработала нормально
    public static final int PUT_LEFT_ERR = 2; // ошибка нет возможно вставить элемент перед текущим (список пуст)

    public static final int PUT_RIGHT_OK = 1;  // последняя putRight() отработала нормально
    public static final int PUT_RIGHT_ERR = 2; // ошибка нет возможности вставить элемент после текущего (список пуст)

    public static final int RIGHT_OK = 1;  // последняя right() отработала нормально
    public static final int RIGHT_ERR = 2; // правее нет элемента, смещение вправо невозможно

    public static final int GET_OK = 1;  // последняя get() отработала нормально
    public static final int GET_ERR = 2;  // ошибка значение не получено

    public static final int FIND_OK = 1; // искомое значение найдено
    public static final int FIND_ERR = 2; // значение не найдено
    public static final int FIND_EMPTY = 3; // список пуст


    public static final int REPLACE_OK = 1; // последняя replace() прошла успешно
    public static final int REPLACE_ERR = 2; // замена не произошла

    public static final int REMOVE_OK = 1; // текущий узел удален успешно
    public static final int REMOVE_ERR = 1; // текущий узел не удален, ошибка (не существует)


    // Конструктор
    // Постусловие - создан новый пустой список
    protected ParentList() {} // Конструктор

    // Команды:

    // Предусловие - список не пустой
    // Постусловие - курсор установлен на первый узел
    public abstract void head(); // установить курсор на первый узел в списке

    // Предусловие - список не пустой
    // Постусловие - курсор установлен на последний узел
    public abstract void tail(); // установить курсор на последний узел в списке

    // Предусловие - список не пустой, и текущий узел не является последним
    // Постусловие - курсор сдвинут на один узел вправо
    public abstract void right(); // сдвинуть курсор на один узел вправо

    // Предусловие - список не пустой
    // Постусловие - вставлен новый узел перед текущим заданным значением справа
    public abstract void putRight(T value); // вставить следом за текущим узлом новый узел с заданным значением

    // Предусловие - список не пустой
    // Постусловие - вставлен новый узел перед текущим заданным значением слева
    public abstract void putLeft(T value); // вставить перед текущим узлом новый узел с заданным значением

    // Предусловие - список не пустой
    // Постусловие - удален текущий узел
    // Постусловие - курсор смещен в зависимости от соседних узлов
    public abstract void remove();  // удалить текущий узел
    // (курсор смещается к правому соседу, если он есть,
    // в противном случае курсор смещается к левому соседу,
    // если он есть)

    // Постусловие - из списка удаляются все значения
    public abstract void clear(); // очистить список

    // Предусловие - список пуст
    // Постусловие - добавлен новый узел
    public abstract void addToEmpty(T value); // добавить новый узел в пустой список.

    // Постусловие - добавлен новый узел в хвост списка
    public abstract void addTail(T value); // добавить новый узел в хвост списка

    // Предусловие - список не пустой
    // Постусловие - значение текущего узла заменено на заданное
    public abstract void replace(T value); // заменить значение текущего узла на заданное

    // Постусловие - курсор установлен на следующий узел с искомым значением (по отношению к текущему узлу)
    public abstract void find(T value);

    // Постусловие - все узлы с заданным значением удалены из списка
    public abstract void removeAll(T value); // удалить в списке все узлы с заданным значением


    // Запросы:

    // Предусловие - список не пустой
    public abstract T get(); // получить значение текущего узла

    public abstract int size(); // посчитать количество узлов в списке
    public abstract boolean isHead(); // находится ли курсор в начале списка?
    public abstract boolean isTail(); // находится ли курсор в конце списка?
    public abstract boolean isValue(); // установлен ли курсор на какой-либо узел в списке (по сути, непустой ли список)


    // Доп. запросы
    public abstract int getHeadStatus(); // возвращает значение HEAD_*
    public abstract int getTailStatus(); // возвращает значение TAIL_*
    public abstract int getGetStatus(); // возвращает значение GET_*
    public abstract int getRightStatus();// возвращает значение RIGHT_*
    public abstract int getPutLeftStatus(); // возвращает значение PUT_LEFT_*
    public abstract int getPutRightStatus(); // возвращает значение PUT_RIGHT_*
    public abstract int getReplaceStatus(); // возвращает REPLACE_*
    public abstract int getFindStatus(); // возвращает значение FIND_*
    public abstract int getRemoveStatus(); // возвращает значение REMOVE_*
}

abstract class LinkedList<T> extends ParentList<T> { }

abstract class TwoWayList<T> extends ParentList<T> {

    // Интерфейс класса, реализующий АТД TwoWayList
    public static final int LEFT_OK = 1;  // последняя left() отработала нормально
    public static final int LEFT_ERR = 2; // левее нет элемента, смещение влево невозможно


    // Конструктор
    protected TwoWayList() {
        super();
    }

    // Команды

    // Предусловие - список не пустой, и текущий узел не является первым
    // Постусловие - курсор сдвинут на один узел влево
    public abstract void left(); // сдвинуть курсор на один узел влево

    // Доп. запрос
    public abstract int getLeftStatus();// возвращает значение LEFT_*

}

class TwoWayListImpl<T> extends TwoWayList<T> {

    protected Node<T> head;
    protected Node<T> tail;
    protected Node<T> cursor;

    protected static class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;

        Node(T value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    private int size;

    private int headStatus;
    private int tailStatus;
    private int getStatus;
    private int rightStatus;
    private int putLeftStatus;
    private int putRightStatus;
    private int removeStatus;
    private int replaceStatus;
    private int findStatus;
    private int leftStatus; // статус запроса left()

    protected TwoWayListImpl() {
        head = null;
        tail = null;
        cursor = null;
        size = 0;
    }

    @Override
    public void head() {
        if (!isEmpty()) {
            cursor = head;
            headStatus = HEAD_OK;
        } else {
            headStatus = HEAD_ERR;
        }
    }

    @Override
    public void tail() {
        if (!isEmpty()) {
            cursor = tail;
            tailStatus = TAIL_OK;
        } else {
            tailStatus = TAIL_ERR;
        }
    }

    @Override
    public void right() {
        if (cursor == null || cursor.next == null) {
            rightStatus = RIGHT_ERR;
        } else {
            cursor = cursor.next;
            rightStatus = RIGHT_OK;
        }
    }

    @Override
    public void putRight(T value) {
        if (!isValue()) {
            putRightStatus = PUT_RIGHT_ERR;
            return;
        }
        Node<T> newNode = new Node<>(value);
        newNode.next = cursor.next;
        newNode.prev = cursor;
        if (cursor.next != null) {
            cursor.next.prev = newNode;
        }
        cursor.next = newNode;
        if (cursor == tail) {
            tail = newNode;
        }
        size++;
        putRightStatus = PUT_RIGHT_OK;
    }

    @Override
    public void putLeft(T value) {
        if (!isValue()) {
            putLeftStatus = PUT_LEFT_ERR;
            return;
        }
        Node<T> newNode = new Node<>(value);
        newNode.prev = cursor.prev;
        newNode.next = cursor;
        if (cursor.prev != null) {
            cursor.prev.next = newNode;
        } else {
            head = newNode;
        }
        cursor.prev = newNode;
        size++;
        putLeftStatus = PUT_LEFT_OK;
    }

    @Override
    public void remove() {
        if (!isValue()) {
            removeStatus = REMOVE_ERR;
            return;
        }
        if (cursor.prev != null) {
            cursor.prev.next = cursor.next;
        } else {
            head = cursor.next; // Если текущий узел был головой
        }

        if (cursor.next != null) {
            cursor.next.prev = cursor.prev;
        } else {
            tail = cursor.prev; // Если текущий узел был хвостом
        }

        cursor = (cursor.next != null) ? cursor.next : cursor.prev; // Сдвинуть курсор
        size--;
        removeStatus = REMOVE_OK;
    }

    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.cursor = null;
        size = 0;
    }

    @Override
    public void addToEmpty(T value) {
        Node<T> newNode = new Node<>(value);
        head = newNode;
        tail = newNode;
        cursor = newNode;
        size++;
    }

    @Override
    public void addTail(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        cursor = newNode;
        size++;
    }

    @Override
    public void replace(T value) {
        if (isEmpty()) {
            replaceStatus = REPLACE_ERR;
            return;
        }
        cursor.value = value;
        replaceStatus = REPLACE_OK;
    }

    @Override
    public void find(T value) {
        if (isEmpty()) {
            findStatus = FIND_EMPTY;
        }
        if (isValue()) {
            Node<T> current = cursor != null ? cursor : head; // Начинаем либо с головы, либо с курсора
            while (current != null) {
                if (current.value.equals(value)) {
                    findStatus = FIND_OK;
                    cursor = current;
                    return; // Найдено
                }
                current = current.next;
            }
        }
        findStatus = FIND_ERR; // Если не найдено
    }

    @Override
    public void removeAll(T value) {
        Node<T> current = head;
        while (current != null) {
            Node<T> next = current.next; // Сохраняем ссылку на следующий узел
            if (current.value.equals(value)) {
                cursor = current;
                remove();
            }
            current = next;
        }
    }

    @Override
    public T get() {
        if (!isValue()) {
            getStatus = GET_ERR;
            return null;
        }
        getStatus = GET_OK;
        return cursor.value;
    }

    // Предусловие - список не пустой, и текущий узел не является первым
    // Постусловие - курсор сдвинут на один узел влево
    public void left() {
        if (cursor == null || cursor.prev == null) {
            leftStatus = LEFT_ERR;
        } else {
            cursor = cursor.prev;
            leftStatus = LEFT_OK;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isHead() { return !isEmpty() && cursor == head; }

    @Override
    public boolean isTail() {
        return !isEmpty() && cursor == tail;
    }

    @Override
    public boolean isValue() { return !isEmpty(); }

    @Override
    public int getHeadStatus() {
        return headStatus;
    }

    @Override
    public int getTailStatus() {
        return tailStatus;
    }

    @Override
    public int getGetStatus() {
        return getStatus;
    }

    @Override
    public int getRightStatus() { return rightStatus; }

    @Override
    public int getPutLeftStatus() { return putLeftStatus; }

    @Override
    public int getPutRightStatus() { return putRightStatus; }

    @Override
    public int getReplaceStatus() { return replaceStatus; }

    @Override
    public int getFindStatus() { return findStatus; }

    @Override
    public int getRemoveStatus() {
        return removeStatus;
    }
    public int getLeftStatus() {
        return leftStatus;
    }

    public List<T> getAllValues() {
        if (isEmpty()) {
            return Collections.emptyList();
        }
        Node<T> current = head;
        List<T> list = new ArrayList<>();
        while (current != null) {
            list.add(current.value);
            current = current.next;
        }
        return list;
    }

    // Вспомогательный метод для проверки пустоты списка
    private boolean isEmpty() { return size == 0; }

}
