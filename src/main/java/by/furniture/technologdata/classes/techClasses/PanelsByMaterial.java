package by.furniture.technologdata.classes.techClasses;

import by.furniture.technologdata.classes.Panel;

import java.util.ArrayList;

public class PanelsByMaterial {
    private String code;
    private String nomination;
    private ArrayList<Panel> panels;

    public PanelsByMaterial(String nomination) {
        this.nomination = nomination;
    }

    public PanelsByMaterial(String nomination, ArrayList<Panel> panels) {
        this.nomination = nomination;
        this.panels = panels;
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

    public void setNomination(String nomination) {
        this.nomination = nomination;
    }

    public ArrayList<Panel> getPanels() {
        return panels;
    }

    public void setPanels(ArrayList<Panel> panels) {
        this.panels = panels;
    }

    @Override
    public String toString() {
        return "PanelsByMaterial{" +
                "code='" + code + '\'' +
                ", nomination='" + nomination + '\'' +
                ", panels=" + panels +
                '}';
    }
}
