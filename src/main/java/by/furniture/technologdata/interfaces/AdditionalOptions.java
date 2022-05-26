package by.furniture.technologdata.interfaces;

import javafx.scene.control.TextField;

public interface AdditionalOptions {
    static void checkFloatInput(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("")) newValue = "0";
            if (!newValue.matches("^\\d+\\.?\\d*$")) {
                if (!oldValue.equals("")) {
                    textField.setText(oldValue);
                } else {
                    textField.setText("0");
                }
            } else {
                textField.setText(newValue);
            }
        });
    }

    static void checkIntegerInput(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("")) newValue = "0";
            if (!newValue.matches("\\d+")) {
                if (!oldValue.equals("")) {
                    textField.setText(oldValue);
                } else {
                    textField.setText("0");
                }
            } else {
                textField.setText(newValue);
            }
        });
    }
}
