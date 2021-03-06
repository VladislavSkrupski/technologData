package by.furniture.technologdata.classes.techClasses;

import javafx.scene.control.CheckBox;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TechEdgeData {
    private String article;
    private String name;
    private CheckBox nameCheckBox;
    private String designation;
    private Float length;
    private String orderWholeLength;
    private Float width;
    private Float thickness;
    private final String unit = "м";
    private String format;
    private Boolean selected = false;

    public TechEdgeData(String article, String name, String designation, Float length, Float width, Float thickness, Boolean state) {
        this.article = article;
        this.name = name;
        this.nameCheckBox = new CheckBox(this.name);
        this.nameCheckBox.setOnAction(event -> this.selected = nameCheckBox.isSelected());
        this.designation = designation;
        this.length = length;
        this.width = width;
        this.thickness = thickness;
        this.format = String.format(this.thickness % 1 != 0 ? "%.1f" : "%.0f", this.thickness) + "/" + String.format("%.0f", this.width);
        this.selected = state;
        this.nameCheckBox.setSelected(this.selected);
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CheckBox getNameCheckBox() {
        return nameCheckBox;
    }

    public void setNameCheckBox(CheckBox nameCheckBox) {
        this.nameCheckBox = nameCheckBox;
    }

    public Boolean isSelected() {
        return selected;
    }

    public void setSelected(Boolean state) {
        this.selected = state;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getThickness() {
        return thickness;
    }

    public void setThickness(Float thickness) {
        this.thickness = thickness;
    }

    public String getUnit() {
        return unit;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getOrderWholeLength() {
        return orderWholeLength;
    }

    public void setOrderWholeLength() {
        BigDecimal roundedLength = new BigDecimal(this.length);
        this.orderWholeLength = roundedLength.setScale(0, RoundingMode.CEILING).toString();
    }

    public void setOrderWholeLength(String orderWholeLength) {
        this.orderWholeLength = orderWholeLength;
    }

    @Override
    public String toString() {
        return "TechEdgeData{" +
                "article='" + article + '\'' +
                ", name='" + name + '\'' +
                ", nameCheckBox=" + nameCheckBox +
                ", designation='" + designation + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", thickness=" + thickness +
                '}';
    }
}
