package by.furniture.technologdata.classes.techClasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

import java.util.HashMap;

/**
 * Класс овечает за листовой материал и его возможные форматы, получаемый из внешнего списка
 */
public class MaterialDB {
    private String article;
    private String name;
    private final HashMap<String, Float[]> boardFormatsMap = new HashMap<>();
    private ChoiceBox<String> formatChoiceBox;
    private float listThickness;

    /**
     * @param article       артикул материала
     * @param name          наименование материала
     * @param listLength    длина листа материала
     * @param listWidth     ширина листа материала
     * @param listThickness толщина листа материала
     */
    public MaterialDB(String article, String name, float listLength, float listWidth, float listThickness) {
        this.article = article;
        this.name = name;
        String formatStr = String.format("%.0f", listLength) + "х" + String.format("%.0f", listWidth);
        this.boardFormatsMap.put(formatStr, new Float[]{listLength, listWidth});
        this.listThickness = listThickness;
    }

    /**
     * Создаёт или дописывает ChoiceBox со списком доступных форматов листа материала
     */
    public void setFormatChoiceBox() {
        ObservableList<String> observableFormatList = FXCollections.observableList(this.boardFormatsMap.keySet().stream().toList());
        if (formatChoiceBox == null) {
            this.formatChoiceBox = new ChoiceBox<>(observableFormatList);
        } else {
            this.formatChoiceBox.setItems(observableFormatList);
        }
        this.formatChoiceBox.setValue(observableFormatList.stream().findFirst().get());
    }

    /**
     * @return ChoiceBox со списком доступных форматов листа материала
     */
    public ChoiceBox<String> getFormatChoiceBox() {
        return formatChoiceBox;
    }

    /**
     * @return возвращает артикул материала
     */
    public String getArticle() {
        return article;
    }

    /**
     * @param article устанавливает артикул материала
     */
    public void setArticle(String article) {
        this.article = article;
    }

    /**
     * @return возвращает наименование материала
     */
    public String getName() {
        return name;
    }

    /**
     * @param name устанавливает наименование материала
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return возвращает HashMap доступных форматов листа
     */
    public HashMap<String, Float[]> getBoardFormatsMap() {
        return boardFormatsMap;
    }

    /**
     * добавляет в HashMap уникальные форматы листа материала
     *
     * @param boardFormatsMap HashMap форматов листа
     */
    public void setListFormat(HashMap<String, Float[]> boardFormatsMap) {
        this.boardFormatsMap.putAll(boardFormatsMap);
    }

    /**
     * добавляет в HashMap уникальные форматы листа материала
     *
     * @param listLength длина листа материала
     * @param listWidth  ширина листа материала
     */
    public void setListFormat(float listLength, float listWidth) {
        String formatStr = String.format("%.0f", listLength) + "х" + String.format("%.0f", listWidth);
        this.boardFormatsMap.put(formatStr, new Float[]{listLength, listWidth});
    }

    /**
     * @return толщина листа
     */
    public float getListThickness() {
        return listThickness;
    }

    /**
     * @param listThickness толщина листа
     */
    public void setListThickness(float listThickness) {
        this.listThickness = listThickness;
    }

    @Override
    public String toString() {
        return "MaterialDB{" + "article='" + article + '\'' + ", name='" + name + '\'' + ", listFormatsMap=" + boardFormatsMap + ", listThickness=" + listThickness + '}';
    }

    /**
     * Расчитывает площадь листа материала в квадратных метрах
     *
     * @param BoardFormatStr получает строку с описанием формата листа
     * @return площадь листа
     */
    public float listSquare(String BoardFormatStr) {
        float square;
        try {
            square = (this.boardFormatsMap.get(BoardFormatStr)[0] * this.boardFormatsMap.get(BoardFormatStr)[1]) / 1000000;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return 0f;
        }
        return square;
    }
}
