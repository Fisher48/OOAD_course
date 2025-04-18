package ru.fisher.Task8;


// Фабрика элементов для игрового поля
public abstract class ElementsFactory {

    // Создаем новый объект Element
    // Постусловие: создан новый элемент
    public Element createElement() {
        return new Element();
    }
}
