package ru.fisher.task9;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Objects;

// В Java уже предоставляет функциональность Object
public class General implements Cloneable, Serializable {

    private Object name;

    protected General(Object name) {
        this.name = name;
    }

    // Копирование объекта
    public General deepCopy() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
            outputStream.writeObject(this);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (General) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Клонирование объекта
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Сравнение объектов
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        General general = (General) o;
        return Objects.equals(name, general.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    // Сериализация
    public String serialize(General obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
           return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // Десериализация
    public General deserialize(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, General.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // Печать
    @Override
    public String toString() {
        return "General{" +
                "name=" + name +
                '}';
    }

    // Проверка типа объекта
    public boolean isType(Class<?> type) {
        return type.isInstance(this);
    }

    // Получение реального типа объекта (getClass())
    public Class<?> getRealType() {
        return getClass();
    }

}

class Any extends General {
    protected Any(Object name) {
        super(name);
    }

}
