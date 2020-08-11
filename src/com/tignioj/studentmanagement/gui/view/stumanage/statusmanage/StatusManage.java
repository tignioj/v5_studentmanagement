package com.tignioj.studentmanagement.gui.view.stumanage.statusmanage;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.tignioj.studentmanagement.backend.service.impl.StuGradeService;
import com.tignioj.studentmanagement.entities.StuInfo;
import com.tignioj.studentmanagement.entities.view.StuGradeViewEntity;
import com.tignioj.studentmanagement.gui.configure.Router;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

public class StatusManage implements Initializable {
    @FXML
    private JFXTextField stuNumIn;
    @FXML
    private JFXTextField stuNameIn;
    @FXML
    private JFXTreeTableView tree;
    @FXML
    private Label gradeSum;
    @FXML
    private Label currentStatus;
    @FXML
    private Label adviceStatus;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    StuGradeService gradeService = new StuGradeService();

    public void goWelcome(ActionEvent actionEvent) {
        Router.go(Router.welcomeView);
    }

    public void loadStuInfo(StuInfo value) {
        try {
            stuNameIn.setText(value.getStuName());
            stuNumIn.setText(value.getStuNum());

            initTree(value);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initTree(StuInfo value) {
        List<StuGradeViewEntity> gradeViewEntities = null;

        currentStatus.setText(value.getStuStatus());
        try {
            tree.getColumns().clear();

            gradeViewEntities = gradeService.getGradeViewEntities(value.getStuNum());
        } catch (Exception e) {
            gradeSum.setText("无");
            adviceStatus.setText("暂无建议");
//            e.printStackTrace();
            return;
        }

        ObservableList<GradeYearTermWrapper> gradeWrappers = FXCollections.observableArrayList();

        double sum = 0;

        for (int i = 0; i < gradeViewEntities.size(); i++) {
            StuGradeViewEntity stuGradeViewEntity = gradeViewEntities.get(i);
            GradeYearTermWrapper g = new GradeYearTermWrapper(stuGradeViewEntity);
            gradeWrappers.add(g);
            sum += g.getTerm1();
            sum += g.getTerm2();
        }

        DecimalFormat df = new DecimalFormat("#.00");
        double avg = sum / (gradeViewEntities.size() * 2);

        String str = df.format(avg);
        gradeSum.setText(str);

        if (avg < 50) {
            adviceStatus.setText("退学");
        } else if (avg < 60) {
            adviceStatus.setText("试读");
        } else if (avg < 80) {
            adviceStatus.setText("合格");
        } else {
            adviceStatus.setText("优秀");
        }

        //设置年份
        TreeTableColumn<GradeYearTermWrapper, String> yearCol = getTreeViewHeader("年份", "year");

        //学期
        TreeTableColumn<GradeYearTermWrapper, String> termCol1 = getTreeViewHeader("学期1", "term1");

        //成绩
        TreeTableColumn<GradeYearTermWrapper, String> gradeCol = getTreeViewHeader("学期2", "term2");

        final TreeItem<GradeYearTermWrapper> root = new RecursiveTreeItem<GradeYearTermWrapper>(gradeWrappers, RecursiveTreeObject::getChildren);

        tree.getColumns().setAll(yearCol, termCol1, gradeCol);

        tree.setRoot(root);

        tree.setShowRoot(false);

    }

    public static TreeTableColumn getTreeViewHeader(String showName, String propertyName) {
        TreeTableColumn column = new TreeTableColumn<>(showName);
//        column.setPrefWidth(70);
        column.setCellValueFactory(new TreeItemPropertyValueFactory<>(propertyName));
        return column;
    }
}
