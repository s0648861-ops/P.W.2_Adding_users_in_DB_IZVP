package com.work;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Reset {

    @FXML
    private Button resetButton;

    @FXML
    void initialize() {
        resetButton.setOnAction(_ -> {
            resetButton.getScene().getWindow().hide();
                System.out.println("reset button clicked");
        });
    }



}
