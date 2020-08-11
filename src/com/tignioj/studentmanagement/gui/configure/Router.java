package com.tignioj.studentmanagement.gui.configure;

import com.tignioj.studentmanagement.entities.Admin;
import com.tignioj.studentmanagement.gui.view.Main;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * 视图路由
 */
public class Router {
    /**
     * 多线程调用初始化
     */
    public static class LoadViewTask extends Task<String> {
        @Override
        protected String call() throws Exception {
            int i = 0;
            int total = views.size();
            this.updateMessage("===初始化视图中===");
            for (String v : views) {
                String s = "加载视图:" + v;
                System.out.println(s);
                this.updateMessage(s);
                loadSceneAndLoader(v);
                this.updateProgress(++i, total);
            }
            this.updateMessage("===初始化完毕===");
            return "视图加载完成";
        }
    }

    /**
     * 内部类，存放scene用于视图切换，Loader用于拿到Controller
     */
    private static class SceneAndLoader {
        Scene scene;
        FXMLLoader loader;

        public SceneAndLoader(Scene scene, FXMLLoader loader) {
            this.scene = scene;
            this.loader = loader;
        }
    }

    public static Map<String, SceneAndLoader> loaderSceneMap = new HashMap<>();

    /**
     * 把loader和scene存到map中方便调用
     *
     * @param path
     * @return
     */
    private static void loadSceneAndLoader(String path) {
        try {
            System.out.println("加载" + path);
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(path));
            System.out.println("loader" + loader);
            System.out.println("1");
            Pane root = loader.load();
            System.out.println("2");
            Scene scene = new Scene(root);
            System.out.println("3");
            scene.setFill(null);
//            setDraggable(scene);
//            scene.getStylesheets().add(Main.class.getResource("main.css").toExternalForm());
            System.out.println("加载成功################");
            if (loaderSceneMap.get(path) == null) {
                loaderSceneMap.put(path, new SceneAndLoader(scene, loader));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setDraggable(Scene scene) {
        class Delta {
            double x, y;
        }

        final Delta dragDelta = new Delta();
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x = window.getX() - mouseEvent.getScreenX();
                dragDelta.y = window.getY() - mouseEvent.getScreenY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                window.setX(mouseEvent.getScreenX() + dragDelta.x);
                window.setY(mouseEvent.getScreenY() + dragDelta.y);
            }
        });
    }

    //各种视图
    public static Stage window;

    public static final String stuInfoView;
    public static final String welcomeView;
    public static final String stuTreeListView;
    public static final String loginView;
    public static final String addStuInfoView;
    public static final String stuEditView;
    //学籍管理建议
    public static final String stuManageView;

    public static Scene gradeView;
    public static final String gradeTableBindingView4;
    public static final String graduateView;
    public static final String changePwdView;

//    public static final String dbSettingView;

    public static ArrayList<String> views = new ArrayList<>();


    //加载视图
    static {
        window = Main.window;

        stuInfoView = "stumanage/view/StuInfoView.fxml";
        views.add(stuInfoView);

        stuTreeListView = "stumanage/list/StuInfoListView.fxml";
        views.add(stuTreeListView);

        addStuInfoView = "stumanage/stuinfoAdd/StuInfoAdd.fxml";
        views.add(addStuInfoView);

        stuEditView = "stumanage/stuedit/StuEdit.fxml";
        views.add(stuEditView);

        stuManageView = "stumanage/statusmanage/StatusManage.fxml";
        views.add(stuManageView);

        gradeTableBindingView4 = "grademanage4/GradeManageBindingView.fxml";
        views.add(stuManageView);

        graduateView = "graduate/StuGraduateView.fxml";
        views.add(graduateView);

        changePwdView = "changepwd/ChangePwdView.fxml";
        views.add(changePwdView);

        welcomeView = "welcome/welcome.fxml";
        views.add(welcomeView);

        loginView = "login/Login.fxml";

//        dbSettingView = "login/configuredb/DBSetting.fxml";
//        views.add(dbSettingView);
    }
    /**
     * 1. 执行go
     * 2. 判断是否已经加载了视图，如果没有，则加载，如果有，则直接跳转到该视图
     */

    /**
     * 获取控制器
     *
     * @param sceneName
     * @param <T>
     * @return
     */
    public static <T> T getController(String sceneName) {
        SceneAndLoader sceneAndLoader = loaderSceneMap.get(sceneName);
        if (sceneAndLoader == null) {
            loadSceneAndLoader(sceneName);
            sceneAndLoader = loaderSceneMap.get(sceneName);
        }
        FXMLLoader loader = sceneAndLoader.loader;
        return (T) loader.getController();
    }

    public static void initStyle() {
    }

    /**
     * 跳转到某个视图
     *
     * @param sceneName
     */
    public static void go(String sceneName) {

        SceneAndLoader sceneAndLoader = loaderSceneMap.get(sceneName);
        if (sceneAndLoader == null) {
            loadSceneAndLoader(sceneName);
            sceneAndLoader = loaderSceneMap.get(sceneName);
        }
        Main.window.setScene(sceneAndLoader.scene);
    }

    public static Admin adminSaved;

}
