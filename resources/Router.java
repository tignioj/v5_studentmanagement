package com.tignioj.studentmanagement.gui.configure;

import com.tignioj.studentmanagement.entities.Admin;
import com.tignioj.studentmanagement.gui.view.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 视图路由
 */
public class Router {

    public static Map<Scene, FXMLLoader> loaderSceneMap = new HashMap<>();

    /**
     * 把loader和scene存到map中方便调用
     * @param path
     * @return
     */
    private static Scene loadSceneAndLoader(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(path));
            Pane root = loader.load();
            Scene scene = new Scene(root);
            scene.setFill(null);

//            setDraggable(scene);
            scene.getStylesheets().add(Main.class.getResource("main.css").toExternalForm());
            loaderSceneMap.put(scene, loader);
            return scene;
        } catch (IOException e) { e.printStackTrace(); }
        return null;
    }

    private static void setDraggable(Scene scene) {
        class Delta { double x, y; }

        final Delta dragDelta = new Delta();
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x = window.getX() - mouseEvent.getScreenX();
                dragDelta.y = window.getY() - mouseEvent.getScreenY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                window.setX(mouseEvent.getScreenX() + dragDelta.x);
                window.setY(mouseEvent.getScreenY() + dragDelta.y);
            }
        });
    }


    //各种视图
    public static Stage window;
    public static Scene stuInfoView;
    public static Scene welcomeView;
    public static Scene stuTreeListView;
    public static Scene loginView;
    public static Scene addStuInfoView;
    public static Scene stuEditView;
    //学籍管理建议
    public static Scene stuManageView;


    public static Scene gradeView;
//    public static Scene gradeTableView;
//    public static Scene gradeTableView2;
//    public static Scene gradeTableBindingView;
    public static Scene gradeTableBindingView4;

    public static Scene graduateView;

    public static Scene changePwdView;



    //加载视图
    static {
        window = Main.window;
        stuInfoView = loadSceneAndLoader("stumanage/view/StuInfoView.fxml");
        stuTreeListView = loadSceneAndLoader("stumanage/list/StuInfoListView.fxml");
        addStuInfoView = loadSceneAndLoader("stumanage/stuinfoAdd/StuInfoAdd.fxml");
        stuEditView = loadSceneAndLoader("stumanage/stuedit/StuEdit.fxml");
        stuManageView = loadSceneAndLoader("stumanage/statusmanage/StatusManage.fxml");

//        gradeView = loadSceneAndLoader("grademanage/GradeManage.fxml");
//        gradeTableView = loadSceneAndLoader("grademanage1/GradeManageTableView.fxml");
//        gradeTableView2 = loadSceneAndLoader("grademanage2/GradeManageTableView2.fxml");
//        gradeTableBindingView = loadSceneAndLoader("grademanage3/GradeManageBindingView.fxml");
        gradeTableBindingView4 = loadSceneAndLoader("grademanage4/GradeManageBindingView.fxml");

        graduateView = loadSceneAndLoader("graduate/StuGraduateView.fxml");

        changePwdView = loadSceneAndLoader("changepwd/ChangePwdView.fxml");


        welcomeView = loadSceneAndLoader("welcome/welcome.fxml");
        loginView = loadSceneAndLoader("login/Login.fxml");

    }


    /**
     * 获取控制器
     * @param scene
     * @param <T>
     * @return
     */
    public static <T> T getController(Scene scene) {
        FXMLLoader loader = loaderSceneMap.get(scene);
        return (T)loader.getController();
    }

    public static void initStyle() {

    }

    /**
     * 跳转到某个视图
     * @param scene
     */
    public static void go(Scene scene) {
        Main.window.setScene(scene);
    }

    public static Admin adminSaved;

}
