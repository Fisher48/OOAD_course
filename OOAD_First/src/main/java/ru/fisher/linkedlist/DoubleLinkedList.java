package ru.fisher.linkedlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DoubleLinkedList<T> extends LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private Node<T> cursor;
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

    public static class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;

        Node(T value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    public DoubleLinkedList() {
        head = null;
        tail = null;
        cursor = null;
        size = 0;
    }

    @Override
    public void head() {
        if (isHead()) {
            cursor = head;
            headStatus = HEAD_OK;
        } else {
            headStatus = HEAD_ERR;
        }
    }

    @Override
    public void tail() {
        if (isTail()) {
            cursor = tail;
            tailStatus = TAIL_OK;
        } else {
            tailStatus = TAIL_ERR;
        }
    }

    @Override
    public void right() {
        if (!isValue() && !isTail()) {
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
        if (isValue()) {
            Node<T> current = cursor; // Начинаем с головы
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
            if (current.value == value) {
                remove();
            } else {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                size--;
            }
            current = current.next;
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
    public int getRightStatus() {
        return rightStatus;
    }

    @Override
    public int getPutLeftStatus() {
        return putLeftStatus;
    }

    @Override
    public int getPutRightStatus() {
        return putRightStatus;
    }

    @Override
    public int getReplaceStatus() { return replaceStatus; }

    @Override
    public int getFindStatus() { return findStatus; }

    @Override
    public int getRemoveStatus() {
        return removeStatus;
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
