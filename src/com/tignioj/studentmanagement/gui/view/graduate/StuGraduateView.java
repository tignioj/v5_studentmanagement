package com.tignioj.studentmanagement.gui.view.graduate;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.tignioj.studentmanagement.backend.service.impl.StuGraduateService;
import com.tignioj.studentmanagement.datasource.datacenter.DataCenter;
import com.tignioj.studentmanagement.entities.StuGraduate;
import com.tignioj.studentmanagement.entities.StuInfo;
import com.tignioj.studentmanagement.gui.configure.Router;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class StuGraduateView implements Initializable {


    @FXML
    private JFXTreeTableView<GraduateWrapper> tree;
    @FXML
    private JFXTextField filterStuNumIn;
    @FXML
    private JFXTextField filterStuNameIn;
    @FXML
    private JFXComboBox<Boolean> filterisOutIn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComponent();
        initTree();
        initFilter();
    }

    private void initComponent() {
        filterisOutIn.getItems().addAll(true, false);
    }

    private void initFilter() {
        filterStuNumIn.textProperty().addListener((obs, oldValue, newValue) -> {
            tree.setPredicate(treeItem -> {

                boolean c;
                if (getComBoBox() == null) {
                    c = true;
                } else {
                    c = (String.valueOf(treeItem.getValue().isIsOut()).contains(String.valueOf(getComBoBox())));
                }

                return treeItem.getValue().getStuNum().contains(newValue) &
                        treeItem.getValue().getStuName().contains(filterStuNameIn.getText()) &
                        c;

            });
        });

        filterStuNameIn.textProperty().addListener((obs, oldValue, newValue) -> {
            tree.setPredicate(treeItem -> {

                boolean c;
                if (getComBoBox() == null) {
                    c = true;
                } else {
                    c = (String.valueOf(treeItem.getValue().isIsOut()).contains(String.valueOf(getComBoBox())));
                }

                return treeItem.getValue().getStuName().contains(newValue) &
                        treeItem.getValue().getStuNum().contains(filterStuNumIn.getText()) &
                        c;
            });

        });

        filterisOutIn.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            tree.setPredicate(treeItem -> {

                boolean c;
                if (getComBoBox() == null) {
                    c = true;
                } else {
                    c = (String.valueOf(treeItem.getValue().isIsOut()).contains(String.valueOf(newValue)));
                }

                return c &
                        treeItem.getValue().getStuNum().contains(filterStuNumIn.getText()) &
                        treeItem.getValue().getStuName().contains(filterStuNameIn.getText());

            });
        });


    }

    private String getComBoBox() {
        if (filterisOutIn.getSelectionModel().getSelectedItem() == null) {
            return null;
        } else {
            Boolean selectedItem = filterisOutIn.getSelectionModel().getSelectedItem();
            if (selectedItem) {
                return "true";
            } else {
                return "false";
            }
        }
    }

    public static StuGraduateService stuGraduateService = new StuGraduateService();
    ObservableList<GraduateWrapper> obsStuGraduates = DataCenter.getObsStuGraduates();

    private void initTree() {


        //设置年份
        TreeTableColumn<GraduateWrapper, String> numCol = getTreeViewHeader("学号", "stuNum");

        TreeTableColumn<GraduateWrapper, String> nameCol = getTreeViewHeader("姓名", "stuName");


        TreeTableColumn<GraduateWrapper, String> inTimeCol = getTreeViewHeader("入学时间", "stuOutTime");

        TreeTableColumn<GraduateWrapper, String> outTimeCol = getTreeViewHeader("毕业时间", "stuInTime");

        TreeTableColumn<GraduateWrapper, Boolean> isOutCol = getTreeViewHeader("是否毕业", "isOut");

        TreeItem<GraduateWrapper> root =
                new RecursiveTreeItem<>(obsStuGraduates, RecursiveTreeObject::getChildren);

        tree.getColumns().setAll(numCol, nameCol, inTimeCol, outTimeCol, isOutCol);

        tree.setRoot(root);

        tree.setShowRoot(false);

    }

    public void goWelcome(ActionEvent actionEvent) {
        Router.go(Router.welcomeView);
    }

    public static TreeTableColumn getTreeViewHeader(String showName, String propertyName) {
        TreeTableColumn column = new TreeTableColumn<>(showName);
//        column.setPrefWidth(70);
        column.setCellValueFactory(new TreeItemPropertyValueFactory<>(propertyName));
        return column;
    }

    public void cleanFilter(ActionEvent actionEvent) {
        filterStuNumIn.clear();
        filterStuNameIn.clear();
        filterisOutIn.getSelectionModel().clearSelection();
    }


    public enum RefreshType {
        GRADUATE_ADD,
        GRADUATE_DEL,
        GRADUATE_UPDATE,
        NONE
    }


    public void refreshDate(RefreshType type, StuInfo stuInfo) {
        GraduateWrapper graduateWrapper;
        if (stuInfo == null) {
            return;
        }
        switch (type) {
            case GRADUATE_ADD:
                try {
                    StuGraduate stuGraduate = stuGraduateService.get(stuInfo.getStuNum());
                    if (stuGraduate == null) {
                        return;
                    }
                    GraduateWrapper g = new GraduateWrapper(stuGraduate);
                    obsStuGraduates.add(g);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case GRADUATE_DEL:
                graduateWrapper = new GraduateWrapper();
                graduateWrapper.setStuNum(stuInfo.getStuNum());
                boolean remove = obsStuGraduates.remove(graduateWrapper);
                break;
            case NONE:
                break;
            case GRADUATE_UPDATE:
                graduateWrapper = new GraduateWrapper();
                graduateWrapper.setStuNum(stuInfo.getStuNum());
                int i = obsStuGraduates.indexOf(graduateWrapper);
                if (i != -1) {
                    obsStuGraduates.get(i).setStuName(stuInfo.getStuName());
                    obsStuGraduates.get(i).setStuInTime(new SimpleDateFormat("yyyy年MM月dd日").format(stuInfo.getStuInTime()));
                }
                break;
            default:
                System.out.println("未知操作！");
                break;
        }
        tree.refresh();
    }
}
