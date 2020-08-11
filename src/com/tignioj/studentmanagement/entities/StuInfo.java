package com.tignioj.studentmanagement.entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.scene.image.ImageView;

import java.util.Date;

public class StuInfo extends RecursiveTreeObject<StuInfo> implements Comparable<StuInfo>{

    private String avatar; //头像
    private String stuNum;
    private String stuIdent; //身份证
    private String stuName;
    private int stuAge;
    private String stuSex;
    private String stuClass; //班级
    private Date stuBir;
    private Date stuInTime; //入学时间
    private String stuMajor;
    private String stuPolitics; //政治面貌
    private String stuNation;
    private String stuAddr;
    private String stuStatus; //学


    @Override
    public String toString() {
        return "StuInfo{" +
                "avatar='" + avatar + '\'' +
                ", stuNum='" + stuNum + '\'' +
                ", stuIdent='" + stuIdent + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuAge=" + stuAge +
                ", stuSex='" + stuSex + '\'' +
                ", stuClass='" + stuClass + '\'' +
                ", stuBir=" + stuBir +
                ", stuInTime=" + stuInTime +
                ", stuMajor='" + stuMajor + '\'' +
                ", stuPolitics='" + stuPolitics + '\'' +
                ", stuNation='" + stuNation + '\'' +
                ", stuAddr='" + stuAddr + '\'' +
                ", stuStatus='" + stuStatus + '\'' +
                '}';
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuIdent() {
        return stuIdent;
    }

    public void setStuIdent(String stuIdent) {
        this.stuIdent = stuIdent;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getStuAge() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public Date getStuBir() {
        return stuBir;
    }

    public void setStuBir(Date stuBir) {
        this.stuBir = stuBir;
    }

    public Date getStuInTime() {
        return stuInTime;
    }

    public void setStuInTime(Date stuInTime) {
        this.stuInTime = stuInTime;
    }

    public String getStuMajor() {
        return stuMajor;
    }

    public void setStuMajor(String stuMajor) {
        this.stuMajor = stuMajor;
    }

    public String getStuPolitics() {
        return stuPolitics;
    }

    public void setStuPolitics(String stuPolitics) {
        this.stuPolitics = stuPolitics;
    }

    public String getStuNation() {
        return stuNation;
    }

    public void setStuNation(String stuNation) {
        this.stuNation = stuNation;
    }

    public String getStuAddr() {
        return stuAddr;
    }

    public void setStuAddr(String stuAddr) {
        this.stuAddr = stuAddr;
    }

    public String getStuStatus() {
        return stuStatus;
    }

    public void setStuStatus(String stuStatus) {
        this.stuStatus = stuStatus;
    }

    public StuInfo() {
    }

    public StuInfo(String avatar, String stuNum, String stuIdent, String stuName, int stuAge, String stuSex, String stuClass, Date stuBir, Date stuInTime, String stuMajor, String stuPolitics, String stuNation, String stuAddr, String stuStatus) {
        this.avatar = avatar;
        this.stuNum = stuNum;
        this.stuIdent = stuIdent;
        this.stuName = stuName;
        this.stuAge = stuAge;
        this.stuSex = stuSex;
        this.stuClass = stuClass;
        this.stuBir = stuBir;
        this.stuInTime = stuInTime;
        this.stuMajor = stuMajor;
        this.stuPolitics = stuPolitics;
        this.stuNation = stuNation;
        this.stuAddr = stuAddr;
        this.stuStatus = stuStatus;
    }

    @Override
    public int compareTo(StuInfo o) {
        return getStuNum().compareTo(o.getStuNum());
    }
}
