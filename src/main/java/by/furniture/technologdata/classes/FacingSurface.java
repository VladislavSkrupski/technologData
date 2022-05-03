package by.furniture.technologdata.classes;

public class FacingSurface {
    private String nomination;
    private String code;
    private Float length;
    private Float width;
    private Float partLengthWithoutEdging;
    private Float partWidthWithoutEdging;
    private Float finishedPartLength;
    private Float finishedPartWidth;
    private String orientationOfTexture;
    private Float thickness;
    private Integer amount;

    public FacingSurface() {
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

    public Float getPartLengthWithoutEdging() {
        return partLengthWithoutEdging;
    }

    public void setPartLengthWithoutEdging(Float partLengthWithoutEdging) {
        this.partLengthWithoutEdging = partLengthWithoutEdging;
    }

    public Float getPartWidthWithoutEdging() {
        return partWidthWithoutEdging;
    }

    public void setPartWidthWithoutEdging(Float partWidthWithoutEdging) {
        this.partWidthWithoutEdging = partWidthWithoutEdging;
    }

    public Float getFinishedPartLength() {
        return finishedPartLength;
    }

    public void setFinishedPartLength(Float finishedPartLength) {
        this.finishedPartLength = finishedPartLength;
    }

    public Float getFinishedPartWidth() {
        return finishedPartWidth;
    }

    public void setFinishedPartWidth(Float finishedPartWidth) {
        this.finishedPartWidth = finishedPartWidth;
    }

    public String getOrientationOfTexture() {
        return orientationOfTexture;
    }

    public void setOrientationOfTexture(String orientationOfTexture) {
        this.orientationOfTexture = orientationOfTexture;
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

    @Override
    public String toString() {
        return "FacingSurface{" +
                "nomination='" + nomination + '\'' +
                ", code='" + code + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", partLengthWithoutEdging=" + partLengthWithoutEdging +
                ", partWidthWithoutEdging=" + partWidthWithoutEdging +
                ", finishedPartLength=" + finishedPartLength +
                ", finishedPartWidth=" + finishedPartWidth +
                ", orientationOfTexture='" + orientationOfTexture + '\'' +
                ", thickness=" + thickness +
                ", amount=" + amount +
                '}';
    }
}
