package com.tignioj.studentmanagement.gui.view.stumanage.stuinfoAdd;

import com.jfoenix.controls.JFXButton;
import com.tignioj.studentmanagement.backend.service.impl.StuInfoService;
import com.tignioj.studentmanagement.entities.StuInfo;
import com.tignioj.studentmanagement.gui.component.stuinfomanage.oneStuinfo.OneStuInfo;
import com.tignioj.studentmanagement.gui.configure.Router;
import com.tignioj.studentmanagement.gui.msg.AlertBox;
import com.tignioj.studentmanagement.gui.msg.ConfirmBox;
import com.tignioj.studentmanagement.gui.view.graduate.StuGraduateView;
import com.tignioj.studentmanagement.gui.view.stumanage.list.StuInfoListViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class StuInfoAddController implements Initializable {

    @FXML
    private JFXButton saveBtn;


    @FXML
    private OneStuInfo oneStuInfoController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        oneStuInfoController.setEditable(true);
//        oneStuInfoController.cleanStuInfo();

    }

    StuInfoService stuInfoService = new StuInfoService();

    public void saveBtnClicked(ActionEvent actionEvent) {
        if (!oneStuInfoController.validateAll()) {
            System.out.println("不通过");
            return;
        }
        StuInfo stuInfo = oneStuInfoController.getStuInfo();
        try {
            stuInfoService.addOne(stuInfo);
            boolean tips = ConfirmBox.display("提示", "添加成功， 是否继续添加？");

            //刷新LIst
            StuInfoListViewController controller = Router.<StuInfoListViewController>getController(Router.stuTreeListView);
            controller.refresh(StuInfoListViewController.RefreshType.STU_ADD, stuInfo);

            //刷新毕业表
            StuGraduateView stuGraduateView = (StuGraduateView) Router.getController(Router.graduateView);
            stuGraduateView.refreshDate(StuGraduateView.RefreshType.GRADUATE_ADD, oneStuInfoController.getStuInfo());



            if (tips) {
                oneStuInfoController.cleanStuInfo();
            } else {
                Router.go(Router.stuTreeListView);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            AlertBox.display("出错!", e.getMessage());
        }


    }

    public void goList(ActionEvent actionEvent) {
        Router.go(Router.stuTreeListView);
    }


    public void goWelcome(ActionEvent actionEvent) {
        Router.go(Router.welcomeView);
    }
}
