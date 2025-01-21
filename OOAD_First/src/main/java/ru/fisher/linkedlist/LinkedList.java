package ru.fisher.linkedlist;

abstract class LinkedList<T> {

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

    public static final int REPLACE_OK = 1; // последняя replace() прошла успешно
    public static final int REPLACE_ERR = 2; // замена не произошла

    public static final int REMOVE_OK = 1; // текущий узел удален успешно
    public static final int REMOVE_ERR = 1; // текущий узел не удален, ошибка (не существует)


    // Конструктор
    // Постусловие - создан новый пустой список
    protected LinkedList() {} // Конструктор

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
    // Постусловие - удаляется текущий узел
    // Постусловие - курсор смещается в зависимости от соседних узлов
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

    // Предусловие - список не пустой
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

/*

 2.2. Почему операция tail не сводима к другим операциям (если исходить из эффективной реализации)?

 Потому что, операция tail выполняется за O(1), по сравнению с другими операциями.

 2.3. Операция поиска всех узлов с заданным значением, выдающая список таких узлов, уже не нужна. Почему?

 Потому что, можно обойтись командами find() и get(), например в цикле.

 */