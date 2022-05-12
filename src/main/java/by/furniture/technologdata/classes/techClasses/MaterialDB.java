package by.furniture.technologdata.classes.techClasses;

import java.util.ArrayList;

public class MaterialDB {
    private String article;
    private String name;
    private ArrayList<Float[]> listFormat = new ArrayList<>();
    private float listThickness;

    public MaterialDB(String article, String name, float listLength, float listWidth, float listThickness) {
        this.article = article;
        this.name = name;
        this.listFormat.add(new Float[]{listLength, listWidth});
        this.listThickness = listThickness;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Float[]> getListFormat() {
        return listFormat;
    }

    public void setListFormat(ArrayList<Float[]> listFormat) {
        this.listFormat = listFormat;
    }

    public float getListThickness() {
        return listThickness;
    }

    public void setListThickness(float listThickness) {
        this.listThickness = listThickness;
    }

    @Override
    public String toString() {
        return "MaterialDB{" +
                "article='" + article + '\'' +
                ", name='" + name + '\'' +
                ", listFormat=" + listFormat +
                ", listThickness=" + listThickness +
                '}';
    }

    public float listSquare(int listFormatItemNum) {
        float square = 0f;
        try {
            square = (this.listFormat.get(listFormatItemNum)[0] * this.listFormat.get(listFormatItemNum)[1]) / 1000000;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return 0f;
        }
        return square;
    }
}
