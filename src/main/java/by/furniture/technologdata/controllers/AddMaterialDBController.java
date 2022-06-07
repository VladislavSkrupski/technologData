package by.furniture.technologdata.controllers;

import by.furniture.technologdata.StartPointLauncher;
import by.furniture.technologdata.classes.configuration.ConfigurationProperties;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddMaterialDBController {
    private Stage addMaterialDBStage;
    @FXML
    private TextField articleTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField lengthField;
    @FXML
    private TextField widthField;
    @FXML
    private TextField thicknessField;

    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;

    @FXML
    void initialize() {
    }

    public AddMaterialDBController() {
    }

    public Stage getAddMaterialDBStage() {
        return addMaterialDBStage;
    }

    public TextField getArticleTextField() {
        return articleTextField;
    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public TextField getLengthField() {
        return lengthField;
    }

    public TextField getWidthField() {
        return widthField;
    }

    public TextField getThicknessField() {
        return thicknessField;
    }

    public void setAddMaterialDBStage(Stage addMaterialDBStage) {
        this.addMaterialDBStage = addMaterialDBStage;
    }

    @FXML
    private void onCancelButtonClick() {
        addMaterialDBStage.close();
    }

    @FXML
    private void onOkButtonClick() {
        if (nameTextField.getText().equals("") || lengthField.getText().equals("") || widthField.getText().equals("") || thicknessField.getText().equals("")) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Ошибка");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Есть незаполненные поля!\n(допустимо не заполнять поле \"Артикул\")");
            errorAlert.showAndWait();
        } else {
            File file = new File(ConfigurationProperties.getConfigurationProperties().getPathToMaterialDB() + "\\materialDB.xls");
            try (POIFSFileSystem materialDBFile = new POIFSFileSystem(new FileInputStream(file.getAbsolutePath()))) {
                HSSFWorkbook materialDBBook = new HSSFWorkbook(materialDBFile);
                HSSFSheet materialDBSheet = materialDBBook.getSheetAt(0);
                int lastRowIndex = materialDBSheet.getPhysicalNumberOfRows();
                HSSFRow hssfRow = materialDBSheet.createRow(lastRowIndex);
                hssfRow.createCell(0).setCellValue(articleTextField.getText());
                hssfRow.createCell(1).setCellValue(nameTextField.getText());
                hssfRow.createCell(2).setCellValue(Integer.parseInt(lengthField.getText()));
                hssfRow.createCell(3).setCellValue(Integer.parseInt(widthField.getText()));
                hssfRow.createCell(4).setCellValue(Float.parseFloat(thicknessField.getText()));
                FileOutputStream outputStream = new FileOutputStream(file.getAbsolutePath());
                materialDBBook.write(outputStream);
                materialDBBook.close();
                Alert excelAlert = new Alert(Alert.AlertType.INFORMATION);
                excelAlert.setTitle("Информация");
                excelAlert.setHeaderText(null);
                excelAlert.setContentText("Файл " + file.getName() + " создан");
                excelAlert.showAndWait();
                StartPointLauncher.refreshMaterialDB();
                addMaterialDBStage.close();
            } catch (IOException e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Ошибка");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Не удалось сохранить файл " + file.getName());
                errorAlert.showAndWait();
            }
        }
    }
}
