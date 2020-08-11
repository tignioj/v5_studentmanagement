package com.tignioj.studentmanagement.backend.service.impl;

import com.tignioj.studentmanagement.backend.dao.StuGradeDao;
import com.tignioj.studentmanagement.backend.dao.impl.StuGradeDaoImpl;
import com.tignioj.studentmanagement.backend.dao.impl.StuInfoDaoImpl;
import com.tignioj.studentmanagement.backend.service.BaseServiceImpl;
import com.tignioj.studentmanagement.entities.StuGrade;
import com.tignioj.studentmanagement.entities.StuInfo;

import java.util.List;

public class StuInfoService extends BaseServiceImpl<StuInfo> {
    public StuInfoService() {
        baseDao = new StuInfoDaoImpl();
    }

    static StuGradeDao stuGradeDao = new StuGradeDaoImpl();

    public void addOne(StuInfo stuInfo) throws Exception {
        StuInfo stuInfo1 = get(stuInfo.getStuNum());
        if (stuInfo1 != null) {
            throw new Exception("学号为:" + stuInfo1.getStuNum() + "的学生已存在");
        }
        add(stuInfo);
    }


    public void updateOne(StuInfo stuInfo) throws Exception {
        StuInfo stuInfoFromDB = get(stuInfo.getStuNum());
        if (stuInfoFromDB == null) {
            throw new Exception("学号为:" + stuInfo.getStuNum() + "的学生不存在");
        }
        alter(stuInfo);
    }

    /**
     * 删除学籍
     * @param id
     */
    public void delOne(String id) throws Exception {
        StuInfo stuInfo1 = get(id);
        if (stuInfo1 == null) {
            throw new Exception("学号为:" + id + "的学生不存在");
        }
        delete(id);
    }

    public List<StuGrade> getByStuNum(String stuNum) throws Exception {
        List<StuGrade> gradesByStuNum = stuGradeDao.getGradesByStuNum(stuNum);
        System.out.println("数据库查询：" + gradesByStuNum);
        //BUG FIX1 NULL POINT EXCEPTION
        if (gradesByStuNum == null || gradesByStuNum.size() == 0) {
//            throw new Exception("没有成绩数据！");
            return null;
        }
        return gradesByStuNum;
    }
}
