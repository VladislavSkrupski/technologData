package by.furniture.technologdata.controllers;


import by.furniture.technologdata.StartPointLauncher;
import by.furniture.technologdata.classes.configuration.ConfigurationProperties;
import by.furniture.technologdata.classes.techClasses.MaterialDB;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;

import static by.furniture.technologdata.controllers.MainFrameController.showAddMaterialDBForm;

public class MaterialDBViewController {
    private Stage materialDBViewStage;
    @FXML
    private TableView<MaterialDB> materialDBTableView;

    public MaterialDBViewController() {
    }

    public void setMaterialDBViewStage(Stage materialDBViewStage) {
        this.materialDBViewStage = materialDBViewStage;
    }

    @FXML
    void onCancelButtonClick() {
        materialDBViewStage.close();
    }

    @FXML
    void initialize() {
        setMaterialDBTable();
    }

    @FXML
    private void refreshMaterialDB() {
        StartPointLauncher.refreshMaterialDB();
        setMaterialDBTable();
    }

    @FXML
    private void onShowAddForm() {
        showAddMaterialDBForm("");
    }

    @FXML
    private void onEditInExcelButtonClick() {
        StartPointLauncher startPointLauncher = StartPointLauncher.getStartPointLauncher();
        File selectedFile = new File(ConfigurationProperties.getConfigurationProperties().getPathToMaterialDB() + "\\materialDB.xls");
        startPointLauncher.getHostServices().showDocument(selectedFile.getAbsolutePath());
    }


    private void setMaterialDBTable() {
        materialDBTableView.getColumns().clear();
        materialDBTableView.setItems(FXCollections.observableArrayList(StartPointLauncher.materialDBList.values()));
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
