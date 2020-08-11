package com.tignioj.studentmanagement.backend.dao.impl;

import com.tignioj.studentmanagement.backend.dao.StuGradeDao;
import com.tignioj.studentmanagement.backend.service.impl.StuInfoService;
import com.tignioj.studentmanagement.backend.utils.JdbcUtils;
import com.tignioj.studentmanagement.entities.StuGrade;
import com.tignioj.studentmanagement.entities.view.StuGradeViewEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StuGradeDaoImpl implements StuGradeDao {
    @Override
    public void delete(String id) throws Exception {
    }



    @Override
    public void alter(StuGrade stuGrade) throws Exception {
        Connection connection = JdbcUtils.getConnection();
        try {
            String sql = "UPDATE stu_grade SET " +
                    "stu_num=?, stu_year=?, stu_grade=?, stu_term=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, stuGrade.getStuInfo().getStuNum());
            preparedStatement.setString(2, stuGrade.getStuYear());
            preparedStatement.setDouble(3, stuGrade.getStuGrade());
            preparedStatement.setInt(4, stuGrade.getStuTerm());
            preparedStatement.setInt(5, stuGrade.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void add(StuGrade stuGrade) throws Exception {

        Connection connection = JdbcUtils.getConnection();
        try {
            String sql = "INSERT INTO stu_grade (" +
                    "stu_num, stu_year, stu_grade, stu_term ) VALUES ( " +
                    "?, ?,?,? )";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, stuGrade.getStuInfo().getStuNum());
            preparedStatement.setString(2, stuGrade.getStuYear());
            preparedStatement.setDouble(3, stuGrade.getStuGrade());
            preparedStatement.setInt(4, stuGrade.getStuTerm());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public StuGrade get(String id) {

        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "SELECT * FROM stu_grade WHERE id=?";
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<StuGrade> stuGrades = getStuGrades(resultSet);
            if (stuGrades.size() == 0) {
                return null;
            }
            return stuGrades.get(0);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    static StuInfoService stuInfoService = new StuInfoService();

    @Override
    public List<StuGrade> getAll() throws Exception {
        Connection connection = JdbcUtils.getConnection();

        String sql = "SELECT * FROM stu_grade";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getStuGrades(resultSet);
    }

    private List<StuGrade> getStuGrades(ResultSet resultSet) throws Exception {
        ArrayList<StuGrade> list = new ArrayList<>();
        while (resultSet.next()) {
            StuGrade stuGrade = new StuGrade(
                    resultSet.getInt("id"),
                    stuInfoService.get(resultSet.getString("stu_num")),
                    resultSet.getString("stu_year"),
                    resultSet.getDouble("stu_grade"),
                    resultSet.getInt("stu_term")
            );
            list.add(stuGrade);
        }
        return list;
    }

    @Override
    public void delete(int id) throws Exception {
        Connection connection = JdbcUtils.getConnection();
        String sql = "DELETE FROM stu_grade WHERE id= ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<StuGrade> getGradesByStuNum(String stuNum) throws Exception {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "SELECT * FROM stu_grade WHERE stu_num=?";
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, stuNum);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<StuGrade> stuGrades = getStuGrades(resultSet);
            if (stuGrades.size() == 0) {
                return null;
            }
            return stuGrades;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    @Override
    public List<StuGradeViewEntity> getGradeEntityViewByStuNum(String stuNum) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();

//            String sql = "SELECT  g1.stu_year grade_year, g1.stu_grade term1, g2.stu_grade term2 " +
//                    "FROM stu_grade g1 " +
//                    "INNER JOIN stu_grade g2" +
//                    "ON g1.stu_num = g2.stu_num" +
//                    "AND g1.stu_term <> g2.stu_term" +
//                    "AND g1.stu_year = g2.stu_year" +
//                    "WHERE g1.stu_num = ?" +
//                    "GROUP BY g1.stu_year";
            String sql = "SELECT grade_year, term1, term2 FROM v_stu_grade WHERE stu_num=? GROUP BY grade_year";

            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, stuNum);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<StuGradeViewEntity> stuGradeViewEntities = new ArrayList<>();

            while (resultSet.next()) {
                StuGradeViewEntity stuGradeViewEntity = new StuGradeViewEntity(
                        resultSet.getString("grade_year"),
                        resultSet.getDouble("term1"),
                        resultSet.getDouble("term2")
                );
                stuGradeViewEntities.add(stuGradeViewEntity);
            }

            if (stuGradeViewEntities.size() == 0) {
                return null;
            }
            return stuGradeViewEntities;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    @Override
    public StuGrade getGradeByGradeYearAndTermAndStuNum(StuGrade stuGrade) throws Exception{
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "SELECT * FROM stu_grade WHERE stu_year=? AND stu_term=? AND stu_num=?";
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, stuGrade.getStuYear());
            preparedStatement.setInt(2, stuGrade.getStuTerm());
            preparedStatement.setString(3, stuGrade.getStuInfo().getStuNum());

            ResultSet resultSet = preparedStatement.executeQuery();
            List<StuGrade> stuGrades = getStuGrades(resultSet);
            if (stuGrades.size() == 0) {
                return null;
            }
            return stuGrades.get(0);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
