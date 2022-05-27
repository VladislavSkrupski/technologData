package by.furniture.technologdata.controllers;

import by.furniture.technologdata.classes.configuration.ConfigurationProperties;
import by.furniture.technologdata.classes.techClasses.UnsupportedPanel;
import javafx.collections.FXCollections;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.furniture.technologdata.controllers.MainFrameController.product;

public class UnsupportedPanelsFrameController {

    private final List<UnsupportedPanel> unsupportedPanels = new ArrayList<>();
    private Stage unsupportedPanelsFrameStage = new Stage();
    @FXML
    private TableView<UnsupportedPanel> unsupportedPanelTableView;
    @FXML
    private Button exportButton;

    @FXML
    void initialize() {
        setTable(unsupportedPanels);
    }

    @FXML
    private void onCancelButtonClick() {
        unsupportedPanelsFrameStage.close();
    }

    @FXML
    private void onExportButtonClick() {
        int rowTempPoint = 0;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(ConfigurationProperties.getConfigurationProperties().getPathToSaveXLS()));
        fileChooser.setInitialFileName(product.getNomination() + " сверхгабаритные детали");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Файл EXCEL", "*.xls"));
        File file = fileChooser.showSaveDialog(exportButton.getParent().getScene().getWindow());
        if (file != null) {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
            HSSFFont titleFont = hssfWorkbook.createFont();
            titleFont.setBold(true);
            titleFont.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
            titleFont.setFontHeightInPoints((short) 0x0A);
            HSSFFont mainFont = hssfWorkbook.createFont();
            mainFont.setFontHeightInPoints((short) 0x0B);
            HSSFFont orderFont = hssfWorkbook.createFont();
            orderFont.setFontHeightInPoints((short) 0x0A);
            orderFont.setBold(true);
            HSSFFont orderDataFont = hssfWorkbook.createFont();
            orderDataFont.setFontHeightInPoints((short) 0x0A);
            HSSFFont groupTitleFont = hssfWorkbook.createFont();
            groupTitleFont.setColor(HSSFColor.HSSFColorPredefined.DARK_RED.getIndex());
            groupTitleFont.setBold(true);
            groupTitleFont.setFontHeightInPoints((short) 0x0A);
            HSSFCellStyle orderCellStyle = hssfWorkbook.createCellStyle();
            orderCellStyle.setFont(orderFont);
            HSSFCellStyle orderDataCellStyle = hssfWorkbook.createCellStyle();
            orderDataCellStyle.setFont(orderDataFont);
            HSSFCellStyle firstDataCellStyle = hssfWorkbook.createCellStyle();
            firstDataCellStyle.setBorderTop(BorderStyle.THIN);
            firstDataCellStyle.setBorderBottom(BorderStyle.THIN);
            firstDataCellStyle.setBorderLeft(BorderStyle.THIN);
            firstDataCellStyle.setBorderRight(BorderStyle.THIN);
            firstDataCellStyle.setAlignment(HorizontalAlignment.LEFT);
            firstDataCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            firstDataCellStyle.setFont(mainFont);
            HSSFCellStyle titleCellStyle = hssfWorkbook.createCellStyle();
            titleCellStyle.setBorderTop(BorderStyle.THIN);
            titleCellStyle.setBorderBottom(BorderStyle.THIN);
            titleCellStyle.setBorderLeft(BorderStyle.THIN);
            titleCellStyle.setBorderRight(BorderStyle.THIN);
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            firstDataCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            titleCellStyle.setFont(titleFont);
            titleCellStyle.setFillForegroundColor((short) 0x08);
            titleCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            HSSFCellStyle dataCellStyle = hssfWorkbook.createCellStyle();
            dataCellStyle.setBorderTop(BorderStyle.THIN);
            dataCellStyle.setBorderBottom(BorderStyle.THIN);
            dataCellStyle.setBorderLeft(BorderStyle.THIN);
            dataCellStyle.setBorderRight(BorderStyle.THIN);
            dataCellStyle.setAlignment(HorizontalAlignment.CENTER);
            dataCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            dataCellStyle.setFont(mainFont);
            HSSFCellStyle groupTitleStyle = hssfWorkbook.createCellStyle();
            groupTitleStyle.setFont(groupTitleFont);
            HSSFSheet hssfSheet = hssfWorkbook.createSheet(product.getOrder().equals("") ? "лист 1" : product.getOrder());
            HSSFRow productOrderRow = hssfSheet.createRow(rowTempPoint++);
            productOrderRow.createCell(0).setCellValue("Заказ:");
            productOrderRow.getCell(0).setCellStyle(orderCellStyle);
            productOrderRow.createCell(1).setCellValue(product.getOrder());
            productOrderRow.getCell(1).setCellStyle(orderDataCellStyle);
            HSSFRow productNominationRow = hssfSheet.createRow(rowTempPoint++);
            productNominationRow.createCell(0).setCellValue("Наименование:");
            productNominationRow.getCell(0).setCellStyle(orderCellStyle);
            productNominationRow.createCell(1).setCellValue(product.getNomination());
            productNominationRow.getCell(1).setCellStyle(orderDataCellStyle);
            HSSFRow productArticleRow = hssfSheet.createRow(rowTempPoint++);
            productArticleRow.createCell(0).setCellValue("Артикул:");
            productArticleRow.getCell(0).setCellStyle(orderCellStyle);
            productArticleRow.createCell(1).setCellValue(product.getArticle());
            productArticleRow.getCell(1).setCellStyle(orderDataCellStyle);
            rowTempPoint++;
            HSSFRow boardRow = hssfSheet.createRow(rowTempPoint++);
            boardRow.createCell(0).setCellValue("Детали, не помещающиеся на лист");
            boardRow.getCell(0).setCellStyle(groupTitleStyle);
            HSSFRow boardTitleRow = hssfSheet.createRow(rowTempPoint++);
            for (int i = 1; i < unsupportedPanelTableView.getColumns().size(); i++) {
                boardTitleRow.createCell(i - 1).setCellValue(unsupportedPanelTableView.getColumns().get(i).getText());
                boardTitleRow.getCell(i - 1).setCellStyle(titleCellStyle);
            }
            for (int rowItem = 0; rowItem < unsupportedPanelTableView.getItems().size(); rowItem++) {
                if (unsupportedPanelTableView.getItems().get(rowItem).getChecked().isSelected()) {
                    HSSFRow hssfRow = hssfSheet.createRow(rowTempPoint++);
                    for (int c = 1; c < unsupportedPanelTableView.getColumns().size(); c++) {
                        Object cellValue = unsupportedPanelTableView.getColumns().get(c).getCellObservableValue(rowItem).getValue();
                        if (cellValue != null) {
                            if (cellValue.getClass() == CheckBox.class) {
                                hssfRow.createCell(c - 1).setCellValue(((CheckBox) cellValue).getText());
                            } else {
                                hssfRow.createCell(c - 1).setCellValue(cellValue.toString());
                            }
                            if (c == 2 || c == 6) {
                                hssfRow.getCell(c - 1).setCellStyle(firstDataCellStyle);
                            } else {
                                hssfRow.getCell(c - 1).setCellStyle(dataCellStyle);
                            }
                        }
                    }
                }
            }
            hssfSheet.autoSizeColumn((short) 0);
            hssfSheet.autoSizeColumn((short) 1);
            hssfSheet.autoSizeColumn((short) 2);
            hssfSheet.autoSizeColumn((short) 3);
            hssfSheet.autoSizeColumn((short) 4);
            hssfSheet.autoSizeColumn((short) 5);
            hssfSheet.autoSizeColumn((short) 6);
            try (FileOutputStream outputStream = new FileOutputStream(file.getAbsolutePath())) {
                hssfWorkbook.write(outputStream);
                hssfWorkbook.close();
                Alert excelAlert = new Alert(Alert.AlertType.INFORMATION);
                excelAlert.setTitle("Информация");
                excelAlert.setHeaderText(null);
                excelAlert.setContentText("Файл " + file.getName() + " создан");
                excelAlert.showAndWait();
            } catch (IOException e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Ошибка");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Не удалось создать файл " + file.getName());
                errorAlert.showAndWait();
            }
        }
        unsupportedPanelsFrameStage.close();
    }

    public Stage getUnsupportedPanelsFrameStage() {
        return unsupportedPanelsFrameStage;
    }

    public void setUnsupportedPanelsFrameStage(Stage unsupportedPanelsFrameStage) {
        this.unsupportedPanelsFrameStage = unsupportedPanelsFrameStage;
    }

    public List<UnsupportedPanel> getUnsupportedPanels() {
        return unsupportedPanels;
    }

    public void setUnsupportedPanels(List<UnsupportedPanel> unsupportedPanels) {
        this.unsupportedPanels.addAll(unsupportedPanels);
    }

    private void setTable(List<UnsupportedPanel> unsupportedPanels) {
        unsupportedPanelTableView.setItems(FXCollections.observableList(unsupportedPanels));
        unsupportedPanelTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("checked"));
        unsupportedPanelTableView.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        unsupportedPanelTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("position"));
        unsupportedPanelTableView.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        unsupportedPanelTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("nomination"));
        unsupportedPanelTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("length"));
        unsupportedPanelTableView.getColumns().get(3).setStyle("-fx-alignment: CENTER;");
        unsupportedPanelTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Width"));
        unsupportedPanelTableView.getColumns().get(4).setStyle("-fx-alignment: CENTER;");
        unsupportedPanelTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("amount"));
        unsupportedPanelTableView.getColumns().get(5).setStyle("-fx-alignment: CENTER;");
        unsupportedPanelTableView.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("materialNomination"));
    }
}
