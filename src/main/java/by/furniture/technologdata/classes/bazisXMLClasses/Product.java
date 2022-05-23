package by.furniture.technologdata.classes.bazisXMLClasses;

/**
 * класс для хранения данных о заказе
 */
public class Product {
    private String nomination;
    private String article;
    private String order;
    private Integer amount;
    private String developer;
    private String objectType;
    private String position;
    private String cost;

    public Product() {
    }

    public String getNomination() {
        return nomination;
    }

    public void setNomination(String nomination) {
        this.nomination = nomination;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "nomination='" + nomination + '\'' +
                ", article='" + article + '\'' +
                ", order='" + order + '\'' +
                ", amount=" + amount +
                ", developer='" + developer + '\'' +
                ", objectType='" + objectType + '\'' +
                ", position='" + position + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
