package com.tignioj.studentmanagement.backend.dao.impl;

import com.tignioj.studentmanagement.backend.dao.AdminDao;
import com.tignioj.studentmanagement.backend.utils.JdbcUtils;
import com.tignioj.studentmanagement.entities.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    @Override
    public void delete(String id) {


    }

    @Override
    public void alter(Admin admin) {
        Connection connection = JdbcUtils.getConnection();
        try {
            String sql = "UPDATE admin SET pwd=? WHERE uname=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, admin.getPwd());
            preparedStatement.setString(2, admin.getUname());

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
    public void add(Admin admin) {
        Connection connection = JdbcUtils.getConnection();
        try {
            String sql = "INSERT INTO admin (" +
                    "uname , pwd) VALUES ( " +
                    "?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, admin.getUname());
            preparedStatement.setString(2, admin.getPwd());

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
    public Admin get(String id) {
        Connection connection = JdbcUtils.getConnection();
        try {
            String sql = "SELECT * FROM admin WHERE " +
                    "uname=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            Admin admin = null;
            while (resultSet.next()) {
                admin = new Admin(
                        resultSet.getString("uname"),
                        resultSet.getString("pwd")
                        );
            }
            return admin;
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
    public List<Admin> getAll() {
        return null;
    }

}
