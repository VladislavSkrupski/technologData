package by.furniture.technologdata;

import by.furniture.technologdata.classes.bazisXMLClasses.*;
import by.furniture.technologdata.classes.configuration.ConfigurationProperties;
import by.furniture.technologdata.classes.techClasses.MaterialDB;
import by.furniture.technologdata.interfaces.BazisXMLTags;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class StartPointLauncher extends Application implements BazisXMLTags {
    private static StartPointLauncher startPointLauncher;

    private static Stage mainStage;

    public static final HashMap<String, MaterialDB> materialDBList = new HashMap<>();

    public StartPointLauncher() {
        startPointLauncher = this;
    }

    public static StartPointLauncher getStartPointLauncher() {
        return startPointLauncher;
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/mainFrame.fxml")));
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.setTitle("черновая оценка заказа");
        mainStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo238x238.png"))));
        materialDBList.putAll(getMaterialDBList());
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void refreshMaterialDB() {
        materialDBList.putAll(getMaterialDBList());
    }

    /**
     * Метод для получения объекта панели из XML-спецификации
     *
     * @param node DOM-объект содержащий в себе описание панели
     * @return объект панели со всеми доступными в XML-спецификации свойствами
     */
    public static Panel NodeToPanel(Node node) {
        ArrayList<Node> childNodes = new ArrayList<>();
        NodeList nodeList = node.getChildNodes();
        Panel panel = new Panel();
        ArrayList<ArrayList<Edge>> edgeLists = new ArrayList<>(Arrays.asList(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        ArrayList<ArrayList<FacingSurface>> facingSurfacesList = new ArrayList<>(Arrays.asList(new ArrayList<>(), new ArrayList<>()));
        ArrayList<ArrayList<OrderOfFacingSurface>> orderOfFacingSurfaceList = new ArrayList<>(Arrays.asList(new ArrayList<>(), new ArrayList<>()));
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() != Node.TEXT_NODE) {
                childNodes.add(nodeList.item(i));
            }
        }
        for (Node child : childNodes) {
            switch (child.getNodeName()) {
                case OBJECT_TYPE: {
                    panel.setObjectType(child.getTextContent());
                    break;
                }
                case NOMINATION: {
                    panel.setNomination(child.getTextContent());
                    break;
                }
                case CODE: {
                    panel.setCode(child.getTextContent());
                    break;
                }
                case CODE_OF_DETAIL: {
                    panel.setCodeOfDetail(child.getTextContent());
                    break;
                }
                case ADDITIONAL_MATERIALS: {
                    if (child.hasChildNodes()) {
                        NodeList additionalMaterials = child.getChildNodes();
                        ArrayList<AdditionalMaterial> materialArrayList = new ArrayList<>();
                        for (int a = 0; a < additionalMaterials.getLength(); a++) {
                            if (additionalMaterials.item(a).getNodeType() != Node.TEXT_NODE && additionalMaterials.item(a).hasChildNodes()) {
                                NodeList additionalMaterial = additionalMaterials.item(a).getChildNodes();
                                AdditionalMaterial material = new AdditionalMaterial();
                                for (int b = 0; b < additionalMaterial.getLength(); b++) {
                                    if (additionalMaterial.item(b).getNodeType() != Node.TEXT_NODE) {
                                        switch (additionalMaterial.item(b).getNodeName()) {
                                            case ARTICLE: {
                                                material.setArticle(additionalMaterial.item(b).getTextContent());
                                                break;
                                            }
                                            case NOMINATION: {
                                                material.setNomination(additionalMaterial.item(b).getTextContent());
                                                break;
                                            }
                                            case QUANTITY: {
                                                material.setAmount(Float.parseFloat(additionalMaterial.item(b).getTextContent()));
                                                break;
                                            }
                                            case UNIT: {
                                                material.setUnit(additionalMaterial.item(b).getTextContent());
                                                break;
                                            }
                                        }
                                    }
                                }
                                materialArrayList.add(material);
                            }
                        }
                        panel.setAdditionalMaterials(materialArrayList);
                    } else {
                        panel.setAdditionalMaterials(null);
                    }
                    break;
                }
                case LENGTH: {
                    panel.setLength(Float.parseFloat(child.getTextContent()));
                    break;
                }
                case WIDTH: {
                    panel.setWidth(Float.parseFloat(child.getTextContent()));
                    break;
                }
                case PART_LENGTH_WITHOUT_EDGING: {
                    panel.setPartLengthWithoutEdging(Float.parseFloat(child.getTextContent()));
                    break;
                }
                case PART_WIDTH_WITHOUT_EDGING: {
                    panel.setPartWidthWithoutEdging(Float.parseFloat(child.getTextContent()));
                    break;
                }
                case FINISHED_PART_LENGTH: {
                    panel.setFinishedPartLength(Float.parseFloat(child.getTextContent()));
                    break;
                }
                case FINISHED_PART_WIDTH: {
                    panel.setFinishedPartWidth(Float.parseFloat(child.getTextContent()));
                    break;
                }
                case OVERALL_THICKNESS: {
                    panel.setOverallThickness(Float.parseFloat(child.getTextContent()));
                    break;
                }
                case QUANTITY: {
                    panel.setAmount(Integer.parseInt(child.getTextContent()));
                    break;
                }
                case POSITION: {
                    panel.setPosition(child.getTextContent());
                    break;
                }
                case DESIGNATION: {
                    panel.setDesignation(child.getTextContent());
                    break;
                }
                case RECTANGULAR: {
                    panel.setRectangular(child.getTextContent().equals("Y"));
                    break;
                }
                case FRONT_SIDE: {
                    panel.setFrontSide(child.getTextContent());
                    break;
                }
                case SIDE: {
                    panel.setSide(child.getTextContent());
                    break;
                }
                case ORIENTATION_OF_TEXTURE: {
                    panel.setOrientationOfTexture(child.getTextContent());
                    break;
                }
                case EDGE_LIST_1: {
                    if (child.hasChildNodes()) {
                        NodeList edgesNodes = child.getChildNodes();
                        ArrayList<Edge> edges = new ArrayList<>();
                        for (int a = 0; a < edgesNodes.getLength(); a++) {
                            if (edgesNodes.item(a).getNodeType() != Node.TEXT_NODE && edgesNodes.item(a).hasChildNodes()) {
                                NodeList edgesNode = edgesNodes.item(a).getChildNodes();
                                Edge edge = new Edge();
                                for (int b = 0; b < edgesNode.getLength(); b++) {
                                    if (edgesNode.item(b).getNodeType() != Node.TEXT_NODE) {
                                        switch (edgesNode.item(b).getNodeName()) {
                                            case NOMINATION: {
                                                edge.setNomination(edgesNode.item(b).getTextContent());
                                                break;
                                            }
                                            case CODE: {
                                                edge.setCode(edgesNode.item(b).getTextContent());
                                                break;
                                            }
                                            case LENGTH: {
                                                edge.setLength(Float.parseFloat(edgesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case WIDTH: {
                                                edge.setWidth(Float.parseFloat(edgesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case THICKNESS: {
                                                edge.setThickness(Float.parseFloat(edgesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case DESIGNATION: {
                                                edge.setDesignation(edgesNode.item(b).getTextContent());
                                                break;
                                            }
                                        }
                                    }
                                }
                                edges.add(edge);
                            }
                        }
                        edgeLists.get(0).addAll(edges);
                    } else {
                        edgeLists.get(0).clear();
                    }
                    break;
                }
                case EDGE_LIST_2: {
                    if (child.hasChildNodes()) {
                        NodeList edgesNodes = child.getChildNodes();
                        ArrayList<Edge> edges = new ArrayList<>();
                        for (int a = 0; a < edgesNodes.getLength(); a++) {
                            if (edgesNodes.item(a).getNodeType() != Node.TEXT_NODE && edgesNodes.item(a).hasChildNodes()) {
                                NodeList edgesNode = edgesNodes.item(a).getChildNodes();
                                Edge edge = new Edge();
                                for (int b = 0; b < edgesNode.getLength(); b++) {
                                    if (edgesNode.item(b).getNodeType() != Node.TEXT_NODE) {
                                        switch (edgesNode.item(b).getNodeName()) {
                                            case NOMINATION: {
                                                edge.setNomination(edgesNode.item(b).getTextContent());
                                                break;
                                            }
                                            case CODE: {
                                                edge.setCode(edgesNode.item(b).getTextContent());
                                                break;
                                            }
                                            case LENGTH: {
                                                edge.setLength(Float.parseFloat(edgesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case WIDTH: {
                                                edge.setWidth(Float.parseFloat(edgesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case THICKNESS: {
                                                edge.setThickness(Float.parseFloat(edgesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case DESIGNATION: {
                                                edge.setDesignation(edgesNode.item(b).getTextContent());
                                                break;
                                            }
                                        }
                                    }
                                }
                                edges.add(edge);
                            }
                        }
                        edgeLists.get(1).addAll(edges);
                    } else {
                        edgeLists.get(1).clear();
                    }
                    break;
                }
                case EDGE_LIST_3: {
                    if (child.hasChildNodes()) {
                        NodeList edgesNodes = child.getChildNodes();
                        ArrayList<Edge> edges = new ArrayList<>();
                        for (int a = 0; a < edgesNodes.getLength(); a++) {
                            if (edgesNodes.item(a).getNodeType() != Node.TEXT_NODE && edgesNodes.item(a).hasChildNodes()) {
                                NodeList edgesNode = edgesNodes.item(a).getChildNodes();
                                Edge edge = new Edge();
                                for (int b = 0; b < edgesNode.getLength(); b++) {
                                    if (edgesNode.item(b).getNodeType() != Node.TEXT_NODE) {
                                        switch (edgesNode.item(b).getNodeName()) {
                                            case NOMINATION: {
                                                edge.setNomination(edgesNode.item(b).getTextContent());
                                                break;
                                            }
                                            case CODE: {
                                                edge.setCode(edgesNode.item(b).getTextContent());
                                                break;
                                            }
                                            case LENGTH: {
                                                edge.setLength(Float.parseFloat(edgesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case WIDTH: {
                                                edge.setWidth(Float.parseFloat(edgesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case THICKNESS: {
                                                edge.setThickness(Float.parseFloat(edgesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case DESIGNATION: {
                                                edge.setDesignation(edgesNode.item(b).getTextContent());
                                                break;
                                            }
                                        }
                                    }
                                }
                                edges.add(edge);
                            }
                        }
                        edgeLists.get(2).addAll(edges);
                    } else {
                        edgeLists.get(2).clear();
                    }
                    break;
                }
                case EDGE_LIST_4: {
                    if (child.hasChildNodes()) {
                        NodeList edgesNodes = child.getChildNodes();
                        ArrayList<Edge> edges = new ArrayList<>();
                        for (int a = 0; a < edgesNodes.getLength(); a++) {
                            if (edgesNodes.item(a).getNodeType() != Node.TEXT_NODE && edgesNodes.item(a).hasChildNodes()) {
                                NodeList edgesNode = edgesNodes.item(a).getChildNodes();
                                Edge edge = new Edge();
                                for (int b = 0; b < edgesNode.getLength(); b++) {
                                    if (edgesNode.item(b).getNodeType() != Node.TEXT_NODE) {
                                        switch (edgesNode.item(b).getNodeName()) {
                                            case NOMINATION: {
                                                edge.setNomination(edgesNode.item(b).getTextContent());
                                                break;
                                            }
                                            case CODE: {
                                                edge.setCode(edgesNode.item(b).getTextContent());
                                                break;
                                            }
                                            case LENGTH: {
                                                edge.setLength(Float.parseFloat(edgesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case WIDTH: {
                                                edge.setWidth(Float.parseFloat(edgesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case THICKNESS: {
                                                edge.setThickness(Float.parseFloat(edgesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case DESIGNATION: {
                                                edge.setDesignation(edgesNode.item(b).getTextContent());
                                                break;
                                            }
                                        }
                                    }
                                }
                                edges.add(edge);
                            }
                        }
                        edgeLists.get(3).addAll(edges);
                    } else {
                        edgeLists.get(3).clear();
                    }
                    break;
                }
                case LIST_OF_EDGES_WATCH_DRAWING: {
                    if (child.hasChildNodes()) {
                        NodeList edgesNodes = child.getChildNodes();
                        ArrayList<Edge> edges = new ArrayList<>();
                        for (int a = 0; a < edgesNodes.getLength(); a++) {
                            if (edgesNodes.item(a).getNodeType() != Node.TEXT_NODE && edgesNodes.item(a).hasChildNodes()) {
                                NodeList edgesNode = edgesNodes.item(a).getChildNodes();
                                Edge edge = new Edge();
                                for (int b = 0; b < edgesNode.getLength(); b++) {
                                    if (edgesNode.item(b).getNodeType() != Node.TEXT_NODE) {
                                        switch (edgesNode.item(b).getNodeName()) {
                                            case NOMINATION: {
                                                edge.setNomination(edgesNode.item(b).getTextContent());
                                                break;
                                            }
                                            case CODE: {
                                                edge.setCode(edgesNode.item(b).getTextContent());
                                                break;
                                            }
                                            case LENGTH: {
                                                edge.setLength(Float.parseFloat(edgesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case WIDTH: {
                                                edge.setWidth(Float.parseFloat(edgesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case THICKNESS: {
                                                edge.setThickness(Float.parseFloat(edgesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case DESIGNATION: {
                                                edge.setDesignation(edgesNode.item(b).getTextContent());
                                                break;
                                            }
                                        }
                                    }
                                }
                                edges.add(edge);
                            }
                        }
                        edgeLists.get(4).addAll(edges);
                    } else {
                        edgeLists.get(4).clear();
                    }
                    break;
                }
                case HOLES: {
                    if (child.hasChildNodes()) {
                        NodeList holesNodes = child.getChildNodes();
                        ArrayList<Hole> holes = new ArrayList<>();
                        for (int a = 0; a < holesNodes.getLength(); a++) {
                            if (holesNodes.item(a).getNodeType() != Node.TEXT_NODE && holesNodes.item(a).hasChildNodes()) {
                                NodeList holesNode = holesNodes.item(a).getChildNodes();
                                Hole hole = new Hole();
                                for (int b = 0; b < holesNode.getLength(); b++) {
                                    if (holesNode.item(b).getNodeType() != Node.TEXT_NODE) {
                                        switch (holesNode.item(b).getNodeName()) {
                                            case POSITION_X: {
                                                hole.setPositionX(Float.parseFloat(holesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case POSITION_Y: {
                                                hole.setPositionY(Float.parseFloat(holesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case POSITION_Z: {
                                                hole.setPositionZ(Float.parseFloat(holesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case DIAMETER: {
                                                hole.setDiameter(Float.parseFloat(holesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case TYPE: {
                                                hole.setType(holesNode.item(b).getTextContent());
                                                break;
                                            }
                                            case DEPTH: {
                                                hole.setDepth(Float.parseFloat(holesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case DIRECTION_X: {
                                                hole.setDirectionX(Float.parseFloat(holesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case DIRECTION_Y: {
                                                hole.setDirectionY(Float.parseFloat(holesNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case DIRECTION_Z: {
                                                hole.setDirectionZ(Float.parseFloat(holesNode.item(b).getTextContent()));
                                                break;
                                            }
                                        }
                                    }
                                }
                                holes.add(hole);
                            }
                        }
                        panel.setHoles(holes);
                    } else {
                        panel.setHoles(null);
                    }
                    break;
                }
                case PANEL_TYPE: {
                    panel.setPanelType(child.getTextContent());
                    break;
                }
                case MAIN_MATERIAL: {
                    if (child.hasChildNodes()) {
                        NodeList mainMaterialNodes = child.getChildNodes();
                        MainMaterial mainMaterial = new MainMaterial();
                        for (int a = 0; a < mainMaterialNodes.getLength(); a++) {
                            if (mainMaterialNodes.item(a).getNodeType() != Node.TEXT_NODE) {
                                switch (mainMaterialNodes.item(a).getNodeName()) {
                                    case ID: {
                                        mainMaterial.setId(mainMaterialNodes.item(a).getTextContent());
                                        break;
                                    }
                                    case NOMINATION: {
                                        mainMaterial.setNomination(mainMaterialNodes.item(a).getTextContent());
                                        break;
                                    }
                                    case CODE: {
                                        mainMaterial.setCode(mainMaterialNodes.item(a).getTextContent());
                                        break;
                                    }
                                    case TYPE: {
                                        mainMaterial.setType(mainMaterialNodes.item(a).getTextContent());
                                        break;
                                    }
                                    case QUANTITY: {
                                        mainMaterial.setAmount(Float.parseFloat(mainMaterialNodes.item(a).getTextContent()));
                                        break;
                                    }
                                    case QUANTITY_FROM_MODEL: {
                                        mainMaterial.setAmountFromModel(Float.parseFloat(mainMaterialNodes.item(a).getTextContent()));
                                        break;
                                    }
                                    case QUANTITY_BY_ASSOCIATE: {
                                        mainMaterial.setAmountByAssociate(Float.parseFloat(mainMaterialNodes.item(a).getTextContent()));
                                        break;
                                    }
                                    case QUANTITY_BEFORE_ROUNDING: {
                                        mainMaterial.setAmountBeforeRounding(Float.parseFloat(mainMaterialNodes.item(a).getTextContent()));
                                        break;
                                    }
                                    case UNIT: {
                                        mainMaterial.setUnit(mainMaterialNodes.item(a).getTextContent());
                                        break;
                                    }
                                    case COST: {
                                        mainMaterial.setCost(Float.parseFloat(mainMaterialNodes.item(a).getTextContent()));
                                        break;
                                    }
                                    case PRICE: {
                                        mainMaterial.setPrice(Float.parseFloat(mainMaterialNodes.item(a).getTextContent()));
                                        break;
                                    }
                                    case COEFFICIENT: {
                                        mainMaterial.setCoefficient(Float.parseFloat(mainMaterialNodes.item(a).getTextContent()));
                                        break;
                                    }
                                    case THICKNESS: {
                                        mainMaterial.setThickness(Float.parseFloat(mainMaterialNodes.item(a).getTextContent()));
                                        break;
                                    }
                                    case ROUNDING_METHOD: {
                                        mainMaterial.setRoundingMethod(mainMaterialNodes.item(a).getTextContent());
                                        break;
                                    }
                                    case ROUNDING_AMOUNT: {
                                        mainMaterial.setRoundingAmount(Float.parseFloat(mainMaterialNodes.item(a).getTextContent()));
                                        break;
                                    }
                                    case M_CLASS: {
                                        mainMaterial.setmClass(mainMaterialNodes.item(a).getTextContent());
                                        break;
                                    }
                                    case SYNC_ID: {
                                        mainMaterial.setSyncId(mainMaterialNodes.item(a).getTextContent());
                                        break;
                                    }
                                    case NOTE: {
                                        mainMaterial.setNote(mainMaterialNodes.item(a).getTextContent());
                                        break;
                                    }
                                }
                            }
                        }
                        panel.setMainMaterial(mainMaterial);
                    } else {
                        panel.setMainMaterial(null);
                    }
                    break;
                }
                case GROOVE_LIST: {
                    if (child.hasChildNodes()) {
                        NodeList groovesNodes = child.getChildNodes();
                        ArrayList<Groove> grooves = new ArrayList<>();
                        for (int a = 0; a < groovesNodes.getLength(); a++) {
                            if (groovesNodes.item(a).getNodeType() != Node.TEXT_NODE && groovesNodes.item(a).hasChildNodes()) {
                                NodeList grooveNode = groovesNodes.item(a).getChildNodes();
                                Groove groove = new Groove();
                                for (int b = 0; b < grooveNode.getLength(); b++) {
                                    if (grooveNode.item(b).getNodeType() != Node.TEXT_NODE) {
                                        switch (grooveNode.item(b).getNodeName()) {
                                            case TYPE: {
                                                groove.setType(grooveNode.item(b).getTextContent());
                                                break;
                                            }
                                            case TITLE: {
                                                groove.setTitle(grooveNode.item(b).getTextContent());
                                                break;
                                            }
                                            case DESIGNATION: {
                                                groove.setDesignation(grooveNode.item(b).getTextContent());
                                                break;
                                            }
                                            case QUANTITY: {
                                                groove.setAmount(Integer.parseInt(grooveNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case LENGTH: {
                                                groove.setLength(Float.parseFloat(grooveNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case WIDTH: {
                                                groove.setWidth(Float.parseFloat(grooveNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case DEPTH: {
                                                groove.setDepth(Float.parseFloat(grooveNode.item(b).getTextContent()));
                                                break;
                                            }
                                        }
                                    }
                                }
                                grooves.add(groove);
                            }
                        }
                        panel.setGrooves(grooves);
                    } else {
                        panel.setGrooves(null);
                    }
                    break;
                }
                case FACING_SURFACE_1: {
                    if (child.hasChildNodes()) {
                        NodeList facingSurfaceNodes = child.getChildNodes();
                        ArrayList<FacingSurface> facingSurfaces = new ArrayList<>();
                        for (int a = 0; a < facingSurfaceNodes.getLength(); a++) {
                            if (facingSurfaceNodes.item(a).getNodeType() != Node.TEXT_NODE && facingSurfaceNodes.item(a).hasChildNodes()) {
                                NodeList facingSurfaceNode = facingSurfaceNodes.item(a).getChildNodes();
                                FacingSurface facingSurface = new FacingSurface();
                                for (int b = 0; b < facingSurfaceNode.getLength(); b++) {
                                    if (facingSurfaceNode.item(b).getNodeType() != Node.TEXT_NODE) {
                                        switch (facingSurfaceNode.item(b).getNodeName()) {
                                            case NOMINATION: {
                                                facingSurface.setNomination(facingSurfaceNode.item(b).getTextContent());
                                                break;
                                            }
                                            case CODE: {
                                                facingSurface.setCode(facingSurfaceNode.item(b).getTextContent());
                                                break;
                                            }
                                            case LENGTH: {
                                                facingSurface.setLength(Float.parseFloat(facingSurfaceNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case WIDTH: {
                                                facingSurface.setWidth(Float.parseFloat(facingSurfaceNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case PART_LENGTH_WITHOUT_EDGING: {
                                                facingSurface.setPartLengthWithoutEdging(Float.parseFloat(facingSurfaceNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case PART_WIDTH_WITHOUT_EDGING: {
                                                facingSurface.setPartWidthWithoutEdging(Float.parseFloat(facingSurfaceNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case FINISHED_PART_LENGTH: {
                                                facingSurface.setFinishedPartLength(Float.parseFloat(facingSurfaceNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case FINISHED_PART_WIDTH: {
                                                facingSurface.setFinishedPartWidth(Float.parseFloat(facingSurfaceNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case ORIENTATION_OF_TEXTURE: {
                                                facingSurface.setOrientationOfTexture(facingSurfaceNode.item(b).getTextContent());
                                                break;
                                            }
                                            case THICKNESS: {
                                                facingSurface.setThickness(Float.parseFloat(facingSurfaceNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case QUANTITY: {
                                                facingSurface.setAmount(Integer.parseInt(facingSurfaceNode.item(b).getTextContent()));
                                                break;
                                            }
                                        }
                                    }
                                }
                                facingSurfaces.add(facingSurface);
                            }
                        }
                        facingSurfacesList.get(0).addAll(facingSurfaces);
                    } else {
                        facingSurfacesList.get(0).clear();
                    }
                    break;
                }
                case FACING_SURFACE_2: {
                    if (child.hasChildNodes()) {
                        NodeList facingSurfaceNodes = child.getChildNodes();
                        ArrayList<FacingSurface> facingSurfaces = new ArrayList<>();
                        for (int a = 0; a < facingSurfaceNodes.getLength(); a++) {
                            if (facingSurfaceNodes.item(a).getNodeType() != Node.TEXT_NODE && facingSurfaceNodes.item(a).hasChildNodes()) {
                                NodeList facingSurfaceNode = facingSurfaceNodes.item(a).getChildNodes();
                                FacingSurface facingSurface = new FacingSurface();
                                for (int b = 0; b < facingSurfaceNode.getLength(); b++) {
                                    if (facingSurfaceNode.item(b).getNodeType() != Node.TEXT_NODE) {
                                        switch (facingSurfaceNode.item(b).getNodeName()) {
                                            case NOMINATION: {
                                                facingSurface.setNomination(facingSurfaceNode.item(b).getTextContent());
                                                break;
                                            }
                                            case CODE: {
                                                facingSurface.setCode(facingSurfaceNode.item(b).getTextContent());
                                                break;
                                            }
                                            case LENGTH: {
                                                facingSurface.setLength(Float.parseFloat(facingSurfaceNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case WIDTH: {
                                                facingSurface.setWidth(Float.parseFloat(facingSurfaceNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case PART_LENGTH_WITHOUT_EDGING: {
                                                facingSurface.setPartLengthWithoutEdging(Float.parseFloat(facingSurfaceNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case PART_WIDTH_WITHOUT_EDGING: {
                                                facingSurface.setPartWidthWithoutEdging(Float.parseFloat(facingSurfaceNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case FINISHED_PART_LENGTH: {
                                                facingSurface.setFinishedPartLength(Float.parseFloat(facingSurfaceNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case FINISHED_PART_WIDTH: {
                                                facingSurface.setFinishedPartWidth(Float.parseFloat(facingSurfaceNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case ORIENTATION_OF_TEXTURE: {
                                                facingSurface.setOrientationOfTexture(facingSurfaceNode.item(b).getTextContent());
                                                break;
                                            }
                                            case THICKNESS: {
                                                facingSurface.setThickness(Float.parseFloat(facingSurfaceNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case QUANTITY: {
                                                facingSurface.setAmount(Integer.parseInt(facingSurfaceNode.item(b).getTextContent()));
                                                break;
                                            }
                                        }
                                    }
                                }
                                facingSurfaces.add(facingSurface);
                            }
                        }
                        facingSurfacesList.get(1).addAll(facingSurfaces);
                    } else {
                        facingSurfacesList.get(1).clear();
                    }
                    break;
                }
                case ORDER_OF_FACING_SURFACE: {
                    if (child.hasChildNodes()) {
                        NodeList orderNodes = child.getChildNodes();
                        for (int a = 0; a < orderNodes.getLength(); a++) {
                            if (orderNodes.item(a).getNodeType() != Node.TEXT_NODE) {
                                switch (orderNodes.item(a).getNodeName()) {
                                    case ABOVE: {
                                        if (orderNodes.item(a).hasChildNodes()) {
                                            NodeList orderNode = orderNodes.item(a).getChildNodes();
                                            ArrayList<OrderOfFacingSurface> aboveOrderList = new ArrayList<>();
                                            for (int b = 0; b < orderNode.getLength(); b++) {
                                                if (orderNode.item(b).getNodeType() != Node.TEXT_NODE && orderNode.item(b).hasChildNodes()) {
                                                    NodeList orderMaterialNode = orderNode.item(b).getChildNodes();
                                                    OrderOfFacingSurface aboveOrderMaterial = new OrderOfFacingSurface();
                                                    for (int c = 0; c < orderMaterialNode.getLength(); c++) {
                                                        if (orderMaterialNode.item(c).getNodeType() != Node.TEXT_NODE) {
                                                            switch (orderMaterialNode.item(c).getNodeName()) {
                                                                case NOMINATION: {
                                                                    aboveOrderMaterial.setNomination(orderMaterialNode.item(c).getTextContent());
                                                                    break;
                                                                }
                                                                case CODE: {
                                                                    aboveOrderMaterial.setCode(orderMaterialNode.item(c).getTextContent());
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    aboveOrderList.add(aboveOrderMaterial);
                                                }
                                            }
                                            orderOfFacingSurfaceList.get(0).addAll(aboveOrderList);
                                        } else {
                                            orderOfFacingSurfaceList.get(0).clear();
                                        }
                                        break;
                                    }
                                    case BOTTOM: {
                                        if (orderNodes.item(a).hasChildNodes()) {
                                            NodeList orderNode = orderNodes.item(a).getChildNodes();
                                            ArrayList<OrderOfFacingSurface> bottomOrderList = new ArrayList<>();
                                            for (int b = 0; b < orderNode.getLength(); b++) {
                                                if (orderNode.item(b).getNodeType() != Node.TEXT_NODE && orderNode.item(b).hasChildNodes()) {
                                                    NodeList orderMaterialNode = orderNode.item(b).getChildNodes();
                                                    OrderOfFacingSurface bottomOrderMaterial = new OrderOfFacingSurface();
                                                    for (int c = 0; c < orderMaterialNode.getLength(); c++) {
                                                        if (orderMaterialNode.item(c).getNodeType() != Node.TEXT_NODE) {
                                                            switch (orderMaterialNode.item(c).getNodeName()) {
                                                                case NOMINATION: {
                                                                    bottomOrderMaterial.setNomination(orderMaterialNode.item(c).getTextContent());
                                                                    break;
                                                                }
                                                                case CODE: {
                                                                    bottomOrderMaterial.setCode(orderMaterialNode.item(c).getTextContent());
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    bottomOrderList.add(bottomOrderMaterial);
                                                }
                                            }
                                            orderOfFacingSurfaceList.get(1).addAll(bottomOrderList);
                                        } else {
                                            orderOfFacingSurfaceList.get(1).clear();
                                        }
                                        break;
                                    }
                                }

                            }
                        }
                        panel.setOrderOfFacingSurfaces(orderOfFacingSurfaceList);
                    } else {
                        panel.setOrderOfFacingSurfaces(null);
                    }
                    break;
                }
                case LIST_OF_OPERATIONS: {
                    if (child.hasChildNodes()) {
                        NodeList operationsNodes = child.getChildNodes();
                        ArrayList<PieceworkOperation> pieceworkOperations = new ArrayList<>();
                        for (int a = 0; a < operationsNodes.getLength(); a++) {
                            if (operationsNodes.item(a).getNodeType() != Node.TEXT_NODE && operationsNodes.item(a).hasChildNodes()) {
                                NodeList operationNode = operationsNodes.item(a).getChildNodes();
                                PieceworkOperation pieceworkOperation = new PieceworkOperation();
                                for (int b = 0; b < operationNode.getLength(); b++) {
                                    if (operationNode.item(b).getNodeType() != Node.TEXT_NODE) {
                                        switch (operationNode.item(b).getNodeName()) {
                                            case NOMINATION: {
                                                pieceworkOperation.setNomination(operationNode.item(b).getTextContent());
                                                break;
                                            }
                                            case CODE: {
                                                pieceworkOperation.setCode(operationNode.item(b).getTextContent());
                                                break;
                                            }
                                            case QUANTITY: {
                                                pieceworkOperation.setAmount(Float.parseFloat(operationNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case UNIT: {
                                                pieceworkOperation.setUnit(operationNode.item(b).getTextContent());
                                                break;
                                            }
                                            case COST: {
                                                pieceworkOperation.setCost(Float.parseFloat(operationNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case PRICE: {
                                                pieceworkOperation.setPrice(Float.parseFloat(operationNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case LABOR_INTENSITY: {
                                                pieceworkOperation.setLaborIntensity(Float.parseFloat(operationNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case ROUNDING_METHOD: {
                                                pieceworkOperation.setRoundingMethod(operationNode.item(b).getTextContent());
                                                break;
                                            }
                                            case ROUNDING_AMOUNT: {
                                                pieceworkOperation.setRoundingAmount(Float.parseFloat(operationNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case M_CLASS: {
                                                pieceworkOperation.setwClass(operationNode.item(b).getTextContent());
                                                break;
                                            }
                                            case SYNC_ID: {
                                                pieceworkOperation.setSyncId(operationNode.item(b).getTextContent());
                                                break;
                                            }
                                            case NOTE: {
                                                pieceworkOperation.setNote(operationNode.item(b).getTextContent());
                                                break;
                                            }
                                            case PRIORITY: {
                                                pieceworkOperation.setPriority(operationNode.item(b).getTextContent());
                                                break;
                                            }
                                        }
                                    }
                                }
                                pieceworkOperations.add(pieceworkOperation);
                            }
                        }
                        panel.setPieceworkOperations(pieceworkOperations);
                    } else {
                        panel.setPieceworkOperations(null);
                    }
                    break;
                }
                case RELATED_MATERIALS: {
                    if (child.hasChildNodes()) {
                        NodeList relatedMaterialNodes = child.getChildNodes();
                        ArrayList<RelatedMaterial> relatedMaterials = new ArrayList<>();
                        for (int a = 0; a < relatedMaterialNodes.getLength(); a++) {
                            if (relatedMaterialNodes.item(a).getNodeType() != Node.TEXT_NODE && relatedMaterialNodes.item(a).hasChildNodes()) {
                                NodeList relatedMaterialNode = relatedMaterialNodes.item(a).getChildNodes();
                                RelatedMaterial relatedMaterial = new RelatedMaterial();
                                for (int b = 0; b < relatedMaterialNode.getLength(); b++) {
                                    if (relatedMaterialNode.item(b).getNodeType() != Node.TEXT_NODE) {
                                        switch (relatedMaterialNode.item(b).getNodeName()) {
                                            case ID: {
                                                relatedMaterial.setId(relatedMaterialNode.item(b).getTextContent());
                                                break;
                                            }
                                            case NOMINATION: {
                                                relatedMaterial.setNomination(relatedMaterialNode.item(b).getTextContent());
                                                break;
                                            }
                                            case CODE: {
                                                relatedMaterial.setCode(relatedMaterialNode.item(b).getTextContent());
                                                break;
                                            }
                                            case TYPE: {
                                                relatedMaterial.setType(relatedMaterialNode.item(b).getTextContent());
                                                break;
                                            }
                                            case QUANTITY: {
                                                relatedMaterial.setAmount(Float.parseFloat(relatedMaterialNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case QUANTITY_FROM_MODEL: {
                                                relatedMaterial.setAmountFromModel(Float.parseFloat(relatedMaterialNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case QUANTITY_BY_ASSOCIATE: {
                                                relatedMaterial.setAmountByAssociate(Float.parseFloat(relatedMaterialNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case QUANTITY_BEFORE_ROUNDING: {
                                                relatedMaterial.setAmountBeforeRounding(Float.parseFloat(relatedMaterialNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case UNIT: {
                                                relatedMaterial.setUnit(relatedMaterialNode.item(b).getTextContent());
                                                break;
                                            }
                                            case COST: {
                                                relatedMaterial.setCost(Float.parseFloat(relatedMaterialNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case PRICE: {
                                                relatedMaterial.setPrice(Float.parseFloat(relatedMaterialNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case COEFFICIENT: {
                                                relatedMaterial.setCoefficient(Float.parseFloat(relatedMaterialNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case ROUNDING_METHOD: {
                                                relatedMaterial.setRoundingMethod(relatedMaterialNode.item(b).getTextContent());
                                                break;
                                            }
                                            case ROUNDING_AMOUNT: {
                                                relatedMaterial.setRoundingAmount(Float.parseFloat(relatedMaterialNode.item(b).getTextContent()));
                                                break;
                                            }
                                            case M_CLASS: {
                                                relatedMaterial.setsClass(relatedMaterialNode.item(b).getTextContent());
                                                break;
                                            }
                                            case SYNC_ID: {
                                                relatedMaterial.setSyncId(relatedMaterialNode.item(b).getTextContent());
                                                break;
                                            }
                                            case NOTE: {
                                                relatedMaterial.setNote(relatedMaterialNode.item(b).getTextContent());
                                                break;
                                            }
                                        }
                                    }
                                }
                                relatedMaterials.add(relatedMaterial);
                            }
                        }
                        panel.setRelatedMaterials(relatedMaterials);
                    } else {
                        panel.setRelatedMaterials(null);
                    }
                    break;
                }
                case CUSTOM_PROPERTIES: {
                    if (child.hasChildNodes()) {
                        NodeList customPropertiesNodes = child.getChildNodes();
                        ArrayList<CustomProperty> customProperties = new ArrayList<>();
                        for (int a = 0; a < customPropertiesNodes.getLength(); a++) {
                            if (customPropertiesNodes.item(a).getNodeType() != Node.TEXT_NODE && customPropertiesNodes.item(a).hasChildNodes()) {
                                NodeList customPropertyNode = customPropertiesNodes.item(a).getChildNodes();
                                CustomProperty customProperty = new CustomProperty();
                                for (int b = 0; b < customPropertyNode.getLength(); b++) {
                                    if (customPropertyNode.item(b).getNodeType() != Node.TEXT_NODE) {
                                        switch (customPropertyNode.item(b).getNodeName()) {
                                            case NAME: {
                                                customProperty.setName(customPropertyNode.item(b).getTextContent());
                                                break;
                                            }
                                            case VALUE: {
                                                customProperty.setValue(customPropertyNode.item(b).getTextContent());
                                                break;
                                            }
                                        }
                                    }
                                }
                                customProperties.add(customProperty);
                            }
                        }
                        panel.setCustomProperties(customProperties);
                    } else {
                        panel.setCustomProperties(null);
                    }
                    break;
                }
                default: {
                    System.out.println("Есть нераспознанные поля " + child.getNodeName());
                    break;
                }
            }
        }
        panel.setEdgeLists(edgeLists);
        panel.setFacingSurfaces(facingSurfacesList);
        return panel;
    }

    /**
     * Метод для преобразования дерева DOM-объектов в одномерный список
     *
     * @param node          принимает корневой DOM-объект, в дальнейшем получая наследника рекурсией
     * @param nodeArrayList принимает на старте лист DOM-объектов, дописывая его с каждой последующей итерацией
     * @return одномерный список всех дочерних DOM-объектов
     */
    public static ArrayList<Node> getTagArray(Node node, ArrayList<Node> nodeArrayList) {
        if (node.getNodeType() != Node.TEXT_NODE) {
            nodeArrayList.add(node);
            NodeList list = node.getChildNodes();
            if (list.getLength() > 0) {
                for (int i = 0; i < list.getLength(); i++) {
                    getTagArray(list.item(i), nodeArrayList);
                }
            }
        }
        return nodeArrayList;
    }

    /**
     * Загружает базу материалов из файла MaterialDB.xls
     *
     * @return возвращает HashMap объектов MaterialDB с ключами из наименования материала
     */
    private static HashMap<String, MaterialDB> getMaterialDBList() {
        HashMap<String, MaterialDB> materialDBS = new HashMap<>();
        FileInputStream fileInputStream = null;
        try {
            String path = ConfigurationProperties.getConfigurationProperties().getPathToMaterialDB();
            fileInputStream = new FileInputStream(path + "\\materialDB.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();//TODO search materialDB.xls or create new
        }
        try {
            assert fileInputStream != null;
            try (POIFSFileSystem materialDBFile = new POIFSFileSystem(fileInputStream)) {
                HSSFWorkbook materialDBBook = new HSSFWorkbook(materialDBFile);
                HSSFSheet materialDBSheet = materialDBBook.getSheetAt(0);
                for (int i = 1; i < materialDBSheet.getLastRowNum() + 1; i++) {
                    HSSFRow row = materialDBSheet.getRow(i);
                    HSSFCell articleCell = row.getCell(0);
                    HSSFCell nameCell = row.getCell(1);
                    HSSFCell listLengthCell = row.getCell(2);
                    HSSFCell listWidthCell = row.getCell(3);
                    HSSFCell listThicknessCell = row.getCell(4);
                    MaterialDB materialDB = new MaterialDB(
                            articleCell.getStringCellValue(),
                            nameCell.getStringCellValue(),
                            (float) listLengthCell.getNumericCellValue(),
                            (float) listWidthCell.getNumericCellValue(),
                            (float) listThicknessCell.getNumericCellValue()
                    );
                    if (materialDBS.containsKey(materialDB.getName())) {
                        materialDBS.get(materialDB.getName()).setListFormat(materialDB.getBoardFormatsMap());
                    } else {
                        materialDBS.put(materialDB.getName(), materialDB);
                    }
                    materialDBS.get(materialDB.getName()).setFormatChoiceBox();
                    materialDBS.get(materialDB.getName()).getFormatChoiceBox().setOnAction(actionEvent -> {
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        // }
        return materialDBS;
    }
    private static Panel setRealLengthAndWidth(Panel panel){//TODO set real length and width

        return panel;
    }
}