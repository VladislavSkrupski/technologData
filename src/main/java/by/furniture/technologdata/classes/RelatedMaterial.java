package by.furniture.technologdata.classes;

public class RelatedMaterial {
    private String id;
    private String nomination;
    private String code;
    private String type;
    private Float amount;
    private Float amountFromModel;
    private Float amountByAssociate;
    private Float amountBeforeRounding;
    private String unit;
    private Float cost;
    private Float price;
    private Float coefficient;
    private String roundingMethod;
    private Float roundingAmount;
    private String sClass;
    private String syncId;
    private String note;

    public RelatedMaterial() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getAmountFromModel() {
        return amountFromModel;
    }

    public void setAmountFromModel(Float amountFromModel) {
        this.amountFromModel = amountFromModel;
    }

    public Float getAmountByAssociate() {
        return amountByAssociate;
    }

    public void setAmountByAssociate(Float amountByAssociate) {
        this.amountByAssociate = amountByAssociate;
    }

    public Float getAmountBeforeRounding() {
        return amountBeforeRounding;
    }

    public void setAmountBeforeRounding(Float amountBeforeRounding) {
        this.amountBeforeRounding = amountBeforeRounding;
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

    public Float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Float coefficient) {
        this.coefficient = coefficient;
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

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
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

    @Override
    public String toString() {
        return "RelatedMaterial{" +
                "id='" + id + '\'' +
                ", nomination='" + nomination + '\'' +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", amountFromModel=" + amountFromModel +
                ", amountByAssociate=" + amountByAssociate +
                ", amountBeforeRounding=" + amountBeforeRounding +
                ", unit='" + unit + '\'' +
                ", cost=" + cost +
                ", price=" + price +
                ", coefficient=" + coefficient +
                ", roundingMethod='" + roundingMethod + '\'' +
                ", roundingAmount=" + roundingAmount +
                ", sClass='" + sClass + '\'' +
                ", syncId='" + syncId + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
