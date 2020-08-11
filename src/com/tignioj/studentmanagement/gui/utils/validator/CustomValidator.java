package com.tignioj.studentmanagement.gui.utils.validator;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class CustomValidator extends ValidatorBase {

    public static final String INTEGER_PATTERN = "^\\d+";
    public static final String DOUBLE_PATTERN = "\\d{0,}\\.?\\d+";


    private String errMsg;

    private boolean required;

    //正则匹配的字符串
    private String[] patterns;



    public CustomValidator(boolean required, String errMsg, String... patterns) {
        if (errMsg == null || (required == false && patterns.length == 0)) {
            throw new IllegalArgumentException("参数不正确");
        }
        this.errMsg = errMsg;
        this.required = required;
        this.patterns = patterns;
    }

    //自定义验证器
    @Override
    protected void eval() {
        hasErrors.set(false);
        //获取TextField
        String text = "";

        Node node = srcControl.get();
        if (node instanceof JFXTextField) {
            JFXTextField jfxTextField = (JFXTextField) node;
            jfxTextField.resetValidation();
            text = jfxTextField.getText().trim();
        }

        if (node instanceof JFXPasswordField) {
            JFXPasswordField jfxPasswordField = (JFXPasswordField) node;
            jfxPasswordField.resetValidation();
            text = jfxPasswordField.getText().trim();
        }
        if (node instanceof JFXComboBox) {
            JFXComboBox jfxComboBox = (JFXComboBox) node;
            jfxComboBox.resetValidation();
            if(jfxComboBox.getSelectionModel().getSelectedItem() == null) {
                setMessage("请选择");
                hasErrors.set(true);
                return;
            }
            text = ((Label) jfxComboBox.getSelectionModel().getSelectedItem()).getText().trim();
        }
        if (node instanceof JFXTextArea) {
            ((JFXTextArea) node).resetValidation();
        }


        if (text.matches("") || text.length() == 0) {
            if (required) {
                setMessage("不允许空值");
                hasErrors.setValue(true);
            } else {
                hasErrors.set(false);
            }
            return;
        }



        for (String pattern : patterns) {
            if (!text.matches(pattern)) {
                setMessage(errMsg);
                hasErrors.setValue(true);
                return;
            } else {
                hasErrors.setValue(false);
            }
        }
    }
}
