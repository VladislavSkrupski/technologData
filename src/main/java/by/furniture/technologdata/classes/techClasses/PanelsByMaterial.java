package by.furniture.technologdata.classes.techClasses;

import by.furniture.technologdata.classes.Panel;

import java.util.ArrayList;
import java.util.HashMap;

public class PanelsByMaterial {
    private String code;
    private final String nomination;
    private Integer amountOfPanels;
    private HashMap<String, Float> edgesWithLengthMap;
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
