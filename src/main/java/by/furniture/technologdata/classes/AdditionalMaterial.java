package by.furniture.technologdata.classes;

public class AdditionalMaterial {
    private String article;
    private String nomination;
    private Float amount;
    private String unit;

    public AdditionalMaterial() {
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getNomination() {
        return nomination;
    }

    public void setNomination(String nomination) {
        this.nomination = nomination;
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

    @Override
    public String toString() {
        return "AdditionalMaterial{" +
                "article='" + article + '\'' +
                ", nomination='" + nomination + '\'' +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                '}';
    }
}
