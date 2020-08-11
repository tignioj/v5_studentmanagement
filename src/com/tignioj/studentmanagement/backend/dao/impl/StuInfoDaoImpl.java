package com.tignioj.studentmanagement.backend.dao.impl;

import com.tignioj.studentmanagement.backend.dao.StuInfoDao;
import com.tignioj.studentmanagement.backend.utils.JdbcUtils;
import com.tignioj.studentmanagement.entities.StuInfo;
import org.junit.Test;
import org.w3c.dom.NodeList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StuInfoDaoImpl implements StuInfoDao {
    @Override
    public void delete(String id) {
            Connection connection = JdbcUtils.getConnection();
            String sql = "DELETE FROM stu_info WHERE stu_num = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
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
    public void alter(StuInfo stuInfo) {
        Connection connection = JdbcUtils.getConnection();
        try {
            String sql =
                    "UPDATE stu_info SET " +
                            " stu_ident=?, stu_name=?, stu_age=?, stu_sex=?, " +
                            " stu_class=?, stu_bir=?, stu_intime=?, stu_major=?, stu_politics=?, " +
                            " stu_nation=?, stu_addr=?, stu_status=?, stu_avatar=? " +
                            "WHERE stu_num=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, stuInfo.getStuIdent());
            preparedStatement.setString(2, stuInfo.getStuName());
            preparedStatement.setInt(3, stuInfo.getStuAge());
            preparedStatement.setString(4, stuInfo.getStuSex());

            preparedStatement.setString(5, stuInfo.getStuClass());
            preparedStatement.setDate(6, new Date(stuInfo.getStuBir().getTime()));
            preparedStatement.setDate(7, new Date(stuInfo.getStuInTime().getTime()));
            preparedStatement.setString(8, stuInfo.getStuMajor());
            preparedStatement.setString(9, stuInfo.getStuPolitics());

            preparedStatement.setString(10, stuInfo.getStuNation());
            preparedStatement.setString(11, stuInfo.getStuAddr());
            preparedStatement.setString(12, stuInfo.getStuStatus());
            preparedStatement.setString(13, stuInfo.getAvatar());
            preparedStatement.setString(14, stuInfo.getStuNum());

            preparedStatement.executeUpdate();

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
    public void add(StuInfo stuInfo) {

        Connection connection = JdbcUtils.getConnection();
        try {
            String sql =
                    "INSERT INTO stu_info " +
                            "(stu_num, stu_ident, stu_name, stu_age, stu_sex," +
                            " stu_class, stu_bir, stu_intime, stu_major, stu_politics, " +
                            "stu_nation, stu_addr, stu_status, stu_avatar) " +
                            "VALUES(?,?,?,?,?," +
                            "?,?,?,?,?," +
                            "?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, stuInfo.getStuNum());
            preparedStatement.setString(2, stuInfo.getStuIdent());
            preparedStatement.setString(3, stuInfo.getStuName());
            preparedStatement.setInt(4, stuInfo.getStuAge());
            preparedStatement.setString(5, stuInfo.getStuSex());

            preparedStatement.setString(6, stuInfo.getStuClass());
            preparedStatement.setDate(7, new Date(stuInfo.getStuBir().getTime()));
            preparedStatement.setDate(8, new Date(stuInfo.getStuInTime().getTime()));
            preparedStatement.setString(9, stuInfo.getStuMajor());
            preparedStatement.setString(10, stuInfo.getStuPolitics());

            preparedStatement.setString(11, stuInfo.getStuNation());
            preparedStatement.setString(12, stuInfo.getStuAddr());
            preparedStatement.setString(13, stuInfo.getStuStatus());
            preparedStatement.setString(14, stuInfo.getAvatar());


            preparedStatement.executeUpdate();


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
    public StuInfo get(String id) throws Exception {
        Connection connection = JdbcUtils.getConnection();
        String sql = "SELECT * FROM stu_info WHERE stu_num = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<StuInfo> stuInfo = getStuInfo(resultSet);
            if (stuInfo.size() == 0) {
                return null;
            }
            return stuInfo.get(0);
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
        return null;
    }

    @Override
    public void delete(int id) throws Exception {

    }

    private ArrayList<StuInfo> getStuInfo(ResultSet resultSet) throws SQLException {
        ArrayList<StuInfo> stuInfos = new ArrayList<>();
        while (resultSet.next()) {
            String stu_num = resultSet.getString("stu_num");
            String stu_ident = resultSet.getString("stu_ident");
            String stu_name = resultSet.getString("stu_name");
            int stu_age = resultSet.getInt("stu_age");
            String stu_sex = resultSet.getString("stu_sex");

            String stu_class = resultSet.getString("stu_class");
            Date stu_bir = resultSet.getDate("stu_bir");
            Date stu_intime = resultSet.getDate("stu_intime");
            String stu_major = resultSet.getString("stu_major");
            String stu_politics = resultSet.getString("stu_politics");

            String stu_nation = resultSet.getString("stu_nation");
            String stu_addr = resultSet.getString("stu_addr");
            String stu_status = resultSet.getString("stu_status");
            String stu_avatar = resultSet.getString("stu_avatar");

            StuInfo stuInfo = new StuInfo(
                    stu_avatar,
                    stu_num,
                    stu_ident,
                    stu_name,
                    stu_age,
                    stu_sex,
                    stu_class,
                    stu_bir,
                    stu_intime,
                    stu_major,
                    stu_politics,
                    stu_nation,
                    stu_addr,
                    stu_status
            );
            stuInfos.add(stuInfo);
        }
        return stuInfos;
    }


    @Override
    public List<StuInfo> getAll() {
        Connection connection = JdbcUtils.getConnection();
        String sql = "SELECT * FROM stu_info";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getStuInfo(resultSet);
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
        return null;
    }
}
