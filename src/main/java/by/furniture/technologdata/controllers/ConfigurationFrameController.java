package by.furniture.technologdata.controllers;

import by.furniture.technologdata.classes.configuration.ConfigurationProperties;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class ConfigurationFrameController {
    private Stage configurationFrameStage;

    @FXML
    private TextField pathToXMLTextField;
    @FXML
    private TextField pathToXLSTextField;
    @FXML
    private TextField pathToMaterialDBTextField;
    @FXML
    private TextField edgeCoefficientTextField;
    @FXML
    private TextField materialCoefficientTextField;
    @FXML
    private Button pathToXMLButton;
    @FXML
    private Button pathToXLSButton;
    @FXML
    private Button pathToMaterialDBButton;
    @FXML
    private Button OKButton;
    @FXML
    private Button cancelButton;

    public ConfigurationFrameController() {
    }

    @FXML
    void initialize() {
        edgeCoefficientTextField.setText(String.valueOf(ConfigurationProperties.getConfigurationProperties().getEdgeCoefficient()));
        materialCoefficientTextField.setText(String.valueOf(ConfigurationProperties.getConfigurationProperties().getMaterialCoefficient()));
        pathToXMLTextField.setText(ConfigurationProperties.getConfigurationProperties().getPathToOpenXML());
        pathToXLSTextField.setText(ConfigurationProperties.getConfigurationProperties().getPathToSaveXLS());
        pathToMaterialDBTextField.setText(ConfigurationProperties.getConfigurationProperties().getPathToMaterialDB());
        pathToXMLButton.setOnAction(actionEvent -> pathToXMLTextField.setText(getPath(pathToXMLTextField.getText())));
        pathToXLSButton.setOnAction(actionEvent -> pathToXLSTextField.setText(getPath(pathToXLSTextField.getText())));
        pathToMaterialDBButton.setOnAction(actionEvent -> pathToMaterialDBTextField.setText(getPath(pathToMaterialDBTextField.getText())));
        cancelButton.setOnAction(actionEvent -> configurationFrameStage.close());
        edgeCoefficientTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*\\.?\\d+")) {
                edgeCoefficientTextField.setText(newValue.replaceAll("[^\\d]", "")); //TODO check regex!
            }
        });
        materialCoefficientTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*\\.?\\d+")) {
                materialCoefficientTextField.setText(newValue.replaceAll("[^\\d]", "")); //TODO check regex!
            }
        });
    }

    public Stage getConfigurationFrameStage() {
        return configurationFrameStage;
    }

    public void setConfigurationFrameStage(Stage configurationFrameStage) {
        this.configurationFrameStage = configurationFrameStage;
    }

    private String getPath(String startPath) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File startFile = new File(startPath);
        if (startFile.exists()) {
            directoryChooser.setInitialDirectory(startFile);
        }
        File file = directoryChooser.showDialog(configurationFrameStage.getOwner());
        return file == null ? startPath : file.getPath();
    }
}
