package by.furniture.technologdata.controllers;

import by.furniture.technologdata.classes.techClasses.PanelsByMaterial;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class OrderProductionCardController {
    private Stage orderProductionCardStage;
    @FXML
    private ChoiceBox<String> choiceBoxRM = new ChoiceBox<>();
    @FXML
    private Button exitButton;

    @FXML
    private Label orderNameLabel;

    @FXML
    TableColumn<PanelsByMaterial,String> materialNameTableColumn;

    @FXML
    TableColumn<PanelsByMaterial,String> edgeNameTableColumn;

    @FXML
    TableColumn<PanelsByMaterial,String> edgeLengthTableColumn;

    @FXML
    private TableView<PanelsByMaterial> byMaterialTableView;


    public void setOrderProductionCardStage(Stage orderProductionCardStage) {
        this.orderProductionCardStage = orderProductionCardStage;
    }

    public Stage getOrderProductionCardStage() {
        return orderProductionCardStage;
    }

    @FXML
    void initialize() {
        orderNameLabel.setText(MainFrameController.product.getNomination());
        byMaterialTableView.setItems(FXCollections.observableList(MainFrameController.panelsByMaterialArrayList));
        materialNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("nomination"));
        edgeNameTableColumn.setCellValueFactory((new PropertyValueFactory<>("edgesWithLengthMap")));

    }

    @FXML
    void onExitButtonClick() {
        orderProductionCardStage.close();
    }

    private void createColumns(String caption, String property) {
        TableColumn<PanelsByMaterial, String> column = new TableColumn<>(caption);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        byMaterialTableView.getColumns().add(column);
    }
}
