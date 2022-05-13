package by.furniture.technologdata.classes.techClasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

import java.util.HashMap;

public class MaterialDB {
    private String article;
    private String name;
    private HashMap<String, Float[]> boardFormatsMap = new HashMap<>();
    private ChoiceBox<String> formatChoiceBox;
    private float listThickness;

    public MaterialDB(String article, String name, float listLength, float listWidth, float listThickness) {
        this.article = article;
        this.name = name;
        String formatStr = String.format("%.0f", listLength) + "х" + String.format("%.0f", listWidth);
        this.boardFormatsMap.put(formatStr, new Float[]{listLength, listWidth});
        this.listThickness = listThickness;
    }

    public void setFormatChoiceBox() {
        ObservableList<String> observableFormatList = FXCollections.observableList(this.boardFormatsMap.keySet().stream().toList());
        if (formatChoiceBox == null) {
            this.formatChoiceBox = new ChoiceBox<>(observableFormatList);
        } else {
            this.formatChoiceBox.setItems(observableFormatList);
        }
        this.formatChoiceBox.setValue(observableFormatList.stream().findFirst().get());
    }

    public ChoiceBox<String> getFormatChoiceBox() {
        return formatChoiceBox;
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

    public HashMap<String, Float[]> getBoardFormatsMap() {
        return boardFormatsMap;
    }

    public void setListFormat(HashMap<String, Float[]> boardFormatsMap) {
        this.boardFormatsMap.putAll(boardFormatsMap);
    }

    public void setListFormat(float listLength, float listWidth) {
        String formatStr = String.format("%.0f", listLength) + "х" + String.format("%.0f", listWidth);
        this.boardFormatsMap.put(formatStr, new Float[]{listLength, listWidth});
    }

    public float getListThickness() {
        return listThickness;
    }

    public void setListThickness(float listThickness) {
        this.listThickness = listThickness;
    }

    @Override
    public String toString() {
        return "MaterialDB{" + "article='" + article + '\'' + ", name='" + name + '\'' + ", listFormatsMap=" + boardFormatsMap + ", listThickness=" + listThickness + '}';
    }

    public float listSquare(String BoardFormatStr) {
        float square = 0f;
        try {
            square = (this.boardFormatsMap.get(BoardFormatStr)[0] * this.boardFormatsMap.get(BoardFormatStr)[1]) / 1000000;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return 0f;
        }
        return square;
    }
}
