package com.tignioj.studentmanagement.gui.view.stumanage.list;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.tignioj.studentmanagement.entities.StuInfo;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class StuInfoWrapper extends RecursiveTreeObject<StuInfoWrapper> implements Comparable<StuInfoWrapper> {
//    private StringProperty avatar = new SimpleStringProperty(); //头像
    private SimpleObjectProperty<ImageView> avatar = new SimpleObjectProperty<ImageView>(); //头像
    private StringProperty stuNum = new SimpleStringProperty();
    private StringProperty stuIdent = new SimpleStringProperty(); //身份证
    private StringProperty stuName = new SimpleStringProperty();
    private IntegerProperty stuAge = new SimpleIntegerProperty();
    private StringProperty stuSex = new SimpleStringProperty();
    private StringProperty stuClass = new SimpleStringProperty(); //班级
    private StringProperty stuBir = new SimpleStringProperty();
    private StringProperty stuInTime = new SimpleStringProperty(); //入学时间
    private StringProperty stuMajor = new SimpleStringProperty();
    private StringProperty stuPolitics = new SimpleStringProperty(); //政治面貌
    private StringProperty stuNation = new SimpleStringProperty();
    private StringProperty stuAddr = new SimpleStringProperty();
    private StringProperty stuStatus = new SimpleStringProperty(); //学


    public void updateStuInfoWrapper(StuInfoWrapper stuInfo) {
        try {
            StuInfo newStuInfo = new StuInfo(
                    stuInfo.getAvatar().getId(),
                    stuInfo.getStuNum(),
                    stuInfo.getStuIdent(),
                    stuInfo.getStuName(),
                    stuInfo.getStuAge(),
                    stuInfo.getStuSex(),
                    stuInfo.getStuClass(),
                    stuInfo.sdf.parse(getStuBir()),
                    stuInfo.sdf.parse(getStuInTime()),
                    stuInfo.getStuMajor(),
                    stuInfo.getStuPolitics(),
                    stuInfo.getStuNation(),
                    stuInfo.getStuAddr(),
                    stuInfo.getStuStatus()
            );
            this.stuInfo = newStuInfo;
            wrapStuInfo(newStuInfo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将传入的stuInfo 包装起来
     * @param stuInfo
     */
    public void wrapStuInfo(StuInfo stuInfo) {
        ImageView imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(stuInfo.getAvatar())));
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setId(stuInfo.getAvatar());


        setAvatar(imageView);
        setStuNum(stuInfo.getStuNum());
        setStuIdent(stuInfo.getStuIdent());
        setStuName(stuInfo.getStuName());
        setStuAge(stuInfo.getStuAge());
        setStuSex(stuInfo.getStuSex());
        setStuClass(stuInfo.getStuClass());
        setStuBir(sdf.format(stuInfo.getStuBir()));
        setStuInTime(sdf.format(stuInfo.getStuInTime()));
        setStuMajor(stuInfo.getStuMajor());
        setStuPolitics(stuInfo.getStuPolitics());
        setStuAddr(stuInfo.getStuAddr());
        setStuStatus(stuInfo.getStuStatus());
        this.stuInfo = stuInfo;
    }



    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

    private StuInfo stuInfo;

    public StuInfoWrapper(StuInfo stuInfo) {
        wrapStuInfo(stuInfo);
    }

    @Override
    public String toString() {
        return "StuInfoWrapper{" +
                "avatar=" + avatar +
                ", stuNum=" + stuNum +
                ", stuIdent=" + stuIdent +
                ", stuName=" + stuName +
                ", stuAge=" + stuAge +
                ", stuSex=" + stuSex +
                ", stuClass=" + stuClass +
                ", stuBir=" + stuBir +
                ", stuInTime=" + stuInTime +
                ", stuMajor=" + stuMajor +
                ", stuPolitics=" + stuPolitics +
                ", stuNation=" + stuNation +
                ", stuAddr=" + stuAddr +
                ", stuStatus=" + stuStatus +
                ", sdf=" + sdf +
                '}';
    }


    public StuInfo getStuInfo() {
        return this.stuInfo;
    }

    public SimpleObjectProperty<ImageView> avatarProperty() {
        return avatar;
    }

    public ImageView getAvatar() {
        return avatar.get();
    }

    public void setAvatar(ImageView avatar) {
        this.avatar.set(avatar);
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

    public String getStuIdent() {
        return stuIdent.get();
    }

    public StringProperty stuIdentProperty() {
        return stuIdent;
    }

    public void setStuIdent(String stuIdent) {
        this.stuIdent.set(stuIdent);
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

    public int getStuAge() {
        return stuAge.get();
    }

    public IntegerProperty stuAgeProperty() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge.set(stuAge);
    }

    public String getStuSex() {
        return stuSex.get();
    }

    public StringProperty stuSexProperty() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex.set(stuSex);
    }

    public String getStuClass() {
        return stuClass.get();
    }

    public StringProperty stuClassProperty() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass.set(stuClass);
    }

    public String getStuBir() {
        return stuBir.get();
    }

    public StringProperty stuBirProperty() {
        return stuBir;
    }

    public void setStuBir(String stuBir) {
        this.stuBir.set(stuBir);
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

    public String getStuMajor() {
        return stuMajor.get();
    }

    public StringProperty stuMajorProperty() {
        return stuMajor;
    }

    public void setStuMajor(String stuMajor) {
        this.stuMajor.set(stuMajor);
    }

    public String getStuPolitics() {
        return stuPolitics.get();
    }

    public StringProperty stuPoliticsProperty() {
        return stuPolitics;
    }

    public void setStuPolitics(String stuPolitics) {
        this.stuPolitics.set(stuPolitics);
    }

    public String getStuNation() {
        return stuNation.get();
    }

    public StringProperty stuNationProperty() {
        return stuNation;
    }

    public void setStuNation(String stuNation) {
        this.stuNation.set(stuNation);
    }

    public String getStuAddr() {
        return stuAddr.get();
    }

    public StringProperty stuAddrProperty() {
        return stuAddr;
    }

    public void setStuAddr(String stuAddr) {
        this.stuAddr.set(stuAddr);
    }

    public String getStuStatus() {
        return stuStatus.get();
    }

    public StringProperty stuStatusProperty() {
        return stuStatus;
    }

    public void setStuStatus(String stuStatus) {
        this.stuStatus.set(stuStatus);
    }

    public StuInfoWrapper() {
    }

    public StuInfoWrapper(SimpleObjectProperty<ImageView> avatar, StringProperty stuNum, StringProperty stuIdent, StringProperty stuName, IntegerProperty stuAge, StringProperty stuSex, StringProperty stuClass, StringProperty stuBir, StringProperty stuInTime, StringProperty stuMajor, StringProperty stuPolitics, StringProperty stuNation, StringProperty stuAddr, StringProperty stuStatus) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StuInfoWrapper that = (StuInfoWrapper) o;
        return stuNum.getValue().equals(that.stuNum.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuNum.getValue());
    }

    @Override
    public int compareTo(StuInfoWrapper o) {
        return o.getStuNum().compareTo(getStuNum());
    }
}
