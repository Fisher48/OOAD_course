package ru.fisher.task12;


import java.io.*;


public class General implements Serializable {

    public <T> T assignmentAttempt(T target, T source) {
        if (target.getClass().isAssignableFrom(source.getClass())) {
            return target;
        }
        return (T) new None();
    }

}

class Any extends General {
    //...
}

final class None extends Any {
    //...
}
