package ru.fisher.GameField;

import java.util.Objects;

public class Element {
    String type;
    protected Coordinates coordinates;

    public Element(String type, Coordinates coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public Element(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type.toString();
    }

    public void setCoordinates(int x, int y) {
        this.coordinates = new Coordinates(x, y);
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return Objects.equals(type, element.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
