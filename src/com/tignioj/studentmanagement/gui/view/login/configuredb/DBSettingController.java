package com.tignioj.studentmanagement.gui.view.login.configuredb;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.tignioj.studentmanagement.backend.utils.JdbcUtils;
import com.tignioj.studentmanagement.datasource.datacenter.DataCenter;
import com.tignioj.studentmanagement.gui.configure.Router;
import com.tignioj.studentmanagement.gui.msg.AlertBox;
import com.tignioj.studentmanagement.gui.msg.ConfirmBox;
import com.tignioj.studentmanagement.gui.utils.validator.AddJFXTextFieldValidatorUtils;
import com.tignioj.studentmanagement.gui.utils.validator.JFXTextFieldValidator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class DBSettingController implements Initializable {

    @FXML
    private JFXTextField dburl;
    @FXML
    private JFXTextField dbuser;
    @FXML
    private JFXTextField dbpwd;
    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private JFXTextArea infoArea;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        AddJFXTextFieldValidatorUtils.addRequiredValidator(dburl,dbuser, dbpwd);
//        initComponent();
    }

    StringProperty infoStr = new SimpleStringProperty("");
    private void initComponent() {
        infoArea.textProperty().bind(infoStr);
        Properties dbProperties = JdbcUtils.dbProperties;
        initTextField(dbProperties);
        progressBar.setVisible(false);
    }

    private void initTextField(Properties dbProperties) {
        String url1 = dbProperties.getProperty("url");
        String mysqlUsername = dbProperties.getProperty("mysqlUsername");
        String mysqlPassword = dbProperties.getProperty("mysqlPassword");

        dburl.setText(url1);
        dbuser.setText(mysqlUsername);
        dbpwd.setText(mysqlPassword);
    }


    private void appendMSGToInfoArea(String string) {
        infoStr.setValue(infoStr.getValue() + string + "\n");
    }
    public void saveConfig(ActionEvent actionEvent) {
        progressBar.setVisible(true);
        if (!validate()) {
            appendMSGToInfoArea("信息填写不完整");
            return;
        }

        System.out.println("设置数据库...");

        //测试连接
        try {
            appendMSGToInfoArea("检测数据库连接状态中..");
            JdbcUtils.testConnection(dburl.getText(), dbuser.getText(), dbpwd.getText());

            appendMSGToInfoArea("测试连接成功");
            JdbcUtils.updateDBProperties(null,
                    dburl.getText(),
                    dbuser.getText(),
                    dbpwd.getText());

            appendMSGToInfoArea("保存数据库信息成功");
        } catch (Exception e){
            AlertBox.display("错误", "测试连接失败！");
            appendMSGToInfoArea(e.getMessage());
            return;
        } finally {
            progressBar.setVisible(false);
        }
    }

    private boolean validate() {
        return dbpwd.validate() & dbuser.validate() & dbpwd.validate();
    }

    public void back(ActionEvent actionEvent) {
        Router.go(Router.loginView);
    }

    public void getSQLScript(ActionEvent actionEvent) {
//        String path = DBSettingController.class.getClassLoader().getResource("studentmanagement.sql").getPath();
//        File file = new File(path);
//        InputStream in = null;
//        try {
//            in = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        System.out.println(in);
//        InputStreamReader inputStreamReader = new InputStreamReader(in);
//        StringBuilder sqlsb = new StringBuilder();
//        char[] buff = new char[1024];
//        int len;
//        while (true) {
//            try {
//                if ((len = (inputStreamReader.read(buff))) == -1) {
//                    break;
//                } else {
//                    sqlsb.append(new String( buff,0, len));
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        showSQL(sqlsb.toString());
//        AlertBox.display("SQL脚本", sqlsb.toString(), AlertBox.LARGESIZE, false);
    }

    private void showSQL(String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("SQL脚本");
        window.setMaxHeight(500);
        window.setHeight(500);
        window.setWidth(500);

//        Label label = new Label();
//        label.setText(message);
        JFXTextArea jfxTextArea = new JFXTextArea();
        jfxTextArea.setEditable(false);
        jfxTextArea.setText(message);

        jfxTextArea.setPrefWidth(400);
        jfxTextArea.setPrefWidth(500);
        jfxTextArea.setMaxHeight(500);
        jfxTextArea.setMaxWidth(500);

        jfxTextArea.setPadding(new Insets(10, 10, 10, 10));
//        jfxTextArea.getStylesheets().add(getClass().getResource("dbconfigure.css").toExternalForm());

        JFXButton closeButton = new JFXButton("关闭");
        closeButton.setPrefSize(100,30);
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.setPrefHeight(500);
        layout.setPrefWidth(500);

//        layout.getChildren().addAll(label, closeButton);
        layout.getChildren().addAll(jfxTextArea, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();
    }
}
