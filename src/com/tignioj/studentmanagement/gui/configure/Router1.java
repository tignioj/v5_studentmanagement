package com.tignioj.studentmanagement.gui.configure;

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

public class Router1 {
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
    public static Scene loginView;


    //加载视图
    static {
        window = Main.window;
//        welcomeView = loadSceneAndLoader("welcome/welcome.fxml");
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



    /**
     * 跳转到某个视图
     * @param scene
     */
    public static void go(Scene scene) {
        Main.window.setScene(scene);
    }
}
