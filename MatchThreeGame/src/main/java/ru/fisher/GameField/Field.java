package ru.fisher.GameField;

public class Field {

    public Element[][] grid;
    private Integer rows = 8;
    private Integer columns = 8;
    ElementsFactory elementsFactory = new ElementsFactory();

    public Field() {
       this.grid = new Element[rows][columns];
    }

    public Field(int rows, int columns) {
        this.columns = columns;
        this.rows = rows;
        this.grid = new Element[rows][columns];
        initField();
    }

    public void initField() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                Element e = elementsFactory.createNewRandomElement();
                e.setCoordinates(i, j);
                grid[i][j] = e;
            }
        }
    }

    public boolean isInBounds(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < columns;
    }

    public boolean fieldIsEmpty() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    // Запрос: получить элемент по координатам
    public Element getElement(int x, int y) {
        return grid[x][y];
    }

    public void setElement(Element element, int x, int y) {
        element.setCoordinates(x, y);
        grid[x][y] = element;
    }

    public void renderFiled() {
        // Нумерация столбцов
        System.out.print("   ");
        for (int i = 0; i < grid.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Линия снизу от нумерации столбцов
        System.out.print("  ");
        for (int j = 0; j < grid.length; j++) {
            System.out.print("__");
        }
        System.out.println();

        // Нумерация строк и вывод элементов
        for (int i = 0; i < grid.length; i++) {
            System.out.print(i + "| ");
            for (int j = 0; j < grid.length; j++) {
                System.out.print(colorize(grid[i][j].type) + " ");
            }
            System.out.println();
        }
    }

    private String colorize(String symbol) {
        return switch (symbol) {
            case "A" -> "\u001B[31m" + symbol + "\u001B[0m"; // Красный
            case "B" -> "\u001B[32m" + symbol + "\u001B[0m"; // Зелёный
            case "C" -> "\u001B[34m" + symbol + "\u001B[0m"; // Синий
            case "D" -> "\u001B[33m" + symbol + "\u001B[0m"; // Жёлтый
            case "E" -> "\u001B[35m" + symbol + "\u001B[0m"; // Фиолетовый
            case "F" -> "\u001B[36m" + symbol + "\u001B[0m"; // Голубой
            case "G" -> "\u001B[37m" + symbol + "\u001B[0m"; // Белый
            case "H" -> "\u001B[90m" + symbol + "\u001B[0m"; // Серый
            case " " -> "\u001B[55m" + "·" + "\u001B[0m";    // Точки для пустых ячеек
            case "*" -> "✖"; // Серый крестик для удалённых
            default -> "\u001B[37m" + symbol + "\u001B[0m"; // Белый по умолчанию
        };
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }
}
