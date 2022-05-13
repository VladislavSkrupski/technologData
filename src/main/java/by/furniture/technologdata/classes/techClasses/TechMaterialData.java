package by.furniture.technologdata.classes.techClasses;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

import java.util.HashMap;

public class TechMaterialData {
    private final CheckBox materialNameCheckBox;
    private final ChoiceBox<String> formatChoiceBox;
    private String name;
    private String thickness;
    private HashMap<String, Float[]> format;
    private String unit;
    private String amount;

    public TechMaterialData(String name, String thickness, HashMap<String, Float[]> format, ChoiceBox<String> formatChoiceBox, String unit, String amount) {
        this.name = name;
        this.materialNameCheckBox = new CheckBox(this.name);
        this.materialNameCheckBox.setSelected(true);
        this.thickness = thickness;
        this.format = format;
        this.formatChoiceBox = formatChoiceBox;
        this.unit = unit;
        this.amount = amount;
    }

    public CheckBox getMaterialNameCheckBox() {
        return materialNameCheckBox;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThickness() {
        return thickness;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness;
    }

    public ChoiceBox<String> getFormatChoiceBox() {
        return formatChoiceBox;
    }

    public HashMap<String, Float[]> getFormat() {
        return format;
    }

    public void setFormat(HashMap<String, Float[]> format) {
        this.format = format;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TechMaterialData{" +
                "name='" + name + '\'' +
                ", thickness='" + thickness + '\'' +
                ", format='" + format + '\'' +
                ", unit='" + unit + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
