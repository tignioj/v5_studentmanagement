package com.tignioj.studentmanagement.gui.utils.validator;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

public class AddJFXTextFieldValidatorUtils {
    /**
     * 添加整数校验
     * @param required
     * @param jfxTextFields
     */
    public static void addIntegerValidator(boolean required, JFXTextField... jfxTextFields) {
        for (JFXTextField jfxTextField : jfxTextFields) {
            jfxTextField.getValidators().add(JFXTextFieldValidator.getIntegerValidator(required));
            jfxTextField.textProperty().addListener((opt, newValue, oldValue) -> jfxTextField.validate());
            jfxTextField.focusedProperty().addListener((opt, newValue, oldValue) -> jfxTextField.validate());
        }
    }

    //添加double值校验
    public static  void addDoubleValidator(boolean required, JFXTextField... jfxTextFields) {
        for (JFXTextField jfxTextField : jfxTextFields) {
            jfxTextField.getValidators().add(JFXTextFieldValidator.getDoubleValudator(required));
            jfxTextField.textProperty().addListener((opt, newValue, oldValue) -> jfxTextField.validate());
            jfxTextField.focusedProperty().addListener((obs, oldVallue, newValue) -> jfxTextField.validate());
        }

    }

    public static void addRequiredValidator(JFXTextField ...jfxTextFields) {
        for (JFXTextField jfxTextField : jfxTextFields) {
            jfxTextField.getValidators().add(new CustomValidator(true, "不允许空值！"));
            jfxTextField.focusedProperty().addListener((obs, oldVallue, newValue) -> jfxTextField.validate());
            jfxTextField.textProperty().addListener((obs, oldVallue, newValue) -> jfxTextField.validate());
        }

    }

    public static void addRequiredValidator(JFXPasswordField ...jfxPasswordFields) {
        for (JFXPasswordField jfxPasswordField : jfxPasswordFields) {
            jfxPasswordField.getValidators().add(new CustomValidator(true, "不允许空值！"));
            jfxPasswordField.focusedProperty().addListener((obs, oldVallue, newValue) -> jfxPasswordField.validate());
            jfxPasswordField.textProperty().addListener((obs, oldVallue, newValue) -> jfxPasswordField.validate());
        }
    }
}
