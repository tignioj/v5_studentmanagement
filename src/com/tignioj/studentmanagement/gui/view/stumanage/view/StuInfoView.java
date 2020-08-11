package com.tignioj.studentmanagement.gui.view.stumanage.view;

import com.jfoenix.controls.JFXButton;
import com.tignioj.studentmanagement.gui.component.stuinfomanage.oneStuinfo.OneStuInfo;
import com.tignioj.studentmanagement.gui.configure.Router;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class StuInfoView implements Initializable {

    @FXML
    private OneStuInfo oneStuInfoController;

    @FXML
    private JFXButton editBtn;
    @FXML
    private JFXButton saveBtn;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComponent();
    }

    private void initComponent() {
        oneStuInfoController.getStuStatusComboBox().getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)-> {
//            System.out.println(oldValue + ":" + newValue);
        });

    }

    public void goWelcome(ActionEvent actionEvent) {
        Router.go(Router.welcomeView);
    }

    public void setEditable(ActionEvent actionEvent) {
        if (!oneStuInfoController.isEditable()) {
            editBtn.setText("退出编辑");
            oneStuInfoController.setEditable(true);
        } else {
            editBtn.setText("编辑");
            oneStuInfoController.setEditable(false);
        }
    }

    public void saveBtnClicked(ActionEvent actionEvent) {

    }

    public void deleteBenClicked(ActionEvent actionEvent) {

    }
}
