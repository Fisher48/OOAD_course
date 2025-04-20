**Шаг 4.** Начинаем создание формальных спецификаций АТД, выделяем абстрактные, 
частично реализованные классы и т.д., увязываем их в иерархию (проектирование).

**Шаг 5.** Продолжаем уточнение формальных спецификаций АТД, подробно и развёрнуто определяем классы в терминах запросов, 
команд и ограничений (проектирование).

Решение 8-го задания:
Формальные спецификации АТД.
Классы поведения:

1. **_Field_**
2. **_Element_** 
3. **_ElementFactory_**

4. _~~**_GameObserver_**~~_ он больше подходит для варианта с графическим UI, не увидел необходимости
5. ~~**_GameFacade_**~~ - в ходе проектирования принял решение не использовать этот класс, т.к он избыточен для игры "три в ряд".
6. ~~**_GameState_**~~ - в ходе проектирования посчитал что это избыточный класс.
7. **_GameCommand_**

8. **_GameSession_** - по сути является фасадом для игры, поэтому я отказался и от **_GameFacade_**

9. **_Rules_**
10. **_BonusSystem_**
11. **_Statistics_**

```java
// Абстрактный игровой элемент
public abstract class Element { 
    //...//
}

// Абстрактная фабрика по созданию элементов
public abstract class ElementsFactory {
    // Создаем новый объект Element
    // Постусловие: создан новый элемент
    public abstract Element createElement();
}

// Абстрактное поле игры
public abstract class Field {

    // Предусловие: начало игры или перезапуск
    // Постусловие: все ячейки инициализированы элементами
    public abstract void initField();

    // Запрос: поле пустое?
    public abstract boolean fieldIsEmpty();

    // Запрос: получить элемент по координатам
    public abstract Element getElement(int x, int y);
}

// Команды игрока
public abstract class GameCommand {
    
    // Предусловие: элементы соседние?
    // Постусловие: элементы поменялись местами
    public abstract void swapElements(Element e1, Element e2);

    // Проверка валидности перестановки
    public abstract boolean swapIsValid(Element e1, Element e2);
}

// Интерфейс логики игры
public interface Rules {

    // Проверка активации бонуса
    boolean bonusIsActivate();

    // Проверка есть ли совпадения?
    boolean hasMatches();

    // Предусловие: есть совпадения
    // Постусловие: удалены совпавшие элементы
    void removeMatch(Field field);

    // Предусловие: есть пустые клетки
    // Постусловие: элементы сдвинуты вниз
    void shiftElementsDown(Field field);

    // Запрос: есть ли возможные ходы?
    boolean hasValidMoves(GameField field);
}

// Бонусная система
public class BonusSystem { 
    Integer rateIndex; // множитель
}

// Статистика игрока
public abstract class Statistics {
    
    protected Integer points;
    protected Integer moves;

    // Постусловие: увеличены очки
    public abstract void addPoints(int amount);

    // Постусловие: увеличены ходы
    public abstract void addMoves(int amount);

    // Запрос получения кол-во очков
    public abstract int getPoints();

    // Запрос получения кол-во ходов
    public abstract int getMoves();
    
    // Сбросить статистику
    public abstract void resetStat();

    // Отображение текущей статистики
    public abstract void displayStatistics();
}

// Основной класс игры - управляет всем процессом игры
public abstract class GameSession {
    
    public abstract void start();                 // Запуск игры
    public abstract void renderField();           // Отрисовка поля
    public abstract void handlePlayerMove();      // Обработка ввода игрока
    public abstract void updateStatistics();      // Обновление очков/ходов
    public abstract boolean gameOver();           // Конец игры?
    public abstract void restart();               // Перезапуск игры
    public abstract void exit();                  // Завершить игру
    
    // Обработка строки ввода пользователя
    // Предусловие: ввод должен быть корректным
    // Постусловие: либо происходит действие, либо выводится сообщение об ошибке
    public abstract void processInput(String input);

    // Вывод сообщения об ошибке
    public abstract void showError(String message);
}
```