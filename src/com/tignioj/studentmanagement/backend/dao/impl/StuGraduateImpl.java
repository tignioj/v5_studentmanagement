package com.tignioj.studentmanagement.backend.dao.impl;

import com.tignioj.studentmanagement.backend.dao.StuGraduateDao;
import com.tignioj.studentmanagement.backend.dao.StuInfoDao;
import com.tignioj.studentmanagement.backend.utils.JdbcUtils;
import com.tignioj.studentmanagement.entities.Admin;
import com.tignioj.studentmanagement.entities.StuGraduate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StuGraduateImpl  implements StuGraduateDao {


    @Override
    public void delete(String id) throws Exception {

    }

    @Override
    public void alter(StuGraduate stuGraduate) throws Exception {

    }

    @Override
    public void add(StuGraduate stuGraduate) throws Exception {
        Connection connection = JdbcUtils.getConnection();
        try {
            String sql = "INSERT INTO stu_graduate (" +
                    "stu_num , stu_outtime) VALUES ( " +
                    "?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, stuGraduate.getStuInfo().getStuNum());
            preparedStatement.setTimestamp(2, new Timestamp(stuGraduate.getStuOutTime().getTime()));

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

    StuInfoDao  stuInfoDao = new StuInfoDaoImpl();

    @Override
    public StuGraduate get(String id) throws Exception {
        Connection connection = JdbcUtils.getConnection();
        try {
            String sql = "SELECT * FROM stu_graduate WHERE " +
                    "stu_num=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            StuGraduate stuGraduate = null;
            while (resultSet.next()) {
                stuGraduate = new StuGraduate(
                        stuInfoDao.get(resultSet.getString("stu_num")),
                        resultSet.getTimestamp("stu_outtime")
                );
            }
            return stuGraduate;
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
        return null;
    }

    @Override
    public void delete(int id) throws Exception {

    }

    @Override
    public List<StuGraduate> getAll() throws Exception {
        Connection connection = JdbcUtils.getConnection();
        try {
            String sql = "SELECT * FROM stu_graduate";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<StuGraduate> stuGraduates = new ArrayList<>();
            while (resultSet.next()) {
                StuGraduate stuGraduate = new StuGraduate(
                        stuInfoDao.get(resultSet.getString("stu_num")),
                        resultSet.getTimestamp("stu_outtime")
                );
                stuGraduates.add(stuGraduate);
            }
            return stuGraduates;
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
        return null;
    }
}
