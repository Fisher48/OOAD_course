Решение 15-го задания:

Пример, как вместо поля родительского класса, например типа персонажа, применяется наследование.
```java
abstract class Person {
    int damage;
    double hp;

    protected Person(int damage, double hp) {
        this.damage = damage;
        this.hp = hp;
    }

    public abstract void attack();
}

class Warrior extends Person {

    public Warrior(int damage, double hp) {
        super(damage, hp);
    }

    @Override
    public void attack() {
        System.out.println("Attack by Warrior");
    }

}
```


Как все бы выглядело по **НЕ**правильному, мы бы создали допустим поле _type_ и пришлось бы сделать конструкцию if-else и каждом случае в зависимости от типа персонажа для метода атаки.
````java
class Person {
    int damage;
    double hp;
    String type;

    protected Person(int damage, double hp, String type) {
        this.damage = damage;
        this.hp = hp;
        this.type = type;
    }

    public void attack(Person target) {
        if (type.equals("warrior")) {
            // Атака воина
        } else if (type.equals("mage")) {
            // Атака мага
        }
        // и т.д.
    }
    
}
````
