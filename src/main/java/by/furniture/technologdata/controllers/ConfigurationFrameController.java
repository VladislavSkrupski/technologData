package by.furniture.technologdata.controllers;

import by.furniture.technologdata.classes.configuration.ConfigurationProperties;
import by.furniture.technologdata.interfaces.AdditionalOptions;
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
        AdditionalOptions.checkFloatInput(edgeCoefficientTextField);
        AdditionalOptions.checkFloatInput(materialCoefficientTextField);
    }

    @FXML
    private void onOKButtonClick() {
        ConfigurationProperties.getConfigurationProperties().setEdgeCoefficient(Float.parseFloat(edgeCoefficientTextField.getText()));
        ConfigurationProperties.getConfigurationProperties().setMaterialCoefficient(Float.parseFloat(materialCoefficientTextField.getText()));
        if (!pathToXMLTextField.getText().equals("")) {
            ConfigurationProperties.getConfigurationProperties().setPathToOpenXML(pathToXMLTextField.getText());
        } else {
            ConfigurationProperties.getConfigurationProperties().setPathToOpenXML(System.getProperty("user.dir"));
        }
        if (!pathToXLSTextField.getText().equals("")) {
            ConfigurationProperties.getConfigurationProperties().setPathToSaveXLS(pathToXLSTextField.getText());
        } else {
            ConfigurationProperties.getConfigurationProperties().setPathToSaveXLS(System.getProperty("user.dir"));
        }
        if (!pathToMaterialDBTextField.getText().equals("")) {
            ConfigurationProperties.getConfigurationProperties().setPathToMaterialDB(pathToMaterialDBTextField.getText());
        } else {
            ConfigurationProperties.getConfigurationProperties().setPathToMaterialDB(System.getProperty("user.dir"));
        }
        ConfigurationProperties.savePropertiesToFile();
        configurationFrameStage.close();
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
