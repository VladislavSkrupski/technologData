package by.furniture.technologdata.classes.techClasses;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

import java.util.HashMap;

public class TechMaterialData {
    private final CheckBox materialNameCheckBox;
    private final ChoiceBox<String> formatChoiceBox;
    private Boolean selected = false;
    private String name;
    private String thickness;
    private HashMap<String, Float[]> format;
    private String unit;
    private String amount;

    private String curvedEdge;


    public TechMaterialData(String name, String thickness, HashMap<String, Float[]> format, ChoiceBox<String> formatChoiceBox, String unit, String amount, String curvedEdge, Boolean state) {
        this.name = name;
        this.materialNameCheckBox = new CheckBox(this.name);
        this.materialNameCheckBox.setOnAction(event -> this.selected = materialNameCheckBox.isSelected());
        this.thickness = thickness;
        this.format = format;
        this.formatChoiceBox = formatChoiceBox;
        this.unit = unit;
        this.amount = amount;
        this.curvedEdge = curvedEdge;
        this.selected = state;
        this.materialNameCheckBox.setSelected(this.selected);
    }

    public Boolean isSelected() {
        return selected;
    }

    public void setSelected(Boolean state) {
        this.selected = state;
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

    public String getCurvedEdge() {
        return curvedEdge;
    }

    public void setCurvedEdge(String curvedEdge) {
        this.curvedEdge = curvedEdge;
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
