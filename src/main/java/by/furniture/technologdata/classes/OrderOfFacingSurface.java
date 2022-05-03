package by.furniture.technologdata.classes;

public class OrderOfFacingSurface {
    private String nomination;
    private String code;

    public OrderOfFacingSurface() {
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

    @Override
    public String toString() {
        return "OrderOfFacingSurface{" +
                "nomination='" + nomination + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
