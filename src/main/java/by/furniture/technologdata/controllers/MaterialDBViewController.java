package by.furniture.technologdata.controllers;

import by.furniture.technologdata.StartPointLauncher;
import by.furniture.technologdata.classes.techClasses.MaterialDB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.HashMap;

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
        setMaterialDBTable(StartPointLauncher.materialDBList);
    }

    private void setMaterialDBTable(HashMap<String, MaterialDB> materialDBS) {
        materialDBTableView.getColumns().clear();
//        materialDBTableView.setItems(FXCollections.observableArrayList(materialDBS));
        TableColumn<MaterialDB, String> articleColumn = new TableColumn<>("Артикул");

    }
}
