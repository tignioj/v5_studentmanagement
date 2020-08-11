package com.tignioj.studentmanagement.gui.utils.validator;

import com.jfoenix.validation.base.ValidatorBase;

public class JFXTextFieldValidator {
    //自定义验证器

    public static ValidatorBase getDoubleValudator(boolean required) {
        return new CustomValidator(required, "只允许数字", CustomValidator.DOUBLE_PATTERN);
    }

    public static ValidatorBase getIntegerValidator(boolean required) {
        return new CustomValidator(required, "只允许整数", CustomValidator.INTEGER_PATTERN);
    }


}
