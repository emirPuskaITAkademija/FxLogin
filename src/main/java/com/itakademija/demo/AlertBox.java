package com.itakademija.demo;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static void display(String title, String message) {
        Stage stage = new Stage();
        //onaj u pozadini drugi glavni stage se vidi al jedini clickable treba da bude ovaj naš
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMinHeight(350);

        Label messageLabel = new Label(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());

        VBox vBox = new VBox(10);
        ObservableList<Node> vboxList = vBox.getChildren();
        vboxList.addAll(messageLabel, closeButton);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
