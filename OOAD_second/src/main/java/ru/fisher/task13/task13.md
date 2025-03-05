Примеры четырех вариантов скрытия метода в Java:

1. Метод публичен в родительском классе А и публичен в его потомке B;
```java
class A {
    public void method() {
        System.out.println("Method in A");
    }
}

class B extends A {
    @Override
    public void method() {
        System.out.println("Method in B");
    }
}
```
2. Метод публичен в родительском классе А и скрыт в его потомке B.

В Java нет такой возможности делать приватным публичный метод родителя в потомке.
```java
class A {
    public void method() {
        System.out.println("Method in A");
    }
}
// Будет ошибка
class B extends A {
    @Override
    private void method() {
        System.out.println("Method in B");
    }
}
```
3. Метод скрыт в родительском классе А и публичен в его потомке B;

Такой возможности в Java нет. Метод с модификатором private виден только внутри того класса, где он определен и в потомках он недоступен.
```java
class A {
    private void method() {
        System.out.println("Method in A");
    }
}

class B extends A {
    public void method() {
        System.out.println("Method in B");
    }
}
```
4. Метод скрыт в родительском классе А и скрыт в его потомке B.

Если мы сделаем их private, то это будут полностью независимые друг от друга методы и не связаны между собой
```java
class A {
    private void method() {
        System.out.println("Method in A");
    }
}

class B extends A {
    private void method() {
        System.out.println("Method in B");
    }
}
```
Можно еще попробовать сделать через protected. 
Но это такое скрытие метода сработает только вне пакета, формально это похоже на 2-й метод (с одной стороны метод может быть переопределен, 
но с другой стороны не всегда будет виден)
```java
class A {
    protected void method() {
        System.out.println("Method in A");
    }
}

class B extends A {
    @Override
    protected void method() {
        System.out.println("Method in B");
    }
}
```
