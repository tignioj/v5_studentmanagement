package com.tignioj.studentmanagement.gui.view;

import com.tignioj.studentmanagement.gui.configure.Router1;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main1_8 extends Application {

    public static Stage window = null;

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        window = primaryStage;
//        Router.go(Router.loginView);
//        System.out.println("Login######################################");
////        Router.go(Router.welcomeView);
//
////        Router.go(Router.gradeView);
////        Router.go(Router.gradeTableView);
////        Router.go(Router.gradeTableBindingView);
////        Router.go(Router.gradeTableView2);
////
////        Router.go(Router.gradeTableBindingView4);
////        Router.go(Router.stuManageView);
////        Router.go(Router.stuManageView);
//
////        Router.go(Router.stuTreeListView);
//
//
////        Router.go(Router.graduateView);
//
////        Router.go(Router.changePwdView);
////        Router.go(Router.addStuInfoView);
//
////        primaryStage.initStyle(StageStyle.TRANSPARENT);
////
////        setUserAgentStylesheet(getClass().getResource("main.css").toExternalForm());
//
//        window.show();
//
//        //初始化数据
//        Welcome welcomeController = Router.getController(Router.welcomeView);
//        JFXProgressBar progressBar = welcomeController.getProgressBar();
//        Label textLoadTip = welcomeController.getTextLoadTip();
//
//        DataCenter.LoadTask loadDataTask = new DataCenter.LoadTask();
//        progressBar.progressProperty().bind(loadDataTask.progressProperty());
//        textLoadTip.textProperty().bind(loadDataTask.messageProperty());
//
//        EventHandler<WorkerStateEvent> eventHandler1 = new EventHandler<WorkerStateEvent>() {
//            @Override
//            public void handle(WorkerStateEvent event) {
//                textLoadTip.textProperty().unbind();
//                String value = loadDataTask.getValue();
//                textLoadTip.setText(value);
//                loadDataTask.removeEventFilter(WorkerStateEvent.WORKER_STATE_SUCCEEDED, this);
//            }
//        };
//        loadDataTask.addEventFilter(WorkerStateEvent.WORKER_STATE_SUCCEEDED, eventHandler1);
//        Thread loadDataThread = new Thread(loadDataTask);
//        loadDataThread.start();
//
//        //加载视图
//        Router.LoadViewTask loadViewTask = new Router.LoadViewTask();
//        Thread loadViewThread = new Thread(loadViewTask);
//        loadViewThread.start();
//    }

    public static void main(String[] args) {
        System.out.println("启动......");
        launch(args);
    }

    public static void exitSystem() {
        System.out.println("退出系统");
        System.exit(0);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        Parent root  = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 1024, 768);
        primaryStage.setScene(scene);
        primaryStage.show();

//        Router1.go(Router1.loginView);
        window.show();
    }
}
