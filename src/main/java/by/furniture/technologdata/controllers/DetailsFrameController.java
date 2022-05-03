package by.furniture.technologdata.controllers;

import by.furniture.technologdata.classes.Detail;
import by.furniture.technologdata.classes.Panel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DetailsFrameController {
    private Stage detailsStage;

    @FXML
    private TableView<Detail> detailsTable;

    @FXML
    private void initialize() {
        ArrayList<Detail> details = new ArrayList<>();
        ArrayList<Panel> selectedPanels = new ArrayList<>(MainFrameController.panelsBySelectedMaterial(MainFrameController.panels, MainFrameController.mainMaterialCheckBoxes));
        selectedPanels.forEach(panel -> details.add(panelToDetail(panel)));
        detailsTableCreate(details);
    }

    @FXML
    private void onExitButtonClick() {
        detailsStage.close();
    }

    public void setDetailsStage(Stage detailsStage) {
        this.detailsStage = detailsStage;
    }

    public void detailsTableCreate(ArrayList<Detail> details) {
        detailsTable.getColumns().clear();
        detailsTable.setItems(FXCollections.observableList(details));
        createColumns("Код детали", "codeOfDetail");
        createColumns("Позиция", "position");
        createColumns("Наименование", "name");
        createColumns("Длина", "length");
        createColumns("Ширина", "width");
        createColumns("Толщина", "thickness");
        createColumns("Кол-во", "amount");
        createColumns("Кол-во отверстий", "holeAmount");
    }

    private void createColumns(String caption, String property) {
        TableColumn<Detail, String> column = new TableColumn<>(caption);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        detailsTable.getColumns().add(column);
    }

    public Detail panelToDetail(Panel panel) {
        int holesAmount = 0;
        if (panel.getHoles() != null) holesAmount = panel.getHoles().size() * panel.getAmount();
        Detail detail = new Detail();
        detail.setCodeOfDetail(panel.getCodeOfDetail());
        detail.setPosition(panel.getPosition());
        detail.setName(panel.getNomination());
        detail.setLength(panel.getFinishedPartLength());
        detail.setWidth(panel.getFinishedPartWidth());
        detail.setThickness(panel.getOverallThickness());
        detail.setAmount(panel.getAmount());
        detail.setHoleAmount(holesAmount);
        return detail;
    }

}
