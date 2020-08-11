package com.tignioj.studentmanagement.entities.view;

/**
 * 视图
 *
 *
 原始sql
 SELECT g1.stu_num stu_num, g1.stu_year grade_year, g1.stu_grade term1, g2.stu_grade term2
 FROM stu_grade g1
 INNER JOIN stu_grade g2
 ON g1.stu_num = g2.stu_num
 AND g1.stu_term <> g2.stu_term
 AND g1.stu_year = g2.stu_year
 WHERE g1.stu_num = "3021376509"
 GROUP BY g1.stu_year



 /为了简化查询语句，创建视图
 CREATE VIEW v_stu_grade
 AS
 SELECT g1.stu_num stu_num, g1.stu_year grade_year, g1.stu_grade term1, g2.stu_grade term2
 FROM stu_grade g1
 INNER JOIN stu_grade g2
 ON g1.stu_num = g2.stu_num
 AND g1.stu_term <> g2.stu_term
 AND g1.stu_year = g2.stu_year

简化后，查询视图语句
 SELECT * FROM v_stu_grade
 WHERE stu_num = "3021376509"
 GROUP BY grade_year


 */
public class StuGradeViewEntity {
    private String year;
    private double term1;
    private double term2;

    @Override
    public String toString() {
        return "StuGradeViewEntity{" +
                "year='" + year + '\'' +
                ", term1=" + term1 +
                ", term2=" + term2 +
                '}';
    }

    public StuGradeViewEntity() {
    }

    public StuGradeViewEntity(String year, double term1, double term2) {
        this.year = year;
        this.term1 = term1;
        this.term2 = term2;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getTerm1() {
        return term1;
    }

    public void setTerm1(double term1) {
        this.term1 = term1;
    }

    public double getTerm2() {
        return term2;
    }

    public void setTerm2(double term2) {
        this.term2 = term2;
    }
}
