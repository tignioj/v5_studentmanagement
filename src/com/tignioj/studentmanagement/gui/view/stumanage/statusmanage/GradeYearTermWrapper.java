package com.tignioj.studentmanagement.gui.view.stumanage.statusmanage;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.tignioj.studentmanagement.entities.StuGrade;
import com.tignioj.studentmanagement.entities.view.StuGradeViewEntity;
import javafx.beans.property.*;

public class GradeYearTermWrapper extends RecursiveTreeObject<GradeYearTermWrapper> {
    private StringProperty year = new SimpleStringProperty();
    private DoubleProperty term1 = new SimpleDoubleProperty();
    private DoubleProperty term2 = new SimpleDoubleProperty();

    public GradeYearTermWrapper(StuGradeViewEntity stuGradeViewEntity) {
        setYear(stuGradeViewEntity.getYear());
        setTerm1(stuGradeViewEntity.getTerm1());
        setTerm2(stuGradeViewEntity.getTerm2());
    }

    public String getYear() {
        return year.get();
    }

    public StringProperty yearProperty() {
        return year;
    }

    public void setYear(String year) {
        this.year.set(year);
    }

    public double getTerm1() {
        return term1.get();
    }

    public DoubleProperty term1Property() {
        return term1;
    }

    public void setTerm1(double term1) {
        this.term1.set(term1);
    }

    public double getTerm2() {
        return term2.get();
    }

    public DoubleProperty term2Property() {
        return term2;
    }

    public void setTerm2(double term2) {
        this.term2.set(term2);
    }

    @Override
    public String toString() {
        return "GradeYearTermWrapper{" +
                "year=" + year +
                ", term1=" + term1 +
                ", term2=" + term2 +
                '}';
    }

    public GradeYearTermWrapper() {
    }

    public GradeYearTermWrapper(StringProperty year, DoubleProperty term1, DoubleProperty term2) {
        this.year = year;
        this.term1 = term1;
        this.term2 = term2;
    }
}

