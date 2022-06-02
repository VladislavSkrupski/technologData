package by.furniture.technologdata.enums;

/**
 * Перечисление возможных вариантов ориентации текстуры у панели
 */
public enum Orientation {
    HORIZONTAL("Горизонтальная"),
    VERTICAL("Вертикальная"),
    UNDEFINED("Не задана");

    private final String value;

    Orientation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
