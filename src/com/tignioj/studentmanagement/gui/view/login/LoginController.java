package com.tignioj.studentmanagement.gui.view.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.tignioj.myutuils.web.VerifyCode;
import com.tignioj.studentmanagement.backend.service.impl.AdminService;
import com.tignioj.studentmanagement.backend.utils.JdbcUtils;
import com.tignioj.studentmanagement.entities.Admin;
import com.tignioj.studentmanagement.gui.configure.Router;
import com.tignioj.studentmanagement.gui.msg.AlertBox;
import com.tignioj.studentmanagement.gui.utils.validator.AddJFXTextFieldValidatorUtils;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private BorderPane loginPane;
    @FXML
    private JFXPasswordField passwordIn;
    @FXML
    private JFXTextField usernameIn;
    @FXML
    private JFXTextField codeIn;
    @FXML
    private ImageView codeImg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("11");
        AddJFXTextFieldValidatorUtils.addRequiredValidator(usernameIn, codeIn);
        AddJFXTextFieldValidatorUtils.addRequiredValidator(passwordIn);
        initCode();
        System.out.println("22");
    }


    VerifyCode verifyCode = new VerifyCode();
    private void initCode() {
        verifyCode.refresh();
        BufferedImage bufferedImage = verifyCode.getBufferedImage();
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        codeImg.setImage(image);
        System.out.println(verifyCode.getCode() + ":" + codeIn.getText());
    }

    AdminService adminService = new AdminService();

    public boolean validateAll() {
        return passwordIn.validate() & usernameIn.validate() & codeIn.validate() & codeEq();
    }

    private boolean codeEq() {
        if (!codeIn.getText().equalsIgnoreCase(verifyCode.getCode())) {
            AlertBox.display("警告", "验证码错误");
            return false;
        }
        return true;
    }

    public Admin getAdmin() {
        String uname = usernameIn.getText().trim();
        String pwd = passwordIn.getText().trim();
        return new Admin(uname, pwd);
    }

    public void registBtnClicked(MouseEvent mouseEvent) {
        if (!validateAll()) {
            initCode();
            return;
        }

        try {
            adminService.signIn(getAdmin());
            AlertBox.display("提示", "注册成功，自动登录");
            //保存
            Router.adminSaved = getAdmin();
            Router.go(Router.welcomeView);
        } catch (Exception e) {
            AlertBox.display("提示", e.getMessage());
        }
    }

    public void loginBtnClicked(MouseEvent mouseEvent) {
        if (!validateAll()) {
            initCode();
            return;
        }

        try {
            adminService.login(getAdmin());
            AlertBox.display("提示", "登录成功");
            //保存用户信息
            Router.adminSaved = getAdmin();
            Router.go(Router.welcomeView);

        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                AlertBox.display("错误", "数据库连接失败！");
            }
            AlertBox.display("提示", e.getMessage());
            initCode();
        }

    }

    public void changeBtnClick(MouseEvent mouseEvent) {
        initCode();
    }

    public void initDB(ActionEvent actionEvent) {
//        JdbcUtils.InitDBFromSQLScript();
//        AlertBox.display(
//                "温馨提示",
//                "自己执行init_mysql.sql文件哈哈哈哈\n" +
//                        "账号密码默认均为a, 记得要配置mysqlconfig.properties\n" +
//                        " 使得程序可以正确连接你的数据库，祝使用愉快",
//                AlertBox.MIDSIZE
//        );

//        Router.go(Router.dbSettingView);
    }

}
