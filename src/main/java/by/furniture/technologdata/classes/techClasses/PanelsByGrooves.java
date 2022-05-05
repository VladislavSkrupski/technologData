package by.furniture.technologdata.classes.techClasses;

import by.furniture.technologdata.classes.Panel;

import java.util.ArrayList;

public class PanelsByGrooves {
    private String title;
    private String designation;
    private ArrayList<Panel> panels;

    public PanelsByGrooves(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public ArrayList<Panel> getPanels() {
        return panels;
    }

    public void setPanels(ArrayList<Panel> panels) {
        this.panels = panels;
    }

    @Override
    public String toString() {
        return "PanelsByGrooves{" +
                "title='" + title + '\'' +
                ", designation='" + designation + '\'' +
                ", panels=" + panels +
                '}';
    }
}
