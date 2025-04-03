package com.itakademija.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;

/**
 * GridLayout -> GridPane
 */
public class HelloApplication extends Application {

    private final Text loginButtonText = new Text();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Login Page");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Label welcomeLabel = new Label("Welcome");
//
//        welcomeLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        welcomeLabel.setId("welcome-text");
        gridPane.add(welcomeLabel, 0, 0, 2, 1);

        Label usernameLabel = new Label("Username:");
        gridPane.add(usernameLabel, 0, 1);
        TextField usernameTextField = new TextField();
        gridPane.add(usernameTextField, 1, 1);

        Label passwordLabel = new Label("Password:");
        gridPane.add(passwordLabel, 0, 2);
        PasswordField passwordField = new PasswordField();
        gridPane.add(passwordField, 1, 2);

        Button loginButton = new Button("Sign In");
        loginButton.setOnAction(this::onLoginButtonClicked);
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(loginButton);
        gridPane.add(hBox, 1, 4);

        loginButtonText.setId("login-text");
        gridPane.add(loginButtonText, 1, 6);

        Scene scene = new Scene(gridPane, 300, 280);
        URL resource = HelloApplication.class.getResource("/styles/Login.css");
        if (resource != null)
            scene.getStylesheets().add(resource.toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void onLoginButtonClicked(ActionEvent event) {
        loginButtonText.setFill(Color.FIREBRICK);
        loginButtonText.setText("Sign In button pressed");
    }

    public static void main(String[] args) {
        launch();
    }
}