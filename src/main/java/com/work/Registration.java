package com.work;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Registration {

    @FXML
    private RadioButton Female;

    @FXML
    private RadioButton Male;

    @FXML
    private TextField country;

    @FXML
    private TextField fName;

    @FXML
    private TextField nickname;

    @FXML
    private TextField password;

    @FXML
    private Button registerButton;

    @FXML
    private TextField sName;

    ToggleGroup gender = new ToggleGroup();

    @FXML
    void initialize() {

        DBHandler db = new DBHandler();

        Male.setToggleGroup(gender);
        Male.setSelected(true);
        Female.setToggleGroup(gender);

        registerButton.setOnAction(_ -> {

            RadioButton selected = (RadioButton) gender.getSelectedToggle();

            db.registerUser(
                    fName.getText(),sName.getText(),nickname.getText(),
                    password.getText(),country.getText(),selected.getText()
            );

        });
    }

}
