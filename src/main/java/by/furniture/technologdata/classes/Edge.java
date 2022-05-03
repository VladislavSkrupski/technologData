package by.furniture.technologdata.classes;

public class Edge {
    private String nomination;
    private String code;
    private String designation;
    private Float length;
    private Float width;
    private Float thickness;

    public Edge() {
    }

    public String getNomination() {
        return nomination;
    }

    public void setNomination(String nomination) {
        this.nomination = nomination;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public String toString() {
        return "Edge{" +
                "nomination='" + nomination + '\'' +
                ", code='" + code + '\'' +
                ", designation='" + designation + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", thickness=" + thickness +
                '}';
    }
}
