package by.furniture.technologdata.classes.bazisXMLClasses;

/**
 * класс для хранения данных о пазах
 */
public class Groove {
    private String type;
    private String title;
    private String designation;
    private Integer amount;
    private Float length;
    private Float width;
    private Float depth;

    public Groove() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getDepth() {
        return depth;
    }

    public void setDepth(Float depth) {
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "Groove{" +
                "type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", designation='" + designation + '\'' +
                ", amount=" + amount +
                ", length=" + length +
                ", width=" + width +
                ", depth=" + depth +
                '}';
    }
}
