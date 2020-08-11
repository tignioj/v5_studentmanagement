package com.tignioj.studentmanagement.gui.view.graduate;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.tignioj.studentmanagement.entities.StuGraduate;
import com.tignioj.studentmanagement.entities.StuInfo;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class GraduateWrapper extends RecursiveTreeObject<GraduateWrapper> {

    private StringProperty stuNum = new SimpleStringProperty(); //学生
    private StringProperty stuName = new SimpleStringProperty(); //学生姓名
    private StringProperty stuOutTime = new SimpleStringProperty(); // 毕业时间
    private BooleanProperty isOut = new SimpleBooleanProperty();
    private StringProperty stuInTime = new SimpleStringProperty();



    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

    public GraduateWrapper(StuGraduate stuGraduate) {
        StuInfo stuInfo = stuGraduate.getStuInfo();
        setStuNum(stuInfo.getStuNum());
        setStuInTime(sdf.format(stuInfo.getStuInTime()));
        setStuOutTime(sdf.format(stuGraduate.getStuOutTime()));
        setStuName(stuInfo.getStuName());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraduateWrapper that = (GraduateWrapper) o;
        return stuNum.getValue().equals(that.stuNum.getValue());
    }

    public GraduateWrapper() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuNum.getValue());
    }

    public String getStuName() {
        return stuName.get();
    }

    public StringProperty stuNameProperty() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName.set(stuName);
    }

    public String getStuNum() {
        return stuNum.get();
    }

    public StringProperty stuNumProperty() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum.set(stuNum);
    }

    public String getStuOutTime() {
        return stuOutTime.get();
    }

    public StringProperty stuOutTimeProperty() {
        return stuOutTime;
    }

    public void setStuOutTime(String stuOutTime) {
        this.stuOutTime.set(stuOutTime);
    }

    public boolean isIsOut() {
        return isOut.get();
    }

    public BooleanProperty isOutProperty() {
        return isOut;
    }

    public void setIsOut(boolean isOut) {
        this.isOut.set(isOut);
    }

    public String getStuInTime() {
        return stuInTime.get();
    }

    public StringProperty stuInTimeProperty() {
        return stuInTime;
    }

    public void setStuInTime(String stuInTime) {
        this.stuInTime.set(stuInTime);
    }

    public GraduateWrapper(StringProperty stuNum, StringProperty stuOutTime, BooleanProperty isOut, StringProperty stuInTime, StringProperty stuName) {
        this.stuNum = stuNum;
        this.stuOutTime = stuOutTime;
        this.isOut = isOut;
        this.stuInTime = stuInTime;
        this.stuName = stuName;
    }
}

