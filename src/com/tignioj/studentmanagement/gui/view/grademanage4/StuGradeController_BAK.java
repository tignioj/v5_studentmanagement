//package com.tignioj.studentmanagement.gui.view.grademanage4;
//
//import com.jfoenix.controls.*;
//import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
//import com.jfoenix.validation.base.ValidatorBase;
//import com.tignioj.studentmanagement.datasource.datacenter.DataCenter;
//import com.tignioj.studentmanagement.entities.StuGrade;
//import com.tignioj.studentmanagement.gui.configure.Router;
//import com.tignioj.studentmanagement.gui.utils.validator.CustomValidator;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Label;
//import javafx.scene.control.TreeItem;
//import javafx.scene.control.TreeTableColumn;
//import javafx.scene.control.cell.TreeItemPropertyValueFactory;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.VBox;
//
//import java.net.URL;
//import java.util.List;
//import java.util.ResourceBundle;
//
//import static com.tignioj.studentmanagement.gui.utils.validator.AddJFXTextFieldValidatorUtils.addDoubleValidator;
//import static com.tignioj.studentmanagement.gui.utils.validator.AddJFXTextFieldValidatorUtils.addIntegerValidator;
//
//public class StuGradeController_BAK implements Initializable {
//
//    @FXML
//    private JFXTreeTableView<StuGradeWrapper> stuGradeTable;
//    @FXML
//    private JFXTextField filterStuNumIn;
//    @FXML
//    private JFXTextField filterYearIn;
//    @FXML
//    private JFXComboBox filterTermComboBox;
//    @FXML
//    private JFXButton cleanFilterBtn;
//    @FXML
//    private JFXTextField addStuNumIn;
//    @FXML
//    private JFXTextField addYearIn;
//    @FXML
//    private JFXComboBox addTermComboBox;
//    @FXML
//    private JFXTextField addGradeIn;
//
//
//
//    public static enum RefreshType {
//        GRADE_DEL,
//        GRADE_ADD,
//        GRADE_UPDATE,
//        NONE
//    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        initComponent();
//        initGradeList();
//    }
//
//    private void initComponent() {
//        addIntegerValidator(true, addStuNumIn, addYearIn);
//        addIntegerValidator(false, filterYearIn, filterStuNumIn);
//        addDoubleValidator(true, addGradeIn);
//
//        addStuNumIn.getValidators().add(new CustomValidator(true, "长度必须位10", ".{10}"));
//        initTermComboBox(addTermComboBox, filterTermComboBox);
//
//        cleanFilterBtn.setOnAction(event -> {
//            cleanFilter();
//        });
//
//        initFilter();
//
//    }
//
//    /**
//     * 初始化过滤器
//     */
//    private void initFilter() {
//        filterStuNumIn.textProperty().addListener((obs, oldValue, newValue) -> {
//            stuGradeTable.setPredicate(treeItem -> {
//
//                boolean booleanTerm = String.valueOf(treeItem.getValue().getTerm()).contains(getComboxValue(filterTermComboBox));
//
//                boolean booleanYear = treeItem.getValue().getYear().getText().contains(filterYearIn.getText());
//
//                boolean booleanNum = treeItem.getValue().getNum().contains(newValue);
//                return booleanNum && booleanYear && booleanTerm;
//            });
//        });
//
//
//        filterYearIn.textProperty().addListener((obs, oldValue, newValue) -> {
//            stuGradeTable.setPredicate(treeItem -> {
//                boolean booleanTerm = String.valueOf(treeItem.getValue().getTerm()).contains(getComboxValue(filterTermComboBox));
//                boolean booleanNum = treeItem.getValue().getNum().contains(filterStuNumIn.getText());
//                boolean booleanYear = treeItem.getValue().getYear().getText().contains(newValue);
//                return booleanNum && booleanYear && booleanTerm;
//            });
//        });
//
//        filterTermComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
//            stuGradeTable.setPredicate(treeItem -> {
//                boolean booleanTerm = true;
//                if (treeItem.getValue() == null || newValue == null) {
//                    booleanTerm = true;
//                } else {
//                    booleanTerm = String.valueOf(treeItem.getValue().getTerm()).contains(getComboxValue(filterTermComboBox));
//                }
//                boolean booleanNum = treeItem.getValue().getNum().contains(filterStuNumIn.getText());
//                boolean booleanYear = treeItem.getValue().getYear().getText().contains(filterYearIn.getText());
//                return booleanTerm && booleanNum && booleanYear;
//            });
//        });
//    }
//
//    public String getComboxValue(JFXComboBox jfxComboBox) {
//        Object selectedItem = jfxComboBox.getSelectionModel().getSelectedItem();
//        if (selectedItem == null) {
//            return "";
//        }
//        String text = ((Label) selectedItem).getText().trim();
//        return text;
//    }
//
//    /**
//     * 清除过滤器
//     */
//    private void cleanFilter() {
//        cleanTextField(filterStuNumIn, filterYearIn);
//        filterTermComboBox.getSelectionModel().clearSelection();
//    }
//
//    private void initTermComboBox(JFXComboBox... jfxComboBoxes) {
//        for (JFXComboBox<Label> jfxComboBox : jfxComboBoxes) {
//            ObservableList<Label> termList = FXCollections.observableArrayList();
//
//            termList.addAll(
//                    new Label("1"),
//                    new Label("2")
//            );
//
//            jfxComboBox.getItems().addAll(termList);
//            jfxComboBox.setPromptText("请选择");
////            jfxComboBox.getValidators().add(new CustomValidator(true, "请选择", ""));
//            jfxComboBox.getValidators().addAll(new ValidatorBase() {
//                @Override
//                protected void eval() {
//                    if (jfxComboBox.getSelectionModel().getSelectedItem() == null) {
//                        setMessage("请选择");
//                        hasErrors.set(true);
//                    } else {
//                        hasErrors.set(false);
//                    }
//                }
//            });
//        }
//    }
//
//
//    public static TreeTableColumn getTreeViewHeader(String showName, String propertyName) {
//        TreeTableColumn column = new TreeTableColumn<>(showName);
////        column.setPrefWidth(70);
//        column.setCellValueFactory(new TreeItemPropertyValueFactory<>(propertyName));
//        return column;
//    }
//
//
//    public ObservableList<StuGradeWrapper> obsStuGrades = DataCenter.getObsStuGrades();
//
//    private void initGradeList() {
//        stuGradeTable.setEditable(true);
//
//        //建立根
//        TreeItem<StuGradeWrapper> root = new RecursiveTreeItem<>(obsStuGrades, RecursiveTreeObject::getChildren);
//
//        TreeTableColumn<StuGradeWrapper, ImageView> userAvater = getTreeViewHeader("头像", "avatar");
//
//        //成绩的id
//        TreeTableColumn<StuGradeWrapper, Integer> gradeId = getTreeViewHeader("id", "id");
//
//        //设置为可以编辑
//        TreeTableColumn<StuGradeWrapper, String> gradeStuInfoNum = getTreeViewHeader("学号", "num");
//
//
//        //姓名
//        TreeTableColumn<StuGradeWrapper, String> gradeStuInfoName = getTreeViewHeader("姓名", "name");
//
//
//        //年
//        TreeTableColumn<StuGradeWrapper, JFXTextField> gradeYear = getTreeViewHeader("年份", "year");
//
//
//        //成绩
//        //https://www.youtube.com/watch?v=BNvVSU9nHDY 如何设置为可以编辑
//        TreeTableColumn<StuGradeWrapper, JFXButton> gradeGrade = getTreeViewHeader("成绩", "grade");
//
//
//        //学期
//        TreeTableColumn<StuGradeWrapper, Integer> gradeTerm = getTreeViewHeader("学期", "term");
//
//
//        //初始化操作
//        TreeTableColumn<StuGradeWrapper, VBox> gradeOption = getTreeViewHeader("操作", "option");
//
//        stuGradeTable.getColumns().setAll(
//                userAvater,
//                gradeId,
//                gradeStuInfoNum,
//                gradeStuInfoName,
//                gradeYear,
//                gradeGrade,
//                gradeTerm,
//                gradeOption
//        );
//        stuGradeTable.setRoot(root);
//        stuGradeTable.setShowRoot(false);
//    }
//
//
////    public static StuGradeService stuGradeService = new StuGradeService();
////    public static StuInfoService stuInfoService = new StuInfoService();
//
//
//    public void addGrade(ActionEvent actionEvent) {
////        addTermComboBox.validate();
////        boolean b = testComboxHasError(addTermComboBox);
////        boolean b1 = testJFXTextFieldhasError(addStuNumIn, addYearIn, addGradeIn);
////        if (b || b1) {
////            return;
////        }
////
////
////        String stuNumInText = addStuNumIn.getText().trim();
////
////        String stuYearInText = addYearIn.getText().trim();
////
////        String stuTermText = ((Label) addTermComboBox.getSelectionModel().getSelectedItem()).getText().trim();
////
////        String gradeInText = addGradeIn.getText().trim();
////
////
////        //待插入数据库的对象
////        StuGrade stuGrade = new StuGrade();
////
////
////        try {
////            //从数据库中获取学生信息
////            StuInfo stuInfo = stuInfoService.get(stuNumInText);
////            stuGrade.setStuInfo(stuInfo);
////
////            //设置成绩
////            stuGrade.setStuGrade(Double.parseDouble(gradeInText));
////
////            //设置学期
////            stuGrade.setStuTerm(Integer.parseInt(stuTermText));
////
////            //设置年份
////            stuGrade.setStuYear(stuYearInText);
////
////            //从数据中插叙是否已经有该数据
////
////            StuGrade existStuGrade = stuGradeService.getGradeByGradeYearAndTermAndStuNum(stuGrade);
////
////            //待插入表格的包装对象
////            StuGradeWrapper stuGradeWrapper = new StuGradeWrapper(stuGrade);
////
////
////            //更新数据库
////            stuGradeService.add(stuGrade);
////
////            AlertBox.display("提示", "添加成功！");
////
////            //如果不为空，则直接更新
////            if (existStuGrade != null) {
////
////                System.out.println("已经存在:" + existStuGrade);
////
////                int i = obsStuGrades.indexOf(new StuGradeWrapper(existStuGrade));
////
////                obsStuGrades.get(i).updateOne(stuGradeWrapper);
////                stuGradeTable.getSelectionModel().select(i);
////                stuGradeTable.scrollTo(i);
////                System.out.println("更新" + i);
////            } else {
////                //如果不存在，则先插入，后查询
////                StuGrade newGrade = stuGradeService.getGradeByGradeYearAndTermAndStuNum(stuGrade);
////
////                obsStuGrades.add(new StuGradeWrapper(newGrade));
////                stuGradeTable.getSelectionModel().selectLast();
////                stuGradeTable.scrollTo(stuGradeTable.getRoot().getChildren().size() - 1);
////                System.out.println("增加" + stuGradeWrapper);
////            }
////
////            cleanAddRow();
////        } catch (Exception e) {
////            e.printStackTrace();
////            AlertBox.display("警告", e.getMessage());
////        }
//    }
//
//
//    private boolean testComboxHasError(JFXComboBox... addTermComboBoxs) {
//        boolean hasError = false;
//        for (JFXComboBox jfxComboBox : addTermComboBoxs) {
//            boolean validate = jfxComboBox.validate();
//            if (!validate) {
//                hasError = true;
//            }
//        }
//        return hasError;
//    }
//
//    private void cleanAddRow() {
//        cleanTextField(addStuNumIn, addYearIn, addGradeIn);
//        addTermComboBox.getSelectionModel().clearSelection();
//    }
//
//    private void cleanTextField(JFXTextField... jfxTextFields) {
//        for (JFXTextField jfxTextField : jfxTextFields) {
//            jfxTextField.clear();
//            jfxTextField.resetValidation();
//        }
//    }
//
//    private boolean testJFXTextFieldhasError(JFXTextField... jfxTextFields) {
//        boolean hasError = false;
//        for (JFXTextField jfxTextField : jfxTextFields) {
//            //调用验证其
//            boolean validate = jfxTextField.validate();
//            if (!validate) {
//                hasError = true;
//            }
//        }
//        return hasError;
//    }
//
//    public void goWelcome(ActionEvent actionEvent) {
//        Router.go(Router.welcomeView);
//    }
//
//
//    public void mousePressed(MouseEvent mouseEvent) {
//    }
//
//    public void treeTableviewClicked(MouseEvent mouseEvent) {
//    }
//
//
//    public void refreshDateByStu(RefreshType type, List<StuGrade> stuGrades) {
////        System.out.println(stuGrades);
////        if (stuGrades == null) {
////            return;
////        }
////        for (StuGrade stuGrade : stuGrades)
////            switch (type) {
////                case GRADE_DEL:
////                    //删掉所有学生的成绩信息
////                    boolean remove = obsStuGrades.remove(new StuGradeWrapper(stuGrade));
////                    System.out.println(remove);
////                    break;
////                case NONE:
////                    break;
////                case GRADE_ADD:
////                    break;
////                case GRADE_UPDATE:
////                    //更新所有学生的头像
////                    StuGradeWrapper ns = new StuGradeWrapper(stuGrade);
////                    int i = obsStuGrades.indexOf(ns);
////                    obsStuGrades.get(i).updateOne(ns);
////            }
////        System.out.println("刷新！");
////        stuGradeTable.refresh();
////    }
//    }
//}
