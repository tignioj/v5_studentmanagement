package com.tignioj.studentmanagement.gui.component.stuinfomanage.oneStuinfo;

import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import com.tignioj.studentmanagement.entities.StuInfo;
import com.tignioj.studentmanagement.gui.utils.DateUtils;
import com.tignioj.studentmanagement.gui.utils.validator.AddJFXTextFieldValidatorUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class OneStuInfo implements Initializable {

    @FXML
    private ImageView avatar;
    @FXML
    private JFXTextField idenIn;
    @FXML
    private JFXTextField nameIn;
    @FXML
    private JFXTextField ageIn;
    @FXML
    private JFXTextField stuNumIn;
    @FXML
    private JFXTextField nationIn;
    @FXML
    private JFXTextField classIn;
    @FXML
    private JFXTextField politicsStatusIn;
    @FXML
    private JFXDatePicker inTimePicker;
    @FXML
    private JFXDatePicker birPicker;
    @FXML
    private JFXComboBox stuStatusComboBox;
    @FXML
    private JFXTextArea homeAddrIn;

    @FXML
    private ToggleGroup sexToggleGroup;
    @FXML
    private JFXRadioButton radioBoy;

    @FXML
    private JFXRadioButton radioGirl;

    @FXML
    private JFXTextField majorIn;

    public JFXTextField getMajorIn() {
        return majorIn;
    }

    public JFXRadioButton getRadioBoy() {
        return radioBoy;
    }

    public JFXRadioButton getRadioGirl() {
        return radioGirl;
    }

    public ToggleGroup getSexToggleGroup() { return sexToggleGroup; }

    public ImageView getAvatar() {
        return avatar;
    }

    public JFXTextField getIdenIn() {
        return idenIn;
    }

    public JFXTextField getNameIn() {
        return nameIn;
    }

    public JFXTextField getAgeIn() {
        return ageIn;
    }

    public JFXTextField getStuNumIn() {
        return stuNumIn;
    }

    public JFXTextField getNationIn() {
        return nationIn;
    }

    public JFXTextField getClassIn() {
        return classIn;
    }

    public JFXTextField getPoliticsStatusIn() {
        return politicsStatusIn;
    }

    public JFXDatePicker getInTimePicker() {
        return inTimePicker;
    }

    public JFXDatePicker getBirPicker() {
        return birPicker;
    }

    public JFXComboBox getStuStatusComboBox() {
        return stuStatusComboBox;
    }

    public JFXTextArea getHomeAddrIn() {
        return homeAddrIn;
    }

    //=============================================
    private boolean isEditable;

    public boolean isEditable() {
        return isEditable;
    }


    public void setStuInfo(StuInfo stuInfo) {
        ImageView imageView = new ImageView(stuInfo.getAvatar());
        //设置唯一标识
        avatar.setId(stuInfo.getAvatar());
        avatar.setImage(imageView.getImage());
        stuNumIn.setText(stuInfo.getStuNum());
        nameIn.setText(stuInfo.getStuName());
        idenIn.setText(stuInfo.getStuIdent());
        ageIn.setText(stuInfo.getStuAge() + "");

        nationIn.setText(stuInfo.getStuNation());
        politicsStatusIn.setText(stuInfo.getStuPolitics());
        inTimePicker.setPromptText(DateUtils.format(stuInfo.getStuInTime()));
        birPicker.setPromptText(DateUtils.format(stuInfo.getStuBir()));
        stuStatusComboBox.getSelectionModel().select(stuInfo.getStuStatus());
        homeAddrIn.setText(stuInfo.getStuAddr());
        classIn.setText(stuInfo.getStuClass());
        majorIn.setText(stuInfo.getStuMajor());
        ObservableList<Toggle> toggles = sexToggleGroup.getToggles();

        for (Toggle t : toggles) {
            JFXRadioButton radioButton = (JFXRadioButton) t;
            if (radioButton.getText().equals(stuInfo.getStuSex())) {
                sexToggleGroup.selectToggle(t);
            }
        }
        
    }
    
    public  boolean validateAll() {
        boolean b = stuNumIn.validate() &
        nameIn.validate() &
        idenIn.validate() &
        ageIn.validate() &
        nationIn.validate() &
        politicsStatusIn.validate() &
        homeAddrIn.validate() &
        classIn.validate() &
        majorIn.validate();
        return b;
    }


    public void cleanStuInfo() {
        avatar.setImage(null);
        stuNumIn.clear();
        stuNumIn.resetValidation();

        nameIn.clear();
        nameIn.resetValidation();

        idenIn.clear();
        idenIn.resetValidation();

        ageIn.clear();
        ageIn.resetValidation();

        nationIn.clear();
        nationIn.resetValidation();

        politicsStatusIn.clear();
        politicsStatusIn.resetValidation();

        majorIn.clear();
        majorIn.resetValidation();

        inTimePicker.setPromptText(DateUtils.format(new Date()));
        birPicker.setPromptText(DateUtils.format(new Date()));
        stuStatusComboBox.getSelectionModel().selectFirst();
        homeAddrIn.clear();
        homeAddrIn.resetValidation();

        classIn.clear();
        classIn.resetValidation();

        ObservableList<Toggle> toggles = sexToggleGroup.getToggles();
        sexToggleGroup.selectToggle(toggles.get(0));

    }

    public Date getDateFromLocalDate(LocalDate localDate) {
        Date date = java.sql.Date.valueOf(localDate);
        return date;
    }

    public StuInfo getStuInfo() {
        StuInfo stuInfo = new StuInfo();
        //默认值
//        stuInfo.setAvatar("img/avatar.jpg");
        stuInfo.setAvatar(avatar.getId());
        stuInfo.setStuNum(stuNumIn.getText());
        stuInfo.setStuName(nameIn.getText());
        stuInfo.setStuAddr(homeAddrIn.getText());
        if (!ageIn.getText().equals("") && ageIn.getText().matches("\\d+")) {
            stuInfo.setStuAge(Integer.parseInt(ageIn.getText()));
        }
        stuInfo.setStuClass(classIn.getText());
        stuInfo.setStuBir(getDateFromLocalDate(birPicker.getValue()));
        stuInfo.setStuIdent(idenIn.getText());
        stuInfo.setStuMajor(majorIn.getText());
        stuInfo.setStuPolitics(politicsStatusIn.getText());
        Toggle selectedToggle = sexToggleGroup.getSelectedToggle();
        if (selectedToggle != null) {
            JFXRadioButton radioButton = (JFXRadioButton) selectedToggle;
            stuInfo.setStuSex(radioButton.getText());
        }
        stuInfo.setStuNation(nationIn.getText());
        stuInfo.setStuInTime(getDateFromLocalDate(inTimePicker.getValue()));
        String selectedItem = (String) stuStatusComboBox.getSelectionModel().getSelectedItem();
        stuInfo.setStuStatus(selectedItem);
        return stuInfo;
    }




    @FXML
    void comboboxClicked(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComponent();
        initValidator();

    }

    private void initValidator() {
        AddJFXTextFieldValidatorUtils.addRequiredValidator(
                nationIn,
                stuNumIn,
                nameIn,
                majorIn,
                classIn,
                politicsStatusIn,
                ageIn
        );

        AddJFXTextFieldValidatorUtils.addIntegerValidator(
                true,
                idenIn,
                stuNumIn,
                ageIn
        );
        stuNumIn.getValidators().add(
                new ValidatorBase() {
                    @Override
                    protected void eval() {
                        TextInputControl node = (TextInputControl) srcControl.get();
                        if (node.getText().length() != 10) {
                            setMessage("学号长度必须为10");
                            hasErrors.set(true);
                        } else {
                            hasErrors.set(false);
                        }
                    }
                }
        );

        homeAddrIn.getValidators().add(new RequiredFieldValidator("不允许空值"));
    }

    private void initComponent() {
        setEditable(false);
        //combox
        stuStatusComboBox.getItems().addAll(
                "优秀",
                "合格",
                "试读",
                "退学"
        );
        stuStatusComboBox.getSelectionModel().selectFirst();

        //picker
        birPicker.setConverter(DateUtils.stringConverter);
        inTimePicker.setConverter(DateUtils.stringConverter);
        birPicker.setValue(LocalDate.now());
        inTimePicker.setValue(LocalDate.now());
        //sex
        sexToggleGroup.selectToggle(radioBoy);


        //avatar
        avatar.setImage(new Image(getClass().getClassLoader().getResourceAsStream("img/default.png")));
        avatar.setId("img/default.png");
    }


    public void setEditable(boolean editable) {
        isEditable = editable;
        idenIn.setEditable(editable);
        nameIn.setEditable(editable);
        ageIn.setEditable(editable);
        stuNumIn.setEditable(editable);
        nationIn.setEditable(editable);
        classIn.setEditable(editable);
        inTimePicker.setEditable(false);
        birPicker.setEditable(false);
        stuStatusComboBox.setEditable(false);
        politicsStatusIn.setEditable(editable);
        homeAddrIn.setEditable(editable);
    }


    private void setComboxEditable() {

    }


}
