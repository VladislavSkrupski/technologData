package by.furniture.technologdata.classes.techClasses;

import javafx.scene.control.CheckBox;

public class UnsupportedPanel {
    private CheckBox checked;
    private String position;
    private String nomination;
    private Float length;
    private Float Width;
    private Integer amount;
    private String materialNomination;

    public UnsupportedPanel() {
    }

    public CheckBox getChecked() {
        return checked;
    }

    public void setChecked(CheckBox checked) {
        this.checked = checked;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNomination() {
        return nomination;
    }

    public void setNomination(String nomination) {
        this.nomination = nomination;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getWidth() {
        return Width;
    }

    public void setWidth(Float width) {
        Width = width;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getMaterialNomination() {
        return materialNomination;
    }

    public void setMaterialNomination(String materialNomination) {
        this.materialNomination = materialNomination;
    }

    @Override
    public String toString() {
        return "UnsupportedPanels{" +
                "checked=" + checked +
                ", position='" + position + '\'' +
                ", nomination='" + nomination + '\'' +
                ", length=" + length +
                ", Width=" + Width +
                ", Amount=" + amount +
                ", materialNomination='" + materialNomination + '\'' +
                '}';
    }
}
