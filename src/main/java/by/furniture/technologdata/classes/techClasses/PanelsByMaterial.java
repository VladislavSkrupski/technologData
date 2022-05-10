package by.furniture.technologdata.classes.techClasses;

import by.furniture.technologdata.classes.Panel;

import java.util.ArrayList;
import java.util.HashMap;

public class PanelsByMaterial {
    private String code;
    private final String nomination;
    private Integer amountOfPanels;
    private HashMap<String, Float> edgesWithLengthMap;

    private boolean isSelected = false;
    private boolean isFront = false;
    private boolean isSkinali = false;
    private boolean isWorktop = false;
    private boolean isDVP = false;
    private boolean isTemplate = false;
    private boolean isTextureSelection = false;

    private final ArrayList<Panel> panels;

    public PanelsByMaterial(String nomination, ArrayList<Panel> panels) {
        this.nomination = nomination;
        this.panels = panels;
        setAmountOfPanels();
        setEdgesWithLengthMap();

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNomination() {
        return nomination;
    }

    public ArrayList<Panel> getPanels() {
        return panels;
    }

    public Integer getAmountOfPanels() {
        return amountOfPanels;
    }

    public HashMap<String, Float> getEdgesWithLengthMap() {
        return edgesWithLengthMap;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isFront() {
        return isFront;
    }

    public void setFront(boolean front) {
        isFront = front;
    }

    public boolean isSkinali() {
        return isSkinali;
    }

    public void setSkinali(boolean skinali) {
        isSkinali = skinali;
    }

    public boolean isWorktop() {
        return isWorktop;
    }

    public void setWorktop(boolean worktop) {
        isWorktop = worktop;
    }

    public boolean isDVP() {
        return isDVP;
    }

    public void setDVP(boolean DVP) {
        isDVP = DVP;
    }

    public boolean isTemplate() {
        return isTemplate;
    }

    public void setTemplate(boolean template) {
        isTemplate = template;
    }

    public boolean isTextureSelection() {
        return isTextureSelection;
    }

    public void setTextureSelection(boolean textureSelection) {
        isTextureSelection = textureSelection;
    }

    private void setAmountOfPanels() {
        int counter = 0;
        if (this.panels != null) for (Panel p : this.panels) counter += p.getAmount();
        this.amountOfPanels = counter;
    }

    private void setEdgesWithLengthMap() {
        this.edgesWithLengthMap = new HashMap<>();
        if (this.panels != null) {
            for (Panel panel : panels) {
                for (int a = 0; a < panel.getEdgeLists().size(); a++) {
                    if (panel.getEdgeLists().get(a).size() > 0) {
                        panel.getEdgeLists().get(a).forEach(edge -> {
                            if (edgesWithLengthMap.containsKey(edge.getNomination())) {
                                edgesWithLengthMap.replace(edge.getNomination(), (edgesWithLengthMap.get(edge.getNomination()) + edge.getLength() * panel.getAmount()));
                            } else {
                                edgesWithLengthMap.put(edge.getNomination(), edge.getLength() * panel.getAmount());
                            }
                        });
                    }
                }
            }
        }
    }
}
