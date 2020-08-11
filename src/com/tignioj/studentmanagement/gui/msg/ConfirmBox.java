package com.tignioj.studentmanagement.gui.msg;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    //
    static boolean answer;

    public static final int[] LARGESIZE = {600, 800};
    public static final int[] MIDSIZE = {300, 400};
    public static final int[] SMALLSIZE = {150, 200};

    public static boolean display(String title, String message) {
//        Stage window = new Stage();
//        window.initModality(Modality.APPLICATION_MODAL);
//
//        window.setTitle(title);
//        window.setMaxHeight(250);
//        Label label = new Label();
//        label.setText(message);
//        window.setHeight(150);
//        window.setWidth(200);
//
//        //Create button
//        JFXButton yesButton = new JFXButton("是");
//        yesButton.setPrefSize(100, 30);
//        JFXButton noButton =  new JFXButton("否");
//        noButton.setPrefSize(100, 30);
//
//        yesButton.setOnAction(event -> {
//            answer = true;
//            window.close();
//        });
//        noButton.setOnAction(event -> {
//            answer = false;
//            window.close();
//        });
//
//
//        VBox layout = new VBox(10);
//        layout.getChildren().addAll(label, yesButton, noButton);
//        layout.setAlignment(Pos.CENTER);
//        Scene scene = new Scene(layout);
//        window.setScene(scene);
//        window.showAndWait();

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        window.setMaxHeight(250);
        window.setHeight(SMALLSIZE[0]);
        window.setWidth(SMALLSIZE[1]);

        Label label = new Label();
        label.setText(message);


        //Create button
        Button yesButton = new Button("是");
        yesButton.setPrefSize(100, 30);
        Button noButton = new Button("否");
        noButton.setPrefSize(100, 30);

        yesButton.setOnAction(event -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(event -> {
            answer = false;
            window.close();
        });


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
