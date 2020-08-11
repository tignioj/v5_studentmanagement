package com.tignioj.studentmanagement.entities;

import java.util.Date;

public class StuGraduate {
    private StuInfo stuInfo; //学生学号
    private Date stuOutTime;

    public StuGraduate() {
    }

    public StuGraduate(StuInfo stuInfo, Date stuOutTime) {
        this.stuInfo = stuInfo;
        this.stuOutTime = stuOutTime;
    }

    public StuInfo getStuInfo() {
        return stuInfo;
    }

    public void setStuInfo(StuInfo stuInfo) {
        this.stuInfo = stuInfo;
    }

    public Date getStuOutTime() {
        return stuOutTime;
    }

    public void setStuOutTime(Date stuOutTime) {
        this.stuOutTime = stuOutTime;
    }


}
