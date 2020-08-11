package treeSetTest;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetTest extends Application {
    public void test1() {
        TreeSet<JFXButton> jfxButtons = new TreeSet<>(new Comparator<JFXButton>() {
            @Override
            public int compare(JFXButton o1, JFXButton o2) {
                int i = o1.getId().compareTo(o2.getId());
                return i;
            }
        });

        for (int i = 0; i < 100; i++) {
            JFXButton jfxButton = new JFXButton("按钮");
            jfxButton.setId(i + "");
            jfxButtons.add(jfxButton);
        }

        for (int i = 0; i < 100; i++) {
            JFXButton jfxButton = new JFXButton("按钮");
            jfxButton.setId(i + "");
            jfxButtons.add(jfxButton);
        }

        for (JFXButton jb : jfxButtons) {
            System.out.println(jb.getId());
        }
        System.out.println(jfxButtons.size());

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        test1();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
