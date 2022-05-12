package by.furniture.technologdata.classes.techClasses;

public class TechMaterialData {
    private String name;
    private String thickness;
    private String format;
    private String unit;
    private String amount;

    public TechMaterialData(String name, String thickness, String format, String unit, String amount) {
        this.name = name;
        this.thickness = thickness;
        this.format = format;
        this.unit = unit;
        this.amount = amount;
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
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
