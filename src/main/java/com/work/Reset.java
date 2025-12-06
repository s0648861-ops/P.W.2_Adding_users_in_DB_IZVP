package com.work;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;

public class Reset {

    @FXML
    private Button resetButton;

    @FXML
    void initialize() {
        resetButton.setOnAction(_ -> {
            resetButton.getScene().getWindow().hide();
            Controller.end("a", "a");
        });
    }



}
