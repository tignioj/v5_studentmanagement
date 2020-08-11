package com.tignioj.studentmanagement.gui.view.stumanage.list;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.tignioj.studentmanagement.datasource.datacenter.DataCenter;
import com.tignioj.studentmanagement.entities.StuInfo;
import com.tignioj.studentmanagement.gui.configure.Router;
import com.tignioj.studentmanagement.gui.msg.AlertBox;
import com.tignioj.studentmanagement.gui.view.stumanage.statusmanage.StatusManage;
import com.tignioj.studentmanagement.gui.view.stumanage.stuedit.StuEdit;
import com.tignioj.studentmanagement.gui.view.stumanage.stuinfoAdd.StuInfoAddController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Comparator;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class StuInfoListViewController implements Initializable {


    @FXML
    private JFXTreeTableView<StuInfoWrapper> stuTreeList;
    @FXML
    private JFXTextField stuNumIn;
    @FXML
    private JFXTextField stuNameIn;

    //数据中心
    ObservableList<StuInfoWrapper> stuInfoWrappers = DataCenter.getObsStuInfos();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initStuInfoList();

        initFilter();
    }


    private void initFilter() {
//        stuNumIn.textProperty().addListener((v, o, newValue) ->{
//            System.out.println(o +":" +  newValue);
//        });
        stuNumIn.textProperty().addListener((v, oldValue, newValue) -> {
            stuTreeList.setPredicate((Predicate<TreeItem<StuInfoWrapper>>) treeItem -> {
                boolean booleanName = treeItem.getValue().getStuName().contains(stuNameIn.getText().trim());
                boolean booleanNum = treeItem.getValue().getStuNum().contains(newValue.trim());
                return booleanName && booleanNum;
            });
        });

        stuNameIn.textProperty().addListener((v, oldValue, newValue) -> {
            stuTreeList.setPredicate((Predicate<TreeItem<StuInfoWrapper>>) treeItem -> {
                boolean booleanNum = treeItem.getValue().getStuNum().contains(stuNumIn.getText());
                boolean booleanName = treeItem.getValue().getStuName().contains(newValue);
                return booleanName && booleanNum;
            });
        });
    }


    public static TreeTableColumn getTreeViewHeader(String showName, String propertyName) {
        TreeTableColumn column = new TreeTableColumn<>(showName);
//        column.setPrefWidth(70);
        column.setCellValueFactory(new TreeItemPropertyValueFactory<>(propertyName));
        return column;
    }




    private void initStuInfoList() {
        //设置头像
        TreeTableColumn<StuInfoWrapper, ImageView> avatarCol = getTreeViewHeader("头像", "avatar");

        TreeTableColumn<StuInfoWrapper, String> stuNum = getTreeViewHeader("学号", "stuNum");
        //对某列设置比较器
        stuNum.setComparator(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Integer i1 = Integer.parseInt(o1);
                Integer i2 = Integer.parseInt(o2);
                return i2 - i1;
            }
        });

        TreeTableColumn<StuInfoWrapper, String> stuIden = getTreeViewHeader("身份证", "stuIdent");
        TreeTableColumn<StuInfoWrapper, String> stuName = getTreeViewHeader("姓名", "stuName");
        TreeTableColumn<StuInfoWrapper, Integer> stuAge = getTreeViewHeader("年龄", "stuAge");
        TreeTableColumn<StuInfoWrapper, String> stuSex = getTreeViewHeader("性别", "stuSex");
        TreeTableColumn<StuInfoWrapper, String> stuClass = getTreeViewHeader("班级", "stuClass");
        TreeTableColumn<StuInfoWrapper, Date> stuBir = getTreeViewHeader("出生日期", "stuBir");
        TreeTableColumn<StuInfoWrapper, Date> stuInTime = getTreeViewHeader("入学时间", "stuInTime");

        TreeTableColumn<StuInfoWrapper, String> stuMajor = getTreeViewHeader("专业", "stuMajor");
        TreeTableColumn<StuInfoWrapper, String> stuPolitics = getTreeViewHeader("政治面貌", "stuPolitics");
        TreeTableColumn<StuInfoWrapper, String> stuNation = getTreeViewHeader("民族", "stuNation");
        TreeTableColumn<StuInfoWrapper, String> stuAddr = getTreeViewHeader("家庭地址", "stuAddr");
        TreeTableColumn<StuInfoWrapper, String> stuStatus = getTreeViewHeader("学籍状态", "stuStatus");


        final TreeItem<StuInfoWrapper> root = new RecursiveTreeItem<>(stuInfoWrappers, RecursiveTreeObject::getChildren);
        stuTreeList.getColumns().setAll(
                avatarCol,
                stuNum, stuIden, stuName, stuAge,
                stuSex, stuClass, stuBir, stuInTime,
                stuMajor, stuPolitics, stuNation, stuAddr, stuStatus);

        stuTreeList.setRoot(root);
        stuTreeList.setShowRoot(false);
    }



    public void goWelcome(ActionEvent actionEvent) {
        Router.go(Router.welcomeView);
    }

    public void addStuInfo(ActionEvent actionEvent) {
        Router.<StuInfoAddController>getController(Router.addStuInfoView);
        Router.go(Router.addStuInfoView);
    }

    public void editInfo(ActionEvent actionEvent) {

//        int count = stuTreeList.getCurrentItemsCount();
//        if (count == 0) {
//            AlertBox.display("警告", "您还没有选中项目！");
//            return;
//        }

        TreeItem<StuInfoWrapper> selectedItem = (TreeItem<StuInfoWrapper>) stuTreeList.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            AlertBox.display("警告", "您还没有选中项目！");
        } else {
            Router.<StuEdit>getController(Router.stuEditView).loadStuInfo(selectedItem.getValue().getStuInfo());
            Router.go(Router.stuEditView);
        }
    }


    /**
     * @param newStuInfo 外部删除学生，修改头像，新增学生时需要传入更新后的学生
     */
    public void refresh(RefreshType type, StuInfo newStuInfo) {

        switch (type) {
            case STU_ADD:
                //根目录添加
                stuInfoWrappers.add(new StuInfoWrapper(newStuInfo));
                //添加学生时，hashMap中不存在obj，此时添加一个到hashMap中
                break;
            case STU_DEL:
                //根目录删除
                stuInfoWrappers.remove(new StuInfoWrapper(newStuInfo));
                //如果删除了学生
                break;
            case STU_UPDATE:
                //查找更新了哪个学生, 他会根据equals方法来匹配
                int i = stuInfoWrappers.indexOf(new StuInfoWrapper(newStuInfo));
                System.out.println(i);
                //拿到旧对象
                StuInfoWrapper oldStu = stuInfoWrappers.get(i);

                //更新旧对象为新对象
                oldStu.wrapStuInfo(newStuInfo);

            case NONE:
                //不更新
                break;
            default:
        }

//        stuTreeList.setRoot(newRoot);
        stuTreeList.refresh();
    }


    public enum RefreshType {
        STU_ADD,
        STU_DEL,
        STU_UPDATE,
        NONE
    }

    public void goManage(ActionEvent actionEvent) {

        TreeItem<StuInfoWrapper> selectedItem = (TreeItem<StuInfoWrapper>) stuTreeList.getSelectionModel().getSelectedItem();

        if (selectedItem == null) {
            AlertBox.display("警告", "您还没有选中项目！");
        } else {
            Router.<StatusManage>getController(Router.stuManageView).loadStuInfo(selectedItem.getValue().getStuInfo());

            Router.go(Router.stuManageView);
        }
    }
}
