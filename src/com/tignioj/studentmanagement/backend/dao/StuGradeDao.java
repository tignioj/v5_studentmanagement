package com.tignioj.studentmanagement.backend.dao;


import com.tignioj.studentmanagement.entities.StuGrade;
import com.tignioj.studentmanagement.entities.view.StuGradeViewEntity;

import java.util.List;

public interface StuGradeDao extends BaseDao<StuGrade> {
    void delete(int id) throws Exception;


    List<StuGrade> getGradesByStuNum(String stuName) throws Exception;


    //查询视图v_stu_grade
    List<StuGradeViewEntity> getGradeEntityViewByStuNum(String stuNum) throws Exception;

    /**
     * 通过学生的学号，年份，成绩查询一条成绩信息
     * @param stuGrade
     * @return
     * @throws Exception
     */
    StuGrade getGradeByGradeYearAndTermAndStuNum(StuGrade stuGrade) throws Exception;
}
