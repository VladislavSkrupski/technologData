package by.furniture.technologdata.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class OrderProductionCardController {
    private Stage orderProductionCardStage;
    @FXML
    private ChoiceBox<String> choiceBoxRM = new ChoiceBox<>(FXCollections.observableArrayList("M", "P", "Z"));
    @FXML
    Button exitButton;

    public void setOrderProductionCardStage(Stage orderProductionCardStage) {
        this.orderProductionCardStage = orderProductionCardStage;
    }

    public Stage getOrderProductionCardStage() {
        return orderProductionCardStage;
    }

    @FXML
    void initialize() {
        choiceBoxRM.show();
    }

    @FXML
    void onExitButtonClick() {
        orderProductionCardStage.close();
    }
}
