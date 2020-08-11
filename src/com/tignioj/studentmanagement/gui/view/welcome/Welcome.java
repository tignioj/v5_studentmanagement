package com.tignioj.studentmanagement.gui.view.welcome;

import com.jfoenix.controls.JFXProgressBar;
import com.tignioj.studentmanagement.gui.configure.Router;
import com.tignioj.studentmanagement.gui.view.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Welcome implements Initializable {
    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private Label textLoadTip;


    public JFXProgressBar getProgressBar() {
        return progressBar;
    }

    public Label getTextLoadTip() {
        return textLoadTip;
    }

    @FXML
    void exitSystem(ActionEvent event) {
        Main.exitSystem();
    }

    @FXML
    void goGrade(ActionEvent event) {
        Router.go(Router.gradeTableBindingView4);
    }

    @FXML
    void goGraduate(ActionEvent event) {
        Router.go(Router.graduateView);
    }

    @FXML
    void goLogin(ActionEvent event) {
        Router.go(Router.loginView);
    }


    @FXML
    void gostumanage(ActionEvent event) {
        Router.go(Router.stuTreeListView);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void goChangePassword(ActionEvent actionEvent) {
        Router.go(Router.changePwdView);
    }
}
