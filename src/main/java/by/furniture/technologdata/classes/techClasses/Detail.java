package by.furniture.technologdata.classes.techClasses;

public class Detail {
    private String codeOfDetail;
    private String position;
    private String name;
    private Float length;
    private Float width;
    private Float thickness;
    private Integer amount;
    private Integer holeAmount;
//------

    public Detail() {
    }

    public String getCodeOfDetail() {
        return codeOfDetail;
    }

    public void setCodeOfDetail(String codeOfDetail) {
        this.codeOfDetail = codeOfDetail;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getHoleAmount() {
        return holeAmount;
    }

    public void setHoleAmount(Integer holeAmount) {
        this.holeAmount = holeAmount;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "codeOfDetail='" + codeOfDetail + '\'' +
                ", position='" + position + '\'' +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", thickness=" + thickness +
                ", amount=" + amount +
                ", holeAmount=" + holeAmount +
                '}';
    }
}
