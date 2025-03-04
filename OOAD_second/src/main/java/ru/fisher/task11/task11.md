Насколько я понял, в Java не поддерживается множественное наследование.

Пример кода, как можно сделать:
```java
public class General implements Serializable {
    //...//
}

class Any extends General {
    //...//
}

final class None extends Any {
    //...//
}
```