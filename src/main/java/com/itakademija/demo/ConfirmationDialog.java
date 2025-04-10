package com.itakademija.demo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmationDialog {

    private boolean answer = false;

    public boolean showConfirmationDialog(String title, String message) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(600);


        Label label = new Label(message);
        Button yesButton = new Button("Yes");
        yesButton.setOnAction(e -> onYesButtonClick(stage));
        Button noButton = new Button("No");
        noButton.setOnAction(e -> onNoButtonClick(stage));

        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(noButton, yesButton);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(label, hBox);
        vbox.setAlignment(Pos.CENTER);

        vbox.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.showAndWait();
        return answer;
    }

    private void onNoButtonClick(Stage stage) {
        answer = false;
        stage.close();
    }

    private void onYesButtonClick(Stage stage) {
        answer = true;
        stage.close();
    }
}
