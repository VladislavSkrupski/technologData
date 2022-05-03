package by.furniture.technologdata.classes;

public class CustomProperty {
    private String name;
    private String value;

    public CustomProperty() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CustomProperty{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
