package by.furniture.technologdata.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChooseMaterialFrameController {
    private Stage chooseMaterialFrameStage;


    public void setChooseMaterialFrameStage(Stage chooseMaterialFrameStage) {
        this.chooseMaterialFrameStage = chooseMaterialFrameStage;
    }

    @FXML
    private Button closeButton;

    @FXML
    void onCloseButtonClick() {
        chooseMaterialFrameStage.close();
    }

    @FXML
    void initialize() {

    }
}
