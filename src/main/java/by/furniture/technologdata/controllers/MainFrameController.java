package by.furniture.technologdata.controllers;

import by.furniture.technologdata.StartPointLauncher;
import by.furniture.technologdata.classes.FacingSurface;
import by.furniture.technologdata.classes.Panel;
import by.furniture.technologdata.classes.Product;
import by.furniture.technologdata.classes.techClasses.TechEdgeData;
import by.furniture.technologdata.classes.techClasses.TechMaterialData;
import by.furniture.technologdata.interfaces.BazisXMLTags;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.TreeSet;

import static by.furniture.technologdata.StartPointLauncher.*;

public class MainFrameController implements BazisXMLTags {

    public static ArrayList<Panel> panels = new ArrayList<>();
    private final TreeSet<String> mainMaterials = new TreeSet<>();
    private final ArrayList<TechMaterialData> techMaterialDataArrayList = new ArrayList<>();
    private final ArrayList<TechEdgeData> techEdgeDataArrayList = new ArrayList<>();
    public static ArrayList<CheckBox> mainMaterialCheckBoxes = new ArrayList<>();
    public static Product product = new Product();

    @FXML
    private MenuItem openFile;
    @FXML
    private MenuItem dataBaseMenuItem;
    @FXML
    private Label fullEdgeLengthLabel;
    @FXML
    private Button exportButton;
    @FXML
    private Label productOrderLabel;
    @FXML
    private Label productNominationLabel;
    @FXML
    private Label productArticleLabel;
    @FXML
    private Label productDeveloperLabel;
    @FXML
    private VBox materialCheckList;
    @FXML
    private TableView<TechMaterialData> techMaterialTable;
    @FXML
    private TableView<TechEdgeData> techEdgeTable;

