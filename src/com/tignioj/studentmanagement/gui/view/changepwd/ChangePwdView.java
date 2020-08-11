package com.tignioj.studentmanagement.gui.view.changepwd;

import com.jfoenix.controls.JFXTextField;
import com.tignioj.studentmanagement.backend.service.impl.AdminService;
import com.tignioj.studentmanagement.entities.Admin;
import com.tignioj.studentmanagement.gui.configure.Router;
import com.tignioj.studentmanagement.gui.msg.AlertBox;
import com.tignioj.studentmanagement.gui.utils.validator.AddJFXTextFieldValidatorUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangePwdView implements Initializable {

    @FXML
    private JFXTextField oldIn;
    @FXML
    private JFXTextField newIn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComponent();
    }

    private void initComponent() {
        AddJFXTextFieldValidatorUtils.addRequiredValidator(oldIn, newIn);
    }
    public boolean validateAll() {
        return oldIn.validate() & newIn.validate();
    }


    public void goWelcome(ActionEvent actionEvent) {
        Router.go(Router.welcomeView);
    }

    static AdminService adminService = new AdminService();
    public void saveBtnClick(ActionEvent actionEvent) {
        if (!validateAll()) {
            AlertBox.display("警告", "输入信息不全");
        } else if (oldIn.getText().equals(newIn.getText())) {
            AlertBox.display("警告", "新旧密码不能相同！");
        } else {
            Admin admin = Router.adminSaved;
            admin.setPwd(newIn.getText());
            try {
                adminService.changePWD(admin);
                AlertBox.display("提示", "更改成功！");
            } catch (Exception e) {
                AlertBox.display("警告", e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
