package com.tignioj.studentmanagement.backend.service.impl;

import com.tignioj.studentmanagement.backend.dao.StuGradeDao;
import com.tignioj.studentmanagement.backend.dao.impl.StuGradeDaoImpl;
import com.tignioj.studentmanagement.backend.service.BaseServiceImpl;
import com.tignioj.studentmanagement.entities.StuGrade;
import com.tignioj.studentmanagement.entities.StuGraduate;
import com.tignioj.studentmanagement.entities.StuInfo;
import com.tignioj.studentmanagement.entities.view.StuGradeViewEntity;

import java.util.List;

public class StuGradeService extends BaseServiceImpl<StuGrade> {
    public StuGradeService() {
        this.baseDao = new StuGradeDaoImpl();
    }

    StuInfoService stuInfoService = new StuInfoService();

    /**
     * 添加一条数据到数据库
     *
     * @param stuGrade
     */
    public void addOne(StuGrade stuGrade) throws Exception {
        if (stuGrade.getStuInfo() == null) {
            throw new Exception("没有对应的学籍信息！");
        }

        StuInfo stuInfo = stuInfoService.get(stuGrade.getStuInfo().getStuNum());
        if (stuInfo == null) {
            throw new Exception("学号为" + stuGrade.getStuInfo().getStuNum() + "的学生不存在！");
        }


        //先查看是否已经存在此条成绩，如果存在，则更新而不是插入
        StuGrade gradeByGradeYearAndTermAndStuNum = ((StuGradeDao) baseDao).getGradeByGradeYearAndTermAndStuNum(stuGrade);
        if (gradeByGradeYearAndTermAndStuNum != null) {
            gradeByGradeYearAndTermAndStuNum.setStuGrade(stuGrade.getStuGrade());
            alter(gradeByGradeYearAndTermAndStuNum);
            return;
        }

        //如果不存在，则插入
        stuGrade.setStuInfo(stuInfo);
        add(stuGrade);
    }


    public List<StuGrade> getGradesByStuNum(String stuNum) throws Exception {
        List<StuGrade> gradesByStuNum = ((StuGradeDao) baseDao).getGradesByStuNum(stuNum);
        if (gradesByStuNum.size() == 0) {
            throw new Exception("没有成绩数据！");
        }
        return gradesByStuNum;
    }

    public List<StuGradeViewEntity> getGradeViewEntities(String stuNum) throws Exception {
        List<StuGradeViewEntity> gradeViewEntities = ((StuGradeDao) baseDao).getGradeEntityViewByStuNum(stuNum);

        if (gradeViewEntities == null) {
            throw new Exception("没有成绩数据");
        }
        return gradeViewEntities;
    }

    public void alterOne(StuGrade stuGrade) throws Exception {
        if (stuGrade.getStuInfo() == null) {
            throw new Exception("没有对应的学籍信息！");
        }

        StuInfo stuInfo = stuInfoService.get(stuGrade.getStuInfo().getStuNum());
        if (stuInfo == null) {
            throw new Exception("学号为" + stuGrade.getStuInfo().getStuNum() + "的学生不存在！");
        }


        //先查看是否已经存在此条成绩，如果存在，则更新而不是插入
        StuGrade gradeByGradeYearAndTermAndStuNum = ((StuGradeDao) baseDao).getGradeByGradeYearAndTermAndStuNum(stuGrade);
        if (gradeByGradeYearAndTermAndStuNum != null) {
            gradeByGradeYearAndTermAndStuNum.setStuGrade(stuGrade.getStuGrade());
            alter(gradeByGradeYearAndTermAndStuNum);
            return;
        }

        //如果不存在，则插入
        stuGrade.setStuInfo(stuInfo);
        add(stuGrade);
    }

    public StuGrade getGradeByGradeYearAndTermAndStuNum(StuGrade stuGrade) throws Exception {
        StuGrade gradeByGradeYearAndTermAndStuNum = null;
        gradeByGradeYearAndTermAndStuNum = ((StuGradeDao) baseDao).getGradeByGradeYearAndTermAndStuNum(stuGrade);
        if (gradeByGradeYearAndTermAndStuNum == null) {
//            throw new Exception("没有成绩数据");
            return null;

        }
        return gradeByGradeYearAndTermAndStuNum;
    }
}
