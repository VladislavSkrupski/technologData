package by.furniture.technologdata.classes.configuration;

public class ConfigurationProperties {
    private float edgeCoefficient;
    private float materialCoefficient;

    private String pathToMaterialDB;
    private String pathToSaveXLS;

    public ConfigurationProperties() {
        this.edgeCoefficient = 1.15f;
        this.materialCoefficient = 1.3f;
        this.pathToSaveXLS = "";
        this.pathToMaterialDB = "";
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
}
