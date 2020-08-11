package com.tignioj.studentmanagement.gui.msg;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public static void display(String title, String message) {
        display(title, message, SMALLSIZE);
    }

    public static final int[] LARGESIZE = {600, 800};
    public static final int[] MIDSIZE = {300, 400};
    public static final int[] SMALLSIZE = {150, 200};


    public static void display(String title, String message, int[] size) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        window.setMaxHeight(500);
        window.setHeight(size[0]);
        window.setWidth(size[1]);

        Label label = new Label();
        label.setText(message);

        JFXButton closeButton = new JFXButton("关闭");
        closeButton.setPrefSize(100,30);
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();
    }




    public static void display(String title, String message, int[] size, boolean isSingleLine) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        window.setMaxHeight(500);
        window.setHeight(size[0]);
        window.setWidth(size[1]);

//        Label label = new Label();
//        label.setText(message);
        JFXTextArea jfxTextArea = new JFXTextArea();
        jfxTextArea.setEditable(false);
        jfxTextArea.setText(message);
        jfxTextArea.setPadding(new Insets(10, 10, 10, 10));

        JFXButton closeButton = new JFXButton("关闭");
        closeButton.setPrefSize(100,30);
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
//        layout.getChildren().addAll(label, closeButton);
        layout.getChildren().addAll(jfxTextArea, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();
    }
}
