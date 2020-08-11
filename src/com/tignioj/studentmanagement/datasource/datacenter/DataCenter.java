package com.tignioj.studentmanagement.datasource.datacenter;

import com.tignioj.studentmanagement.backend.dao.impl.StuInfoDaoImpl;
import com.tignioj.studentmanagement.backend.service.impl.StuGradeService;
import com.tignioj.studentmanagement.backend.service.impl.StuGraduateService;
import com.tignioj.studentmanagement.entities.StuGrade;
import com.tignioj.studentmanagement.entities.StuGraduate;
import com.tignioj.studentmanagement.entities.StuInfo;
import com.tignioj.studentmanagement.gui.view.grademanage4.StuGradeWrapper;
import com.tignioj.studentmanagement.gui.view.graduate.GraduateWrapper;
import com.tignioj.studentmanagement.gui.view.stumanage.list.StuInfoWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.util.List;

public class DataCenter {
    static StuInfoDaoImpl stuInfoDao = new StuInfoDaoImpl();
    static StuGradeService stuGradeService = new StuGradeService();
    static StuGraduateService stuGraduateService = new StuGraduateService();

    private static List<StuGrade> stuGrades;
    private static List<StuInfo> stuInfos;
    private static List<StuGraduate> stuGraduates;


    private static ObservableList<StuInfoWrapper> obsStuInfos = FXCollections.observableArrayList();
    private static ObservableList<StuGradeWrapper> obsStuGrades = FXCollections.observableArrayList();
    private static ObservableList<GraduateWrapper> obsStuGraduates = FXCollections.observableArrayList();

    public static ObservableList<StuInfoWrapper> getObsStuInfos() {
        return obsStuInfos;
    }

    public static ObservableList<StuGradeWrapper> getObsStuGrades() {
        return obsStuGrades;
    }

    public static ObservableList<GraduateWrapper> getObsStuGraduates() {
        return obsStuGraduates;
    }

    /**
     * 更新一个成绩信息
     *
     * @param stuGradeWrapper
     */
    public static void updateStuGrade(StuGradeWrapper stuGradeWrapper) {
        int i = obsStuGrades.indexOf(stuGradeWrapper);
        obsStuGrades.get(i).updateOne(stuGradeWrapper);
    }

    /**
     * 删掉一个成绩信息
     *
     * @param stuGradeWrapper
     */
    public static void removeStuGrade(StuGradeWrapper stuGradeWrapper) {
        obsStuGrades.remove(stuGradeWrapper);
    }

    public static void updateStuInfo(StuInfo stuInfo) {
    }

    public static class LoadTask extends Task<String> {
        int progress;
        int total;

        @Override
        protected String call() throws Exception {
            this.updateMessage("正在连接数据库");
            stuGrades = stuGradeService.getAll();
            stuInfos = stuInfoDao.getAll();
            stuGraduates = stuGraduateService.getAll();
            total = stuGrades.size() + stuInfos.size() + stuGraduates.size();

            initStuInfos();
            initStuGrades();
            initStuGraduates();

            StringBuilder sb = new StringBuilder();
            sb.append("学生数据:" + obsStuInfos.size() + " ");
            sb.append("成绩数据:" + obsStuGrades.size() + " ");
            sb.append("毕业数据:" + obsStuGraduates.size() + " ");
            return sb.toString();
        }

        private void initStuInfos() {
            System.out.println("从数据库获取学籍信息");
            progress = 0;
            for (StuInfo stuInfo : stuInfos) {
                obsStuInfos.add(new StuInfoWrapper(stuInfo));
                progress++;
                this.updateProgress(progress, total);
                this.updateMessage("第" + progress + "条学籍信息加载中");
            }
        }

        private void initStuGrades() {
            System.out.println("从数据库获取成绩数据");
            for (int i = 0; i < stuGrades.size(); i++) {
                StuGrade s = stuGrades.get(i);
                obsStuGrades.add(new StuGradeWrapper(s));
                String str = "第" + progress + "条成绩数据加载中";

                //必须sleep5毫秒以上，否则会出现并发修改异常
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.updateMessage(str);
//                System.out.println(Thread.currentThread().getId() + str);

                progress++;
                this.updateProgress(progress, total);
            }
//            System.out.println("--------------成绩数据加载完毕--------------");
        }

        private void initStuGraduates() {
            System.out.println("获取所有毕业数据");
            for (StuGraduate s : stuGraduates) {
                obsStuGraduates.add(new GraduateWrapper(s));
                progress++;
                this.updateProgress(progress, total);
                this.updateMessage("第" + progress + "条毕业数据加载中");
            }
        }
    }
}
