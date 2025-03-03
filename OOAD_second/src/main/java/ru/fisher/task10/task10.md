Решение 10-го задания:

Для ограничения переопределения метода в Java, можно использовать ключевое слово final.

````java

class General implements Serializable, Cloneable {

    // Помечая метод как final мы ограничиваем дальнейшее его переопределение
    final void deepCopy() {
        //....//
    }

}

class Any extends General {

    // Компилятор выдаст ошибку, т.к. метод родителя помечен как final
    @Override
    void deepCopy() {
        //....//
    }

}
````