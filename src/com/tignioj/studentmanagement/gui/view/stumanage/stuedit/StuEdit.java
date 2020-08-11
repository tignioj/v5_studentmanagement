package com.tignioj.studentmanagement.gui.view.stumanage.stuedit;

import com.tignioj.studentmanagement.backend.service.impl.StuGradeService;
import com.tignioj.studentmanagement.backend.service.impl.StuInfoService;
import com.tignioj.studentmanagement.entities.StuGrade;
import com.tignioj.studentmanagement.entities.StuInfo;
import com.tignioj.studentmanagement.gui.component.stuinfomanage.oneStuinfo.OneStuInfo;
import com.tignioj.studentmanagement.gui.configure.Router;
import com.tignioj.studentmanagement.gui.msg.AlertBox;
import com.tignioj.studentmanagement.gui.msg.ConfirmBox;
import com.tignioj.studentmanagement.gui.view.grademanage4.StuGradeController;
import com.tignioj.studentmanagement.gui.view.graduate.StuGraduateView;
import com.tignioj.studentmanagement.gui.view.stumanage.list.StuInfoListViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StuEdit implements Initializable {

    @FXML
    public OneStuInfo oneStuInfoController;

    public void loadStuInfo(StuInfo stuInfo) {
        //允许编辑
        oneStuInfoController.setEditable(true);

        //主键不可编辑
        oneStuInfoController.getStuNumIn().setEditable(false);
        oneStuInfoController.setStuInfo(stuInfo);
    }

    StuInfoService stuInfoService = new StuInfoService();
    public void saveBtnClicked(ActionEvent actionEvent) {

        //合理性校验
        boolean b = oneStuInfoController.validateAll();
        if (!b) { System.out.println("不通过！"); return; }

        //获取保存后的学生信息
        StuInfo stuInfo = oneStuInfoController.getStuInfo();
        try {
            stuInfoService.updateOne(stuInfo);
            StuInfoListViewController controller = Router.getController(Router.stuTreeListView);
            //将更新的学生信息传到展示列表中供更新
            controller.refresh(StuInfoListViewController.RefreshType.STU_UPDATE, stuInfo);


            //刷新毕业表
            StuGraduateView stuGraduateView = (StuGraduateView) Router.getController(Router.graduateView);
            stuGraduateView.refreshDate(StuGraduateView.RefreshType.GRADUATE_UPDATE, oneStuInfoController.getStuInfo());


            //刷新成绩列表
            //在先获取成绩数据
            List<StuGrade> byStuNum = stuInfoService.getByStuNum(stuInfo.getStuNum());
            StuGradeController stuGradeController = (StuGradeController) Router.getController(Router.gradeTableBindingView4);

            if (byStuNum == null) {
                boolean tips = ConfirmBox.display("提示", "保存成功，是否返回列表？");
                stuGradeController.refreshDateByStu(StuGradeController.RefreshType.NONE, null);

                if (!tips) {
                } else {
                    Router.go(Router.stuTreeListView);
                }

            } else {
                stuGradeController.refreshDateByStu(StuGradeController.RefreshType.GRADE_UPDATE, byStuNum);
                boolean tips = ConfirmBox.display("提示", "保存成功，是否返回列表？");

                if (!tips) {
                } else {
                    Router.go(Router.stuTreeListView);
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void goList(ActionEvent actionEvent) {
        StuInfoListViewController controller = Router.getController(Router.stuTreeListView);
        controller.refresh(StuInfoListViewController.RefreshType.NONE,null);
        Router.go(Router.stuTreeListView);
    }

    public void goWelcome(ActionEvent actionEvent) {
        Router.go(Router.welcomeView);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    StuGradeService stuGradeService = new StuGradeService();
    public void delBtnClicked(ActionEvent actionEvent) {
        boolean bo = ConfirmBox.display("警告", "确认删除" + oneStuInfoController.getStuInfo().getStuNum() + "的学籍？");
        if (!bo) { return; }

        try {
            //在先获取成绩数据
            List<StuGrade> byStuNum = stuInfoService.getByStuNum(oneStuInfoController.getStuNumIn().getText());
            System.out.println("是否有数据:" + byStuNum);

            stuInfoService.delOne(oneStuInfoController.getStuNumIn().getText());

            AlertBox.display("提示", "删除成功");
            StuInfoListViewController controller = Router.getController(Router.stuTreeListView);
            //删除时，刷新学生列表
            controller.refresh(StuInfoListViewController.RefreshType.STU_DEL, oneStuInfoController.getStuInfo());
            Router.go(Router.stuTreeListView);

            //刷新成绩列表
            StuGradeController stuGradeController = (StuGradeController) Router.getController(Router.gradeTableBindingView4);
            stuGradeController.refreshDateByStu(StuGradeController.RefreshType.GRADE_DEL, byStuNum);


            //刷新毕业表
            StuGraduateView stuGraduateView = (StuGraduateView) Router.getController(Router.graduateView);
            stuGraduateView.refreshDate(StuGraduateView.RefreshType.GRADUATE_DEL, oneStuInfoController.getStuInfo());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
