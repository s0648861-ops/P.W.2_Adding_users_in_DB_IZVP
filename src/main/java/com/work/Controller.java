package com.work;

import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

    @FXML
    private Label loginText;

    @FXML
    private TextField login;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField password;

    @FXML
    private Button registration;

    @FXML
    private Button reset;

    Scene scene;

    PauseTransition pause = new PauseTransition(Duration.seconds(2));

    @FXML
    void initialize() {

        pause.setOnFinished(_ -> loginText.setText("Login"));


        loginButton.setOnAction(_ -> {
            if(login.getText().isEmpty() && password.getText().isEmpty()) {
                loginText.setText("Login and password are required");
                if (pause.getStatus().equals(Animation.Status.RUNNING)){
                    pause.stop();
                    pause.play();
                }
                else {
                    pause.play();
                }
            }
            else if(login.getText().isEmpty()) {
                loginText.setText("Login is required");
                if (pause.getStatus().equals(Animation.Status.RUNNING)){
                    pause.stop();
                    pause.play();
                }
                else {
                    pause.play();
                }
            }
            else if(password.getText().isEmpty()) {
                loginText.setText("Password is required");
                if (pause.getStatus().equals(Animation.Status.RUNNING)){
                    pause.stop();
                    pause.play();
                }
                else {
                    pause.play();
                }
            }
            else {
                try {
                    loginUser(login.getText(), password.getText());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        registration.setOnAction(_ -> {
            loginButton.getScene().getWindow().hide();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource("Registration.fxml"));
            scene(stage, fxmlLoader);
            stage.setTitle("Registration");
        });

        reset.setOnAction(_ -> {
            loginButton.getScene().getWindow().hide();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource("Reset.fxml"));
            scene(stage, fxmlLoader);
            stage.setTitle("Password Reset");
        });

    }

    private void scene(Stage stage, FXMLLoader fxmlLoader) {
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        stage.setOnCloseRequest(_ -> System.exit(0));
        stage.show();
    }

    public void loginUser(String log, String pass) throws SQLException {

        DBHandler db = new DBHandler();

        User user = new User();

        user.setUsername(log);
        user.setPassword(pass);

        ResultSet set = db.getUser(user);

        int counter = 0;

        try {
            while (set.next()) {
                counter++;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (counter >= 1) {
            loginButton.getScene().getWindow().hide();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("end.fxml"));
            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setTitle("end");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setOnCloseRequest(_ -> System.exit(0));
            stage.show();
        }
        else {
            loginText.setText("Login or password is wrong");
            if (pause.getStatus().equals(Animation.Status.RUNNING)){
                pause.stop();
                pause.play();
            }
            else {
                pause.play();
            }
        }
    }
}
