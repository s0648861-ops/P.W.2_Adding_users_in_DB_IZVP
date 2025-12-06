package com.work;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.w3c.dom.Text;

import java.io.File;

public class ending {

    @FXML
    private Button exit;

    @FXML
    private ImageView gif;

    @FXML
    private Label text;

    @FXML
    void initialize() {
        gif.setImage(new Image(new File("a.gif").toURI().toString()));

        exit.setOnAction(_ -> System.exit(0));

    }
}
