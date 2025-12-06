package com.work;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.util.Random;

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

        int rand = new Random().nextInt(3);

        if (rand == 0) {
            text.setText("Welcome");
        }
        else if (rand == 1) {
            text.setText("Hi");
        }
        else {
            text.setText("What?");
        }

        exit.setOnAction(_ -> System.exit(0));

    }
}
