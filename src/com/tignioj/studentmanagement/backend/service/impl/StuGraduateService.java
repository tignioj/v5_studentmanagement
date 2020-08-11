package com.tignioj.studentmanagement.backend.service.impl;

import com.tignioj.studentmanagement.backend.dao.StuInfoDao;
import com.tignioj.studentmanagement.backend.dao.impl.StuGraduateImpl;
import com.tignioj.studentmanagement.backend.dao.impl.StuInfoDaoImpl;
import com.tignioj.studentmanagement.backend.service.BaseServiceImpl;
import com.tignioj.studentmanagement.entities.StuGraduate;
import com.tignioj.studentmanagement.entities.StuInfo;

public class StuGraduateService extends BaseServiceImpl<StuGraduate> {
    public StuGraduateService() {
        this.baseDao = new StuGraduateImpl();
    }

    StuInfoDao stuInfoDao = new StuInfoDaoImpl();

    public void addOne(StuGraduate stuGraduate) throws Exception {
        if (stuGraduate == null) {
            throw new Exception("请输入数据！");
        }

        //查询数据库学籍表是否有该学生
        String stuNum = stuGraduate.getStuInfo().getStuNum();
        StuInfo stuInfo = stuInfoDao.get(stuNum);
        if (stuInfo == null) {
            throw new Exception("该学生不存在");
        }

        if (stuGraduate.getStuOutTime() == null) {
            throw new Exception("请输入毕业时间！");
        }
        add(stuGraduate);
    }
}
