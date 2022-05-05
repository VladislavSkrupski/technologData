package by.furniture.technologdata.controllers;

import by.furniture.technologdata.ParseXML;
import by.furniture.technologdata.classes.*;
import by.furniture.technologdata.classes.techClasses.PanelsByMaterial;
import by.furniture.technologdata.interfaces.BazisXMLTags;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class MainFrameController implements BazisXMLTags {

    public static ArrayList<Panel> panels = new ArrayList<>();
    private final TreeSet<String> mainMaterials = new TreeSet<>();
    private final ArrayList<TechnologData> technologDataArrayList = new ArrayList<>();
    public static ArrayList<CheckBox> mainMaterialCheckBoxes = new ArrayList<>();
    private final Product product = new Product();
    @FXML
    private MenuItem openFile;
    @FXML
    private Button exportButton;
    @FXML
    private Button productionCard;
    @FXML
    private Button detailsButton;
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
    private TableView<TechnologData> technologTable;

    @FXML
    void onExitClick() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void onDetailsShow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFrameController.class.getResource("/by/furniture/technologdata/fxml/detailsFrame.fxml"));
            VBox pane = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Подетальный список");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(ParseXML.getMainStage());
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            DetailsFrameController controller = loader.getController();
            controller.setDetailsStage(stage);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onProductionCardClick() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFrameController.class.getResource("/by/furniture/technologdata/fxml/order-production-card.fxml"));
            AnchorPane pane = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Производственная карта заказа");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(ParseXML.getMainStage());
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            OrderProductionCardController controller = loader.getController();
            controller.setOrderProductionCardStage(stage);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                arrayNodes = ParseXML.getTagArray(root, new ArrayList<>());
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
                    panels.add(ParseXML.NodeToPanel(panelNode));
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
            setTable(technologDataArrayList);
            exportButton.setDisable(false);
            detailsButton.setDisable(false);
        }
    }

    @FXML
    void onExportToXLS() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Файл EXCEL", "*.xls"));
        File file = fileChooser.showSaveDialog(exportButton.getParent().getScene().getWindow());

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet(product.getOrder().equals("") ? "лист 1" : product.getOrder());
        HSSFRow productOrderRow = hssfSheet.createRow(0);
        productOrderRow.createCell(0).setCellValue("Заказ:");
        productOrderRow.createCell(1).setCellValue(product.getOrder());
        HSSFRow productNominationRow = hssfSheet.createRow(1);
        productNominationRow.createCell(0).setCellValue("Наименование:");
        productNominationRow.createCell(1).setCellValue(product.getNomination());
        HSSFRow productArticleRow = hssfSheet.createRow(2);
        productArticleRow.createCell(0).setCellValue("Артикул:");
        productArticleRow.createCell(1).setCellValue(product.getArticle());

        HSSFRow titleRow = hssfSheet.createRow(4);
        for (int i = 0; i < technologTable.getColumns().size(); i++) {
            titleRow.createCell(i).setCellValue(technologTable.getColumns().get(i).getText());
        }
        for (int r = 0; r < technologTable.getItems().size(); r++) {
            HSSFRow hssfRow = hssfSheet.createRow(5 + r);
            for (int c = 0; c < technologTable.getColumns().size(); c++) {
                Object cellValue = technologTable.getColumns().get(c).getCellObservableValue(r).getValue();
                try {
                    if (cellValue != null && Float.parseFloat((cellValue.toString())) != 0.0f) {
                        hssfRow.createCell(c).setCellValue(Float.parseFloat(cellValue.toString()));
                    }
                } catch (NumberFormatException e) {
                    hssfRow.createCell(c).setCellValue(cellValue.toString());
                }
            }
        }
        if (file != null) {
            try (FileOutputStream outputStream = new FileOutputStream(file.getAbsolutePath())) {
                hssfWorkbook.write(outputStream);
                hssfWorkbook.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void setDataToArrayList(ArrayList<Panel> panelArrayList) {
        technologDataArrayList.clear();
        technologDataArrayList.add(overallAmountOfDetails(panelArrayList));
        technologDataArrayList.add(overallAmountOfROVERDetails(panelArrayList));
        technologDataArrayList.add(overallThinDetails(panelArrayList));
        technologDataArrayList.addAll(overallTollDetails(panelArrayList));
        technologDataArrayList.addAll(overallEdgeLength(panelArrayList));
        technologDataArrayList.addAll(overallSquareOfMaterials(panelArrayList));
        technologDataArrayList.addAll(overallGrooves(panelArrayList));
        technologDataArrayList.addAll(overallHoles(panelArrayList));
    }

    void resetTableData(ArrayList<Panel> panels) {
        setDataToArrayList(panelsBySelectedMaterial(panels, mainMaterialCheckBoxes));
        setTable(technologDataArrayList);
    }

    //----------------------Проверка чекбоксов------------------
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
    public TechnologData overallAmountOfDetails(ArrayList<Panel> panelArrayList) {
        int panelCount = 0;
        for (Panel panel : panelArrayList) {
            panelCount += panel.getAmount();
        }
        return new TechnologData("Общее количество деталей", String.valueOf(panelCount), "шт.");
    }

    public TechnologData overallAmountOfROVERDetails(ArrayList<Panel> panelArrayList) {
        int countROVERDetails = 0;
        for (Panel panel : panelArrayList) {
            boolean toROVER = false;
            if (panel.getCustomProperties() != null) {
                for (CustomProperty customProperty : panel.getCustomProperties()) {
                    if (customProperty.getName().equals("CutsROVER") && customProperty.getValue().equals("1")) {
                        toROVER = true;
                        break;
                    }
                }
            }
            if (!panel.isRectangular() || toROVER) {
                countROVERDetails += panel.getAmount();
            }
        }
        return new TechnologData("Детали, фрезеруемые на ROVER", String.valueOf(countROVERDetails), "шт.");
    }

    public TechnologData overallThinDetails(ArrayList<Panel> panelArrayList) {
        int thinDetailsCount = 0;
        for (Panel panel : panelArrayList) {
            if (panel.getLength() <= 67 || panel.getWidth() <= 67) {
                thinDetailsCount += panel.getAmount();
            }
        }
        return new TechnologData("Количество узких деталей (до 67мм)", String.valueOf(thinDetailsCount), "шт.");
    }

    public ArrayList<TechnologData> overallTollDetails(ArrayList<Panel> panelArrayList) {
        ArrayList<TechnologData> tollData = new ArrayList<>();
        int countToll = 0;
        float tollSquare = 0.0f;
        for (Panel panel : panelArrayList) {
            boolean countableToll = false;
            int countTollPlastic = 0;
            for (int a = 0; a < panel.getFacingSurfaces().size(); a++) {
                if (panel.getFacingSurfaces().get(a) != null) {
                    for (FacingSurface facingSurface : panel.getFacingSurfaces().get(a)) {
                        if (facingSurface.getThickness() > 2) {
                            countableToll = true;
                            countTollPlastic++;
                        }
                    }
                }
            }
            if (countableToll && ((countTollPlastic >= 1 && panel.getMainMaterial().getThickness() > 2) || countTollPlastic > 1)) {
                countToll += panel.getAmount();
                tollSquare += (panel.getLength() / 1000) * (panel.getWidth() / 1000) * panel.getAmount();
            }
        }
        tollData.add(new TechnologData("Количество сросток (кроме пластиков)", String.valueOf(countToll), "шт."));
        tollData.add(new TechnologData("Площадь сросток (кроме пластиков)", String.format("%.2f", tollSquare), "кв.м"));
        return tollData;
    }

    public ArrayList<TechnologData> overallEdgeLength(ArrayList<Panel> panelArrayList) {
        ArrayList<TechnologData> edgeData = new ArrayList<>();
        HashMap<String, Float> edgesMap = new HashMap<>();
        float fullLength = 0.0f;
        for (Panel panel : panelArrayList) {
            for (int a = 0; a < panel.getEdgeLists().size(); a++) {
                if (panel.getEdgeLists().get(a).size() > 0) {
                    panel.getEdgeLists().get(a).forEach(edge -> {
                        if (edgesMap.containsKey(edge.getNomination())) {
                            edgesMap.replace(edge.getNomination(), (edgesMap.get(edge.getNomination()) + edge.getLength() * panel.getAmount()));
                        } else {
                            edgesMap.put(edge.getNomination(), edge.getLength() * panel.getAmount());
                        }
                    });
                }
            }
        }
        edgesMap.forEach((s, aFloat) -> edgeData.add(new TechnologData(s, String.format("%.2f", aFloat / 1000), "м")));
        ArrayList<Float> values = new ArrayList<>(edgesMap.values());
        for (Float value : values) {
            fullLength += value;
        }
        edgeData.add(new TechnologData("Общий метраж кромки", String.format("%.2f", fullLength / 1000), "м"));
        return edgeData;
    }

    public ArrayList<TechnologData> overallSquareOfMaterials(ArrayList<Panel> panelArrayList) {
        ArrayList<TechnologData> materialsSquareData = new ArrayList<>();
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
            if (facingSurfaceSquareMap.get(str) != null) {
                materialsSquareData.add(new TechnologData("Площадь деталей " + str,
                        String.format("%.2f", ((mainMaterialSquareMap.get(str) == null ? 0 : mainMaterialSquareMap.get(str)) + facingSurfaceSquareMap.get(str))), "кв.м"));
            } else {
                materialsSquareData.add(new TechnologData("Площадь деталей " + str, String.format("%.2f", mainMaterialSquareMap.get(str)), "кв.м"));
            }
        }
        return materialsSquareData;
    }

    public ArrayList<TechnologData> overallGrooves(ArrayList<Panel> panelArrayList) {
        ArrayList<TechnologData> groovesData = new ArrayList<>();
        HashMap<String, Float> groovesMap = new HashMap<>();
        HashMap<String, Integer> groovesCountMap = new HashMap<>();
        for (Panel panel : panelArrayList) {
            if (panel.getGrooves() != null) {
                panel.getGrooves().forEach(groove -> {
                    if (groovesMap.containsKey(groove.getTitle())) {
                        groovesMap.replace(groove.getTitle(), (groovesMap.get(groove.getTitle()) + groove.getLength() * groove.getAmount() * panel.getAmount()));
                    } else {
                        groovesMap.put(groove.getTitle(), groove.getLength() * groove.getAmount() * panel.getAmount());
                    }
                });
                panel.getGrooves().forEach(groove -> {
                    if (groovesCountMap.containsKey(groove.getTitle())) {
                        groovesCountMap.replace(groove.getTitle(), (groovesCountMap.get(groove.getTitle()) + groove.getAmount() * panel.getAmount()));
                    } else {
                        groovesCountMap.put(groove.getTitle(), groove.getAmount() * panel.getAmount());
                    }
                });
            }
        }
        groovesCountMap.forEach((s, aInt) -> groovesData.add(new TechnologData("Паз " + s, String.valueOf(aInt), "шт.")));
        groovesMap.forEach((s, aFloat) -> groovesData.add(new TechnologData("Длина пазов " + s, String.format("%.2f", aFloat / 1000), "м")));
        return groovesData;
    }

    public ArrayList<TechnologData> overallHoles(ArrayList<Panel> panelArrayList) {
        ArrayList<TechnologData> holesData = new ArrayList<>();
        HashMap<String, Integer> holesMap = new HashMap<>();
        for (Panel panel : panelArrayList) {
            if (panel.getHoles() != null) {
                panel.getHoles().forEach(hole -> {
                    String holeName = (hole.getType() + " " + String.format("%.1f", hole.getDiameter()) + (hole.getDepth() != null ? ("x" + String.format("%.1f", hole.getDepth())) : ""));
                    if (holesMap.containsKey(holeName)) {
                        holesMap.replace(holeName, holesMap.get(holeName) + panel.getAmount());
                    } else {
                        holesMap.put(holeName, panel.getAmount());
                    }
                });
            }
        }
        holesMap.forEach((s, aInt) -> holesData.add(new TechnologData("Отверстие " + s, String.valueOf(aInt), "шт.")));
        return holesData;
    }

    //----------------------Вывод в таблицу---------------------
    void setTable(ArrayList<TechnologData> techDataList) {
        technologTable.getColumns().clear();
        technologTable.setItems(FXCollections.observableArrayList(techDataList));

        TableColumn<TechnologData, String> nameColumn = new TableColumn<>("Наименование");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        nameColumn.prefWidthProperty().setValue(400);
        nameColumn.setEditable(true);
        technologTable.getColumns().add(nameColumn);
        TableColumn<TechnologData, String> amountColumn = new TableColumn<>("Кол-во");
        amountColumn.editableProperty().setValue(true);
        amountColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        technologTable.getColumns().add(amountColumn);
        TableColumn<TechnologData, String> unitColumn = new TableColumn<>("Ед. изм.");
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("Unit"));
        unitColumn.setEditable(true);
        unitColumn.setStyle("-fx-alignment: CENTER;");
        technologTable.getColumns().add(unitColumn);
    }

    public ArrayList<PanelsByMaterial> getPanelsByMaterial(ArrayList<Panel> allPanels) {
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
    }

}

