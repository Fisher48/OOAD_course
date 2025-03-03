Решение 10-го задания:

Для ограничения переопределения метода в Java, можно использовать ключевое слово final.

````java
class Animal {

        // Помечая метод как final мы ограничиваем дальнейшее его переопределение
        final void immutableScream() {
            System.out.println("Animal scream!");
        }

    }

    class Cat extends Animal {

        // Компилятор выдаст ошибку, т.к. метод родителя помечен как final
        @Override
        void immutableScream() {
            System.out.println("Cat scream!");
        }

    }
````