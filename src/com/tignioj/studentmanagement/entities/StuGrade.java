package com.tignioj.studentmanagement.entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import java.util.Date;

public class StuGrade<T> extends RecursiveTreeObject<T> {
    private int id;
    private StuInfo stuInfo;
    private String stuYear;
    private double stuGrade;
    private int stuTerm;


    @Override
    public String toString() {
        return "StuGrade{" +
                "id=" + id +
                ", stuInfo=" + stuInfo +
                ", stuYear='" + stuYear + '\'' +
                ", stuGrade=" + stuGrade +
                ", stuTerm=" + stuTerm +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StuInfo getStuInfo() {
        return stuInfo;
    }

    public void setStuInfo(StuInfo stuInfo) {
        this.stuInfo = stuInfo;
    }

    public String getStuYear() {
        return stuYear;
    }

    public void setStuYear(String stuYear) {
        this.stuYear = stuYear;
    }

    public double getStuGrade() {
        return stuGrade;
    }

    public void setStuGrade(double stuGrade) {
        this.stuGrade = stuGrade;
    }

    public int getStuTerm() {
        return stuTerm;
    }

    public void setStuTerm(int stuTerm) {
        this.stuTerm = stuTerm;
    }

    public StuGrade() {
    }

    public StuGrade(int id, StuInfo stuInfo, String stuYear, double stuGrade, int stuTerm) {
        this.id = id;
        this.stuInfo = stuInfo;
        this.stuYear = stuYear;
        this.stuGrade = stuGrade;
        this.stuTerm = stuTerm;
    }
}
