package by.furniture.technologdata.classes;

public class Groove {
    private String title;
    private String designation;
    private Integer amount;
    private Float length;

    public Groove() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Groove{" +
                "title='" + title + '\'' +
                ", designation='" + designation + '\'' +
                ", amount=" + amount +
                ", length=" + length +
                '}';
    }
}
