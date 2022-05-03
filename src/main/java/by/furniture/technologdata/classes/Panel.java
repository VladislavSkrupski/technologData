package by.furniture.technologdata.classes;

import java.util.ArrayList;

public class Panel {
    private String objectType;
    private String nomination;
    private String code;
    private String codeOfDetail;
    private ArrayList<AdditionalMaterial> additionalMaterials = new ArrayList<>();
    private Float length;
    private Float width;
    private Float partLengthWithoutEdging;
    private Float partWidthWithoutEdging;
    private Float finishedPartLength;
    private Float finishedPartWidth;
    private Float overallThickness;
    private Integer amount;
    private String position;
    private String designation;
    private Boolean rectangular;
    private String frontSide;
    private String side;
    private String orientationOfTexture;
    private ArrayList<ArrayList<Edge>> edgeLists;
    private ArrayList<Hole> holes = new ArrayList<>();
    private String panelType;
    private MainMaterial mainMaterial;
    private ArrayList<Groove> grooves = new ArrayList<>();
    private ArrayList<ArrayList<FacingSurface>> facingSurfaces;
    private ArrayList<ArrayList<OrderOfFacingSurface>> orderOfFacingSurfaces;
    private ArrayList<PieceworkOperation> pieceworkOperations = new ArrayList<>();
    private ArrayList<RelatedMaterial> relatedMaterials = new ArrayList<>();
    private ArrayList<CustomProperty> customProperties = new ArrayList<>();

    public Panel() {
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getNomination() {
        return nomination;
    }

    public void setNomination(String nomination) {
        this.nomination = nomination;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeOfDetail() {
        return codeOfDetail;
    }

    public void setCodeOfDetail(String codeOfDetail) {
        this.codeOfDetail = codeOfDetail;
    }

    public ArrayList<AdditionalMaterial> getAdditionalMaterials() {
        return additionalMaterials;
    }

    public void setAdditionalMaterials(ArrayList<AdditionalMaterial> additionalMaterials) {
        this.additionalMaterials = additionalMaterials;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getPartLengthWithoutEdging() {
        return partLengthWithoutEdging;
    }

    public void setPartLengthWithoutEdging(Float partLengthWithoutEdging) {
        this.partLengthWithoutEdging = partLengthWithoutEdging;
    }

    public Float getPartWidthWithoutEdging() {
        return partWidthWithoutEdging;
    }

    public void setPartWidthWithoutEdging(Float partWidthWithoutEdging) {
        this.partWidthWithoutEdging = partWidthWithoutEdging;
    }

    public Float getFinishedPartLength() {
        return finishedPartLength;
    }

    public void setFinishedPartLength(Float finishedPartLength) {
        this.finishedPartLength = finishedPartLength;
    }

    public Float getFinishedPartWidth() {
        return finishedPartWidth;
    }

    public void setFinishedPartWidth(Float finishedPartWidth) {
        this.finishedPartWidth = finishedPartWidth;
    }

    public Float getOverallThickness() {
        return overallThickness;
    }

    public void setOverallThickness(Float overallThickness) {
        this.overallThickness = overallThickness;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Boolean getRectangular() {
        return rectangular;
    }

    public void setRectangular(Boolean rectangular) {
        this.rectangular = rectangular;
    }

    public String getFrontSide() {
        return frontSide;
    }

    public void setFrontSide(String frontSide) {
        this.frontSide = frontSide;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getOrientationOfTexture() {
        return orientationOfTexture;
    }

    public void setOrientationOfTexture(String orientationOfTexture) {
        this.orientationOfTexture = orientationOfTexture;
    }

    public ArrayList<ArrayList<Edge>> getEdgeLists() {
        return edgeLists;
    }

    public void setEdgeLists(ArrayList<ArrayList<Edge>> edgeLists) {
        this.edgeLists = edgeLists;
    }

    public ArrayList<Hole> getHoles() {
        return holes;
    }

    public void setHoles(ArrayList<Hole> holes) {
        this.holes = holes;
    }

    public String getPanelType() {
        return panelType;
    }

    public void setPanelType(String panelType) {
        this.panelType = panelType;
    }

    public MainMaterial getMainMaterial() {
        return mainMaterial;
    }

    public void setMainMaterial(MainMaterial mainMaterial) {
        this.mainMaterial = mainMaterial;
    }

    public ArrayList<Groove> getGrooves() {
        return grooves;
    }

    public void setGrooves(ArrayList<Groove> grooves) {
        this.grooves = grooves;
    }

    public ArrayList<ArrayList<FacingSurface>> getFacingSurfaces() {
        return facingSurfaces;
    }

    public void setFacingSurfaces(ArrayList<ArrayList<FacingSurface>> facingSurfaces) {
        this.facingSurfaces = facingSurfaces;
    }

    public ArrayList<ArrayList<OrderOfFacingSurface>> getOrderOfFacingSurfaces() {
        return orderOfFacingSurfaces;
    }

    public void setOrderOfFacingSurfaces(ArrayList<ArrayList<OrderOfFacingSurface>> orderOfFacingSurfaces) {
        this.orderOfFacingSurfaces = orderOfFacingSurfaces;
    }

    public ArrayList<PieceworkOperation> getPieceworkOperations() {
        return pieceworkOperations;
    }

    public void setPieceworkOperations(ArrayList<PieceworkOperation> pieceworkOperations) {
        this.pieceworkOperations = pieceworkOperations;
    }

    public ArrayList<RelatedMaterial> getRelatedMaterials() {
        return relatedMaterials;
    }

    public void setRelatedMaterials(ArrayList<RelatedMaterial> relatedMaterials) {
        this.relatedMaterials = relatedMaterials;
    }

    public ArrayList<CustomProperty> getCustomProperties() {
        return customProperties;
    }

    public void setCustomProperties(ArrayList<CustomProperty> customProperties) {
        this.customProperties = customProperties;
    }

    @Override
    public String toString() {
        return "Panel{" +
                "objectType='" + objectType + '\'' +
                ", nomination='" + nomination + '\'' +
                ", code='" + code + '\'' +
                ", codeOfDetail='" + codeOfDetail + '\'' +
                ", additionalMaterials=" + additionalMaterials +
                ", length=" + length +
                ", width=" + width +
                ", partLengthWithoutEdging=" + partLengthWithoutEdging +
                ", partWidthWithoutEdging=" + partWidthWithoutEdging +
                ", finishedPartLength=" + finishedPartLength +
                ", finishedPartWidth=" + finishedPartWidth +
                ", overallThickness=" + overallThickness +
                ", amount=" + amount +
                ", position='" + position + '\'' +
                ", designation='" + designation + '\'' +
                ", rectangular=" + rectangular +
                ", frontSide='" + frontSide + '\'' +
                ", side='" + side + '\'' +
                ", orientationOfTexture='" + orientationOfTexture + '\'' +
                ", edgeLists=" + edgeLists +
                ", holes=" + holes +
                ", panelType='" + panelType + '\'' +
                ", mainMaterial=" + mainMaterial +
                ", grooves=" + grooves +
                ", facingSurfaces=" + facingSurfaces +
                ", orderOfFacingSurfaces=" + orderOfFacingSurfaces +
                ", pieceworkOperations=" + pieceworkOperations +
                ", relatedMaterials=" + relatedMaterials +
                ", customProperties=" + customProperties +
                '}';
    }

    public Boolean isRectangular() {
        return rectangular;
    }
}
