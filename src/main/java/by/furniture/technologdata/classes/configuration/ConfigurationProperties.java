package by.furniture.technologdata.classes.configuration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationProperties {
    private static ConfigurationProperties configurationProperties;

    private static Properties properties;
    private float edgeCoefficient;
    private float materialCoefficient;
    private String pathToOpenXML;
    private String pathToMaterialDB;
    private String pathToSaveXLS;

    private ConfigurationProperties() {
        loadPropertiesFromFile();
        this.edgeCoefficient = Float.parseFloat(properties.getProperty("edgeCoefficient", "1.15f"));
        this.materialCoefficient = Float.parseFloat(properties.getProperty("materialCoefficient", "1.3f"));
        this.pathToOpenXML = properties.getProperty("pathToOpenXML", "");
        this.pathToSaveXLS = properties.getProperty("pathToSaveXLS", "");
        this.pathToMaterialDB = properties.getProperty("pathToMaterialDB", "./materialDB.xls");
    }

    // SINGLETON
    public static ConfigurationProperties getConfigurationProperties() {
        if (configurationProperties == null) {
            configurationProperties = new ConfigurationProperties();
        } else {
            loadPropertiesFromFile();
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
        properties = new Properties();
        properties.setProperty("edgeCoefficient", "1.15f");
        properties.setProperty("materialCoefficient", "1.3f");
        properties.setProperty("pathToOpenXML", "");
        properties.setProperty("pathToMaterialDB", "./materialDB.xls");
        properties.setProperty("pathToSaveXLS", "");
        try {
            properties.store(new FileOutputStream("by/furniture/technologdata/properties.conf"), "Created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadPropertiesFromFile() {
        String pathToConfigInDebug = "properties.conf";
        String pathToConfigInRelease = "./properties.conf";
        FileInputStream configFile;
        boolean notFound = false;
        try {
            configFile = new FileInputStream(pathToConfigInDebug);
            properties.load(configFile);
        } catch (IOException e) {
            System.out.println("1");
            notFound = true;
        }
        try {
            if (notFound) {
                configFile = new FileInputStream(pathToConfigInRelease);
                properties.load(configFile);
                notFound = false;
            }
        } catch (IOException e) {
            System.out.println("creating config");
            createConfigurationFile();
        }
    }
}
