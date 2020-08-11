//package com.tignioj.studentmanagement.gui.view.grademanage4;
//
//import com.jfoenix.controls.JFXButton;
//import com.jfoenix.controls.JFXTextField;
//import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
//import com.tignioj.studentmanagement.backend.service.impl.StuGradeService;
//import com.tignioj.studentmanagement.datasource.datacenter.DataCenter;
//import com.tignioj.studentmanagement.entities.StuGrade;
//import com.tignioj.studentmanagement.entities.StuInfo;
//import com.tignioj.studentmanagement.gui.msg.AlertBox;
//import com.tignioj.studentmanagement.gui.msg.ConfirmBox;
//import com.tignioj.studentmanagement.gui.utils.validator.AddJFXTextFieldValidatorUtils;
//import javafx.beans.property.*;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Pos;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.Region;
//import javafx.scene.layout.VBox;
//
//import java.text.DecimalFormat;
//import java.util.Objects;
//
//
//public class StuGradeWrapper_BAK extends RecursiveTreeObject<StuGradeWrapper_BAK>  {
//    private IntegerProperty id = new SimpleIntegerProperty();
//    private ObjectProperty<VBox> option = new SimpleObjectProperty<>();
//    private SimpleObjectProperty<ImageView> avatar = new SimpleObjectProperty<>();
//    private StringProperty name = new SimpleStringProperty();
//    //成绩
//    private ObjectProperty<JFXTextField> grade = new SimpleObjectProperty<>();
//    private SimpleIntegerProperty term = new SimpleIntegerProperty();
//
//    private BooleanProperty canEdit = new SimpleBooleanProperty();
//
//    private StringProperty num = new SimpleStringProperty();
//
//    private SimpleObjectProperty<JFXTextField> year = new SimpleObjectProperty<>();
//
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        StuGradeWrapper_BAK that = (StuGradeWrapper_BAK) o;
//        return id.getValue().equals(that.id.getValue());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id.getValue());
//    }
//
//    public StuGradeWrapper_BAK() {
//    }
//
//    public StuGradeWrapper_BAK(StuGrade stuGrade) {
//        wrapStuGrade(stuGrade);
//    }
//
//    static StuGradeService stuGradeService = new StuGradeService();
//
//    private VBox initVBox(StuGrade stuGrade) {
//        StuGradeWrapper_BAK sw = this;
//        VBox vBox = new VBox();
//
//        //Regon用于填充
//        Region r1 = new Region();
//        r1.setPrefHeight(10);
//
//        JFXButton editBtn = new JFXButton("编辑");
//        editBtn.setStyle("-fx-background-color: #42a4f4;-fx-text-fill: #ffffff");
//        editBtn.setPrefSize(100, 35);
//        editBtn.setButtonType(JFXButton.ButtonType.RAISED);
//
//        editBtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                if (editBtn.getText().equals("编辑")) {
//                    canEdit.setValue(true);
//                    editBtn.setStyle("-fx-background-color: #8899aa;-fx-text-fill: #ffffff");
//                    editBtn.setText("保存");
//                    return;
//                }
//
//                if (editBtn.getText().equals("保存")) {
//                    boolean b = ConfirmBox.display("警告", "确认保存？");
//                    if (b) {
//                        try {
//                            stuGradeService.alterOne(getStuGrade());
//                            canEdit.setValue(false);
//                            AlertBox.display("提示", "保存成功");
////                            GradeManageController controller = (GradeManageController) Router.getController(Router.gradeTableBindingView4);
////                            controller.refreshDate(GradeManageController.RefreshType.GRADE_UPDATE, sw);
//                            //TODO 并发修改问题，思路：数据中心提供api而不是类中自己修改
//                            DataCenter.updateStuGrade(sw);
//
//
//                            editBtn.setText("编辑");
//                            editBtn.setStyle("-fx-background-color: #42a4f4;-fx-text-fill: #ffffff");
//                        } catch (Exception e) {
//                            AlertBox.display("警告", e.getMessage());
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        });
//
//
//        JFXButton delBtn = new JFXButton("删除");
//        delBtn.setStyle("-fx-background-color: #42a4f4;-fx-text-fill: #ffffff");
//        delBtn.setPrefSize(100, 35);
//        delBtn.setButtonType(JFXButton.ButtonType.RAISED);
//
//        delBtn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                boolean confirm = ConfirmBox.display("警告", "确认删除？");
//                if (confirm) {
//                    try {
//                        stuGradeService.delete(getId());
//                        DataCenter.removeStuGrade(sw);
//                        AlertBox.display("提示", "删除成功");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        Region r2 = new Region();
//        r2.setPrefHeight(5);
//
//
//        vBox.getChildren().addAll(r1, editBtn, r2, delBtn);
//
//        return vBox;
//    }
//
//    private StuGrade getStuGrade() {
//        StuGrade<Object> stuGrade = new StuGrade<>();
//        stuGrade.setStuGrade(Double.parseDouble(getGrade().getText()));
//        stuGrade.setId(getId());
//
//
//        stuGrade.setStuTerm(getTerm());
//
//        StuInfo stuInfo = new StuInfo();
//        stuInfo.setStuNum(getNum());
//        stuInfo.setStuName(getName());
//
//        stuInfo.setStuNum(getNum());
//        stuGrade.setStuInfo(stuInfo);
//        stuGrade.setStuYear(getYear().getText());
//
//        return stuGrade;
//    }
//
//
//    public JFXTextField getYear() {
//        return year.get();
//    }
//
//    public SimpleObjectProperty<JFXTextField> yearProperty() {
//        return year;
//    }
//
//    public void setYear(JFXTextField year) {
//        this.year.set(year);
//    }
//
//    public String getNum() {
//        return num.get();
//    }
//
//    public StringProperty numProperty() {
//        return num;
//    }
//
//    public void setNum(String num) {
//        this.num.set(num);
//    }
//
//    private void wrapStuGrade(StuGrade stuGrade) {
//
//        ImageView imageView = new ImageView(ImageCenter.getImage(stuGrade.getStuInfo().getAvatar()));
//        imageView.setFitWidth(100);
//        imageView.setFitHeight(100);
//        setAvatar(imageView);
//        setId(stuGrade.getId());
//
//        setNum(stuGrade.getStuInfo().getStuNum());
//
//        setTerm(stuGrade.getStuTerm());
//        setName(stuGrade.getStuInfo().getStuName());
//        setGrade(initJFXTextFiledGrade(stuGrade));
//        setOption(initVBox(stuGrade));
//
//        setYear(initJFXTextFiledYear(stuGrade));
//
//    }
//
//    private JFXTextField initJFXTextFiledYear(StuGrade stuGrade) {
//        JFXTextField jfxTextField = initJFXTextField(stuGrade.getStuYear());
//        return jfxTextField;
//    }
//
//
//    static DecimalFormat df = new DecimalFormat("#.00");
//
//    private JFXTextField initJFXTextFiledGrade(StuGrade stuGrade) {
//        String str = df.format(stuGrade.getStuGrade());
//        JFXTextField jfxTextField = initJFXTextField(str);
//
//        //添加验证器
//        AddJFXTextFieldValidatorUtils.addDoubleValidator(true, jfxTextField);
//
//        return jfxTextField;
//    }
//
//    private JFXTextField initJFXTextField(String str) {
//        JFXTextField jfxTextField = new JFXTextField(str);
//        jfxTextField.setAlignment(Pos.CENTER);
//        jfxTextField.setPrefWidth(110);
//        jfxTextField.editableProperty().bindBidirectional(canEdit);
//        return jfxTextField;
//    }
//
//
//    public int getId() {
//        return id.get();
//    }
//
//    public IntegerProperty idProperty() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id.set(id);
//    }
//
//    public VBox getOption() {
//        return option.get();
//    }
//
//    public ObjectProperty<VBox> optionProperty() {
//        return option;
//    }
//
//    public void setOption(VBox option) {
//        this.option.set(option);
//    }
//
//    public ImageView getAvatar() {
//        return avatar.get();
//    }
//
//    public SimpleObjectProperty<ImageView> avatarProperty() {
//        return avatar;
//    }
//
//    public void setAvatar(ImageView avatar) {
//        this.avatar.set(avatar);
//    }
//
//    public String getName() {
//        return name.get();
//    }
//
//    public StringProperty nameProperty() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name.set(name);
//    }
//
//    public JFXTextField getGrade() {
//        return grade.get();
//    }
//
//    public ObjectProperty<JFXTextField> gradeProperty() {
//        return grade;
//    }
//
//    public void setGrade(JFXTextField grade) {
//        this.grade.set(grade);
//    }
//
//    public int getTerm() {
//        return term.get();
//    }
//
//    public SimpleIntegerProperty termProperty() {
//        return term;
//    }
//
//    public void setTerm(int term) {
//        this.term.set(term);
//    }
//
//    public boolean isCanEdit() {
//        return canEdit.get();
//    }
//
//    public BooleanProperty canEditProperty() {
//        return canEdit;
//    }
//
//    public void setCanEdit(boolean canEdit) {
//        this.canEdit.set(canEdit);
//    }
//
//    public StuGrade getWrapStuGrade() {
//        StuGrade stuGrade = new StuGrade();
//        stuGrade.setStuYear(getYear().getText());
//        stuGrade.setStuTerm(getTerm());
//        StuInfo stuInfo = new StuInfo();
//        stuInfo.setStuNum(getNum());
//        stuInfo.setStuName(getName());
//        stuGrade.setStuInfo(stuInfo);
//        stuGrade.setId(getId());
//        stuGrade.setStuGrade(Double.parseDouble(getGrade().getText()));
//        return stuGrade;
//    }
//
//
//    //修改后的值
//    public void updateOne(StuGradeWrapper_BAK sw) {
//        setAvatar(sw.getAvatar());
//        setName(sw.getName());
//        setYear(sw.getYear());
//        setGrade(sw.getGrade());
//        setTerm(sw.getTerm());
//    }
//}
