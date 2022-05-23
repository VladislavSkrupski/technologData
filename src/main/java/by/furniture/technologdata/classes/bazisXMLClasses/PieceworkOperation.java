package by.furniture.technologdata.classes.bazisXMLClasses;

/**
 * класс для хранения данных о сдельных операциях
 */
public class PieceworkOperation {
    private String nomination;
    private String code;
    private Float amount;
    private String unit;
    private Float cost;
    private Float price;
    private Float laborIntensity;
    private String roundingMethod;
    private Float roundingAmount;
    private String wClass;
    private String syncId;
    private String note;
    private String priority;

    public PieceworkOperation() {
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

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getLaborIntensity() {
        return laborIntensity;
    }

    public void setLaborIntensity(Float laborIntensity) {
        this.laborIntensity = laborIntensity;
    }

    public String getRoundingMethod() {
        return roundingMethod;
    }

    public void setRoundingMethod(String roundingMethod) {
        this.roundingMethod = roundingMethod;
    }

    public Float getRoundingAmount() {
        return roundingAmount;
    }

    public void setRoundingAmount(Float roundingAmount) {
        this.roundingAmount = roundingAmount;
    }

    public String getwClass() {
        return wClass;
    }

    public void setwClass(String wClass) {
        this.wClass = wClass;
    }

    public String getSyncId() {
        return syncId;
    }

    public void setSyncId(String syncId) {
        this.syncId = syncId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "PieceworkOperation{" +
                "nomination='" + nomination + '\'' +
                ", code='" + code + '\'' +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                ", cost=" + cost +
                ", price=" + price +
                ", laborIntensity=" + laborIntensity +
                ", roundingMethod='" + roundingMethod + '\'' +
                ", roundingAmount=" + roundingAmount +
                ", wClass='" + wClass + '\'' +
                ", syncId='" + syncId + '\'' +
                ", note='" + note + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }
}
