Решение 21-го задания:

**1). Наследования реализации:**

````java
// Абстрактный класс
abstract class A {
    abstract String someMethod(String param);
}

// Создается класс B со стандартно реализованным методом
class B extends A {
    @Override
    String someMethod(String p) {
        return "Param " + p;
    }
}

// В этом классе BImpl идет уже расширение функциональности класса B
class BImpl extends B {
    @Override
    String someMethod(String newParam) {
        return super.someMethod(this.form) + newParam;
    }
}
````

**2). Льготное наследование**

````java
// класс Basic представляет базовую функциональность в виде методов addition, subtraction, basicOperations
class Basic {
    int addition(int a, int b) {
        return a + b;
    }
    int subtraction(int a, int b) {
        return a - b;
    }
    void basicOperations() {
        System.out.println("Some basic operations");
    }
}
// класс Addition может использовать методы класса предка, а также и свои собственные методы
class Addition extends Basic {
    int multiply(int a, int b) {
        return a * b;
    }
    // метод использует базовые методы класса Basic
    int complexSolution(int a, int b) {
       return addition(a, b) + subtraction(a, b);
    }
    void additionalOperations() {
        basicOperations();
        System.out.println("New additional operations");
    }
}
````