package by.furniture.technologdata.classes.configuration;

import javafx.scene.control.Alert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationProperties {
    private static ConfigurationProperties configurationProperties;

    private static final Properties properties = new Properties();
    private float edgeCoefficient;
    private float materialCoefficient;
    private String pathToOpenXML;
    private String pathToMaterialDB;
    private String pathToSaveXLS;

    private ConfigurationProperties() {
        loadPropertiesFromFile();
        this.edgeCoefficient = Float.parseFloat(properties.getProperty("edgeCoefficient"));
        this.materialCoefficient = Float.parseFloat(properties.getProperty("materialCoefficient"));
        this.pathToOpenXML = properties.getProperty("pathToOpenXML");
        this.pathToSaveXLS = properties.getProperty("pathToSaveXLS");
        this.pathToMaterialDB = properties.getProperty("pathToMaterialDB");
    }

    // SINGLETON
    public static ConfigurationProperties getConfigurationProperties() {
        if (configurationProperties == null) {
            configurationProperties = new ConfigurationProperties();
        }
        return configurationProperties;
    }

    public float getEdgeCoefficient() {
        return edgeCoefficient;
    }

    public void setEdgeCoefficient(float edgeCoefficient) {
        this.edgeCoefficient = edgeCoefficient;
    }

    public float getMaterialCoefficient() {
        return materialCoefficient;
    }

    public void setMaterialCoefficient(float materialCoefficient) {
        this.materialCoefficient = materialCoefficient;
    }

    public String getPathToSaveXLS() {
        return pathToSaveXLS;
    }

    public void setPathToSaveXLS(String pathToSaveXLS) {
        this.pathToSaveXLS = pathToSaveXLS;
    }

    public String getPathToMaterialDB() {
        return pathToMaterialDB;
    }

    public void setPathToMaterialDB(String pathToMaterialDB) {
        this.pathToMaterialDB = pathToMaterialDB;
    }

    public String getPathToOpenXML() {
        return pathToOpenXML;
    }

    public void setPathToOpenXML(String pathToOpenXML) {
        this.pathToOpenXML = pathToOpenXML;
    }

    private static void createConfigurationFile() {
        properties.setProperty("edgeCoefficient", "1.15");
        properties.setProperty("materialCoefficient", "1.3");
        properties.setProperty("pathToOpenXML", "");
        properties.setProperty("pathToMaterialDB", "./materialDB.xls");
        properties.setProperty("pathToSaveXLS", "");
        String pathToConfig = System.getProperty("user.dir") + "\\properties.conf";
        try {
            properties.store(new FileOutputStream(pathToConfig), "Created");
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Не удалось создать файл конфигурации properties.conf");
            alert.showAndWait();
        }
    }

    private static void loadPropertiesFromFile() {
        String pathToConfig = System.getProperty("user.dir") + "\\properties.conf";
        try {
            FileInputStream configFile = new FileInputStream(pathToConfig);
            properties.load(configFile);
        } catch (IOException e) {
            createConfigurationFile();
        }
    }
}