    @FXML
    void onExitClick() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void onOpenFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Спецификация Bazis XML", "*.xml"));
        File selectedFile = fileChooser.showOpenDialog(openFile.getParentPopup().getScene().getWindow());
        if (selectedFile != null) {
            panels.clear();
            mainMaterials.clear();
            ArrayList<Node> arrayNodes;
            ArrayList<Node> panelNodes = new ArrayList<>();
            try {
                DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = documentBuilder.parse(selectedFile);
                Node root = document.getDocumentElement();
                arrayNodes = getTagArray(root, new ArrayList<>());
                for (Node node : arrayNodes) {
                    if (node.getNodeType() != Node.TEXT_NODE && node.getNodeName().equals(BazisXMLTags.PRODUCT)) {
                        NodeList productNodeList = node.getChildNodes();
                        for (int a = 0; a < productNodeList.getLength(); a++) {
                            if (productNodeList.item(a).getNodeType() != Node.TEXT_NODE) {
                                switch (productNodeList.item(a).getNodeName()) {
                                    case BazisXMLTags.NOMINATION: {
                                        product.setNomination(productNodeList.item(a).getTextContent());
                                        break;
                                    }
                                    case BazisXMLTags.ARTICLE: {
                                        product.setArticle(productNodeList.item(a).getTextContent());
                                        break;
                                    }
                                    case BazisXMLTags.ORDER: {
                                        product.setOrder(productNodeList.item(a).getTextContent());
                                        break;
                                    }
                                    case BazisXMLTags.QUANTITY: {
                                        product.setAmount(Integer.parseInt(productNodeList.item(a).getTextContent()));
                                        break;
                                    }
                                    case BazisXMLTags.DEVELOPER: {
                                        product.setDeveloper(productNodeList.item(a).getTextContent());
                                        break;
                                    }
                                    case BazisXMLTags.OBJECT_TYPE: {
                                        product.setObjectType(productNodeList.item(a).getTextContent());
                                        break;
                                    }
                                    case BazisXMLTags.POSITION: {
                                        product.setPosition(productNodeList.item(a).getTextContent());
                                        break;
                                    }
                                    case BazisXMLTags.COST: {
                                        product.setCost(productNodeList.item(a).getTextContent());
                                        break;
                                    }
                                    default: {
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
                for (Node node : arrayNodes) {
                    if (node.getNodeName().equals(BazisXMLTags.OBJECT)) {
                        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                            if (node.getChildNodes().item(i).getNodeName().equals(BazisXMLTags.OBJECT_TYPE) &&
                                    node.getChildNodes().item(i).getTextContent().equals("Панель")) {
                                panelNodes.add(node);
                            }
                        }
                    }
                }
                for (Node panelNode : panelNodes) {
                    panels.add(NodeToPanel(panelNode));
                }
            } catch (IOException | ParserConfigurationException | SAXException e) {
                e.printStackTrace();
            }
            for (Panel panel : panels) {
                mainMaterials.add(panel.getMainMaterial().getNomination());
            }
            mainMaterialCheckBoxes.clear();
            materialCheckList.getChildren().clear();
            productOrderLabel.setText(product.getOrder());
            productNominationLabel.setText(product.getNomination());
            productArticleLabel.setText(product.getArticle());
            productDeveloperLabel.setText(product.getDeveloper());
            for (String str : mainMaterials) {
                mainMaterialCheckBoxes.add(new CheckBox(str));
            }
            for (CheckBox chk : mainMaterialCheckBoxes) {
                chk.setSelected(true);
                chk.setPadding(new Insets(5, 5, 5, 5));
                chk.setOnAction(actionEvent -> resetTableData(panels));
                materialCheckList.getChildren().add(chk);
            }
            setDataToArrayList(panels);
            setMaterialTable(techMaterialDataArrayList);
            exportButton.setDisable(false);
            materialDBList.forEach((k, v) -> v.getFormatChoiceBox().setOnAction(actionEvent -> resetTableData(panels)));
            setEdgeTable(techEdgeDataArrayList);

        }
    }

    @FXML
    void onExportToXLS() {
        int rowTempPoint = 0;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(product.getNomination()+"-черновой");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Файл EXCEL", "*.xls"));
        File file = fileChooser.showSaveDialog(exportButton.getParent().getScene().getWindow());
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
        boardRow.createCell(0).setCellValue("Плитные материалы");
        boardRow.getCell(0).setCellStyle(groupTitleStyle);
        HSSFRow boardTitleRow = hssfSheet.createRow(rowTempPoint++);
        for (int i = 0; i < techMaterialTable.getColumns().size(); i++) {
            boardTitleRow.createCell(i).setCellValue(techMaterialTable.getColumns().get(i).getText());
            boardTitleRow.getCell(i).setCellStyle(titleCellStyle);
        }
        for (int rowItem = 0; rowItem < techMaterialTable.getItems().size(); rowItem++) {
            if (techMaterialTable.getItems().get(rowItem).getMaterialNameCheckBox().isSelected()) {
                HSSFRow hssfRow = hssfSheet.createRow(rowTempPoint++);
                for (int c = 0; c < techMaterialTable.getColumns().size(); c++) {
                    Object cellValue = techMaterialTable.getColumns().get(c).getCellObservableValue(rowItem).getValue();
                    if (cellValue != null) {
                        if (cellValue.getClass() == CheckBox.class) {
                            hssfRow.createCell(c).setCellValue(((CheckBox) cellValue).getText());
                        } else if (cellValue.getClass() == ChoiceBox.class) {
                            hssfRow.createCell(c).setCellValue(((ChoiceBox<?>) cellValue).getValue().toString());
                        } else {
                            hssfRow.createCell(c).setCellValue(cellValue.toString());
                        }
                        if (c == 0) {
                            hssfRow.getCell(c).setCellStyle(firstDataCellStyle);
                        } else {
                            hssfRow.getCell(c).setCellStyle(dataCellStyle);
                        }
                    }
                }
            }
        }
        rowTempPoint++;
        HSSFRow edgeRow = hssfSheet.createRow(rowTempPoint++);
        edgeRow.createCell(0).setCellValue("Кромочные материалы");
        edgeRow.getCell(0).setCellStyle(groupTitleStyle);
        HSSFRow edgeTitleRow = hssfSheet.createRow(rowTempPoint++);
        for (int i = 0; i < techEdgeTable.getColumns().size(); i++) {
            edgeTitleRow.createCell(i).setCellValue(techEdgeTable.getColumns().get(i).getText());
            edgeTitleRow.getCell(i).setCellStyle(titleCellStyle);
        }
        for (int rowItem = 0; rowItem < techEdgeTable.getItems().size(); rowItem++) {
            if (techEdgeTable.getItems().get(rowItem).getNameCheckBox().isSelected()) {
                HSSFRow hssfRow = hssfSheet.createRow(rowTempPoint++);
                for (int c = 0; c < techEdgeTable.getColumns().size(); c++) {
                    Object cellValue = techEdgeTable.getColumns().get(c).getCellObservableValue(rowItem).getValue();
                    if (cellValue != null) {
                        if (cellValue.getClass() == CheckBox.class) {
                            hssfRow.createCell(c).setCellValue(((CheckBox) cellValue).getText());
                            hssfRow.getCell(c).getCellStyle().setAlignment(HorizontalAlignment.LEFT);
                        } else {
                            hssfRow.createCell(c).setCellValue(cellValue.toString());
                        }
                        if (c == 0) {
                            hssfRow.getCell(c).setCellStyle(firstDataCellStyle);
                        } else {
                            hssfRow.getCell(c).setCellStyle(dataCellStyle);
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
        if (file != null) {
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
    }

    @FXML
    void onDataBaseMenuItemClick() {
        FXMLLoader materialDBViewLoader = new FXMLLoader();
        try {
            materialDBViewLoader.setLocation(StartPointLauncher.class.getResource("fxml/materialDBView.fxml"));
            VBox frame = materialDBViewLoader.load();
            Stage materialDBViewStage = new Stage();
            materialDBViewStage.setTitle("База плитных материалов");
            materialDBViewStage.initModality(Modality.WINDOW_MODAL);
            materialDBViewStage.initOwner(getMainStage());
            Scene materialDBViewScene = new Scene(Objects.requireNonNull(frame));
            materialDBViewStage.setScene(materialDBViewScene);
            MaterialDBViewController controller = materialDBViewLoader.getController();
            controller.setMaterialDBViewStage(materialDBViewStage);
            materialDBViewStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void setDataToArrayList(ArrayList<Panel> panelArrayList) {
        techMaterialDataArrayList.clear();
        techMaterialDataArrayList.addAll(overallSquareOfMaterials(panelArrayList));
        techEdgeDataArrayList.clear();
        techEdgeDataArrayList.addAll(overallEdgeLength(panelArrayList));
    }

    void resetTableData(ArrayList<Panel> panels) {
        setDataToArrayList(panelsBySelectedMaterial(panels, mainMaterialCheckBoxes));
        setMaterialTable(techMaterialDataArrayList);
        setEdgeTable(techEdgeDataArrayList);
    }

    //----------------------Проверка чек-боксов------------------
    public static ArrayList<Panel> panelsBySelectedMaterial(ArrayList<Panel> allPanels, ArrayList<CheckBox> checkBoxMaterialList) {
        ArrayList<Panel> selectedMaterialPanels = new ArrayList<>();
        for (CheckBox chk : checkBoxMaterialList) {
            if (chk.isSelected()) {
                for (Panel panel : allPanels) {
                    if (panel.getMainMaterial().getNomination().equals(chk.getText())) {
                        selectedMaterialPanels.add(panel);
                    }
                }
            }
        }
        return selectedMaterialPanels;
    }

    //----------------------Конечные данные---------------------
    public ArrayList<TechEdgeData> overallEdgeLength(ArrayList<Panel> panelArrayList) {
        ArrayList<TechEdgeData> edgeData = new ArrayList<>();
        HashMap<String, TechEdgeData> edgesMap = new HashMap<>();
        float fullLength = 0.0f;
        for (Panel panel : panelArrayList) {
            for (int a = 0; a < panel.getEdgeLists().size(); a++) {
                if (panel.getEdgeLists().get(a).size() > 0) {
                    panel.getEdgeLists().get(a).forEach(edge -> {
                        if (edgesMap.containsKey(edge.getNomination())) {
                            float tempLength = edgesMap.get(edge.getNomination()).getLength() + edge.getLength() * panel.getAmount();
                            edgesMap.get(edge.getNomination()).setLength(tempLength);
                        } else {
                            edgesMap.put(edge.getNomination(), new TechEdgeData(
                                    edge.getCode(),
                                    edge.getNomination(),
                                    edge.getDesignation(),
                                    edge.getLength() * panel.getAmount(),
                                    edge.getWidth(),
                                    edge.getThickness())
                            );
                        }
                    });
                }
            }
        }
        // Сумма чистой кромки
        for (TechEdgeData techEdgeData : edgesMap.values()) {
            fullLength += techEdgeData.getLength();
        }
        // Увеличение длины кромки на коэффициент
        edgesMap.forEach((k, v) -> {
            v.setLength((v.getLength() * configurationProperties.getEdgeCoefficient()) / 1000);
            v.setOrderWholeLength();
            edgeData.add(v);
        });
        fullEdgeLengthLabel.setText("Производственный метраж кромки - " + String.format("%.0f", fullLength / 1000) + " м");
        fullEdgeLengthLabel.setStyle("-fx-font-size: 12px; -fx-border-color: black");
        return edgeData;
    }

    public ArrayList<TechMaterialData> overallSquareOfMaterials(ArrayList<Panel> panelArrayList) {
        ArrayList<TechMaterialData> materialsSquareData = new ArrayList<>();
        ArrayList<FacingSurface> facingSurfacesList = new ArrayList<>();
        TreeSet<String> mainMaterialNames = new TreeSet<>();
        TreeSet<String> facingSurfaceNames = new TreeSet<>();
        HashMap<String, Float> mainMaterialSquareMap = new HashMap<>();
        HashMap<String, Float> facingSurfaceSquareMap = new HashMap<>();
        for (Panel p : panelArrayList) {
            mainMaterialNames.add(p.getMainMaterial().getNomination());
            if (p.getFacingSurfaces() != null) {
                for (int a = 0; a < p.getFacingSurfaces().size(); a++) {
                    if (p.getFacingSurfaces().get(a) != null) {
                        for (FacingSurface facingSurface : p.getFacingSurfaces().get(a)) {
                            facingSurfacesList.add(facingSurface);
                            facingSurfaceNames.add(facingSurface.getNomination());
                        }
                    }
                }
            }
        }
        for (String str : mainMaterialNames) {
            float square = 0.0f;
            for (Panel p : panelArrayList) {
                if (p.getMainMaterial().getNomination().equals(str)) {
                    square += ((p.getLength() / 1000) * (p.getWidth() / 1000) * p.getAmount());
                }
            }
            mainMaterialSquareMap.put(str, square);
        }
        for (String str : facingSurfaceNames) {
            float square = 0.0f;
            for (FacingSurface facingSurface : facingSurfacesList) {
                if (facingSurface.getNomination().equals(str)) {
                    square += (facingSurface.getLength() / 1000) * (facingSurface.getWidth() / 1000) * facingSurface.getAmount();
                }
            }
            facingSurfaceSquareMap.put(str, square);
        }
        mainMaterialNames.addAll(facingSurfaceNames);
        for (String str : mainMaterialNames) {
            if (materialDBList.containsKey(str)) {
                String format = materialDBList.get(str).getFormatChoiceBox().getValue();
                if (facingSurfaceSquareMap.get(str) != null) {
                    float squareInList = (mainMaterialSquareMap.get(str) == null ? 0 : mainMaterialSquareMap.get(str)) + facingSurfaceSquareMap.get(str);
                    squareInList = (squareInList * configurationProperties.getMaterialCoefficient()) / materialDBList.get(str).listSquare(format);
                    BigDecimal square = new BigDecimal(squareInList);
                    materialsSquareData.add(new TechMaterialData(
                            str,
                            String.format("%.0f", materialDBList.get(str).getListThickness()),
                            materialDBList.get(str).getBoardFormatsMap(),
                            materialDBList.get(str).getFormatChoiceBox(),
                            "лист",
                            square.setScale(0, RoundingMode.CEILING).toString()));
                } else {
                    float squareInList = (mainMaterialSquareMap.get(str) * configurationProperties.getMaterialCoefficient()) / materialDBList.get(str).listSquare(format);
                    BigDecimal square = new BigDecimal(squareInList);
                    materialsSquareData.add(new TechMaterialData(
                            str,
                            String.format("%.0f", materialDBList.get(str).getListThickness()),
                            materialDBList.get(str).getBoardFormatsMap(),
                            materialDBList.get(str).getFormatChoiceBox(),
                            "лист",
                            square.setScale(0, RoundingMode.CEILING).toString()));
                }
            }
        }
        return materialsSquareData;
    }

    //----------------------Вывод в таблицу---------------------
    void setMaterialTable(ArrayList<TechMaterialData> techDataList) {
        techMaterialTable.getColumns().clear();
        techMaterialTable.setItems(FXCollections.observableArrayList(techDataList));
        TableColumn<TechMaterialData, String> nameColumn = new TableColumn<>("Наименование");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("materialNameCheckBox"));
        nameColumn.setEditable(true);
        techMaterialTable.getColumns().add(nameColumn);
        TableColumn<TechMaterialData, String> thicknessColumn = new TableColumn<>("Толщина");
        thicknessColumn.editableProperty().setValue(true);
        thicknessColumn.setStyle("-fx-alignment: CENTER;");
        thicknessColumn.setCellValueFactory(new PropertyValueFactory<>("thickness"));
        techMaterialTable.getColumns().add(thicknessColumn);
        TableColumn<TechMaterialData, String> formatColumn = new TableColumn<>("Формат листа");
        formatColumn.setCellValueFactory(new PropertyValueFactory<>("formatChoiceBox"));
        formatColumn.setEditable(true);
        formatColumn.setStyle("-fx-alignment: CENTER;");
        techMaterialTable.getColumns().add(formatColumn);
        TableColumn<TechMaterialData, String> unitColumn = new TableColumn<>("Ед. изм.");
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("Unit"));
        unitColumn.setEditable(true);
        unitColumn.setStyle("-fx-alignment: CENTER;");
        techMaterialTable.getColumns().add(unitColumn);
        TableColumn<TechMaterialData, String> amountColumn = new TableColumn<>("Кол-во");
        amountColumn.editableProperty().setValue(true);
        amountColumn.setStyle("-fx-alignment: CENTER;");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        techMaterialTable.getColumns().add(amountColumn);
    }

    void setEdgeTable(ArrayList<TechEdgeData> techDataList) {
        techEdgeTable.getColumns().clear();
        techEdgeTable.setItems(FXCollections.observableArrayList(techDataList));
        TableColumn<TechEdgeData, String> nameColumn = new TableColumn<>("Наименование");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameCheckBox"));
        nameColumn.setEditable(true);
        techEdgeTable.getColumns().add(nameColumn);
        TableColumn<TechEdgeData, String> formatColumn = new TableColumn<>("Формат");
        formatColumn.editableProperty().setValue(true);
        formatColumn.setStyle("-fx-alignment: CENTER;");
        formatColumn.setCellValueFactory(new PropertyValueFactory<>("format"));
        techEdgeTable.getColumns().add(formatColumn);
        TableColumn<TechEdgeData, String> unitColumn = new TableColumn<>("Ед. изм.");
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
        unitColumn.setEditable(true);
        unitColumn.setStyle("-fx-alignment: CENTER;");
        techEdgeTable.getColumns().add(unitColumn);
        TableColumn<TechEdgeData, String> lengthColumn = new TableColumn<>("Кол-во");
        lengthColumn.editableProperty().setValue(true);
        lengthColumn.setStyle("-fx-alignment: CENTER;");
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("orderWholeLength"));
        techEdgeTable.getColumns().add(lengthColumn);
    }

    /*public ArrayList<PanelsByMaterial> getPanelsByMaterial(ArrayList<Panel> allPanels) {
        HashMap<String, ArrayList<Panel>> panelsByMaterialMap = new HashMap<>();
        for (Panel panel : allPanels) {
            String materialName = panel.getMainMaterial().getNomination();
            if (panelsByMaterialMap.containsKey(materialName)) {
                panelsByMaterialMap.get(materialName).add(panel);
            } else {
                ArrayList<Panel> panelArrayList = new ArrayList<>();
                panelArrayList.add(panel);
                panelsByMaterialMap.put(materialName, panelArrayList);
            }
        }
        ArrayList<PanelsByMaterial> panelsByMaterialArrayList = new ArrayList<>();
        panelsByMaterialMap.forEach((k, v) -> {
            panelsByMaterialArrayList.add(new PanelsByMaterial(k, v));
        });
        return panelsByMaterialArrayList;
    }*/

}

