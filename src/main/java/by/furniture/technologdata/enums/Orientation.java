package by.furniture.technologdata.enums;

public enum Orientation {
    HORIZONTAL("Горизонтальная"),
    VERTICAL("Вертикальная"),
    UNDEFINED("Не задана");

    private String value;

    Orientation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
