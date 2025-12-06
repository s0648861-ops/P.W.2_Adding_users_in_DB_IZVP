package com.work;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Objects;

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

        Male.setToggleGroup(gender);
        Male.setSelected(true);
        Female.setToggleGroup(gender);



        registerButton.setOnAction(_ -> createAccount());
    }

    private void createAccount() {

        if (Objects.equals(fName.getText(), "")) {
            System.out.println("First name is null");
            return;
        }
        if (Objects.equals(sName.getText(), "")) {
            System.out.println("Last name is null");
            return;
        }
        if (Objects.equals(nickname.getText(), "")) {
            System.out.println("Nickname is null");
            return;
        }
        if (Objects.equals(password.getText(), "")) {
            System.out.println("Password is null");
            return;
        }
        if (Objects.equals(country.getText(), "")) {
            System.out.println("Country is null");
        }


        RadioButton selected = (RadioButton) gender.getSelectedToggle();

        DBHandler db = new DBHandler();

        User user = new User(fName.getText(),sName.getText(),nickname.getText(),
                password.getText(),country.getText(),selected.getText());

        db.registerUser(user);

    }


}


