module by.furniture.technologdata {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.apache.poi.poi;
    requires java.xml;


    exports by.furniture.technologdata;
    opens by.furniture.technologdata to javafx.fxml;
    exports by.furniture.technologdata.classes;
    exports by.furniture.technologdata.interfaces;
    opens by.furniture.technologdata.interfaces to javafx.fxml;
    exports by.furniture.technologdata.controllers;
    opens by.furniture.technologdata.controllers to javafx.fxml;
}
