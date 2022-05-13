package by.furniture.technologdata.controllers;

import by.furniture.technologdata.classes.techClasses.MaterialDB;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import static by.furniture.technologdata.StartPointLauncher.*;

public class MaterialDBViewController {
    private Stage materialDBViewStage;

    public MaterialDBViewController() {
    }

    public Stage getMaterialDBViewStage() {
        return materialDBViewStage;
    }

    public void setMaterialDBViewStage(Stage materialDBViewStage) {
        this.materialDBViewStage = materialDBViewStage;
    }

    @FXML
    private Button cancelButton;

    @FXML
    private TableView<MaterialDB> materialDBTableView;

      @FXML
    void onCancelButtonClick() {
        materialDBViewStage.close();
    }

    @FXML
    void initialize() {
        setMaterialDBTable();
    }

    private void setMaterialDBTable() {
        materialDBTableView.getColumns().clear();
        materialDBTableView.setItems(FXCollections.observableArrayList(materialDBList.values()));
        TableColumn<MaterialDB, String> articleColumn = new TableColumn<>("Артикул");
        articleColumn.setCellValueFactory(new PropertyValueFactory<>("article"));
        materialDBTableView.getColumns().add(articleColumn);
        TableColumn<MaterialDB, String> nameColumn = new TableColumn<>("Наименование");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        materialDBTableView.getColumns().add(nameColumn);
        TableColumn<MaterialDB, String> formatColumn = new TableColumn<>("Формат");
        formatColumn.setCellValueFactory(new PropertyValueFactory<>("formatChoiceBox"));
        materialDBTableView.getColumns().add(formatColumn);
        TableColumn<MaterialDB, String> thicknessColumn = new TableColumn<>("Толщина");
        thicknessColumn.setCellValueFactory(new PropertyValueFactory<>("listThickness"));
        materialDBTableView.getColumns().add(thicknessColumn);
    }
}
