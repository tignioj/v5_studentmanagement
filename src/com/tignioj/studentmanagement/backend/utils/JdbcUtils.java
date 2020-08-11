package com.tignioj.studentmanagement.backend.utils;

import com.tignioj.studentmanagement.gui.msg.AlertBox;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    static String driverClassName;
    static String url;
    static String mysqlUsername;
    static String mysqlPassword;

    static {
        try {
            setupDBProperties("com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/studentmanagement",
                    "root",
                    "000000"
            );

            if (driverClassName == null) {
                throw new Exception("未配置driverClassName");
            }
            if (url == null) {
                throw new Exception("未配置url");
            }
            if (mysqlPassword == null) {
                throw new Exception("未配置数据库mysqlPassword");
            }
            if (mysqlUsername == null) {
                throw new Exception("未配置mysqlUsername");
            }

            System.out.println("====数据库参数====");
            System.out.println(driverClassName);
            System.out.println(url);
            System.out.println(mysqlUsername + ":" + mysqlPassword);
            System.out.println("================");
        } catch (Exception e) {
            AlertBox.display("警告", e.getMessage());
            System.exit(1);
        }
    }

    public static Properties dbProperties;

    public static synchronized Properties updateDBProperties (String driverclassName, String dbUrl, String usr, String pwd) {
        dbProperties = new Properties();
        File f = new File("mysqlconfig.properties");
        InputStream in = null;
        try {
            if (!f.exists()) {
                f.createNewFile();
            } else {
                in = new FileInputStream(f);
                dbProperties.load(in);
            }

            FileOutputStream fos = new FileOutputStream(f);
            if (driverclassName == null) {
                driverclassName ="com.mysql.jdbc.Driver";
            }
            dbProperties.setProperty("driverClassName", driverclassName);
            dbProperties.setProperty("url", dbUrl);
            dbProperties.setProperty("mysqlUsername", usr);
            dbProperties.setProperty("mysqlPassword", pwd);
            dbProperties.store(fos, "mysql database configure file, DO NOT DELETE IT!");


            driverClassName = driverclassName;
            url = dbUrl;
            mysqlUsername = usr;
            mysqlPassword = pwd;

        } catch (Exception e) {
            AlertBox.display("警告", e.getMessage());
            System.exit(1);
        }
        return dbProperties;
    }


    public static synchronized Properties setupDBProperties(String driverclassName, String dbUrl, String usr, String pwd) {
        dbProperties = new Properties();
        File f = new File("mysqlconfig.properties");
        InputStream in = null;
        try {
            if (!f.exists()) {
                f.createNewFile();
                FileOutputStream fos = new FileOutputStream(f);
                if (driverclassName == null) {
                    driverclassName ="com.mysql.jdbc.Driver";
                }
                dbProperties.setProperty("driverClassName", driverclassName);
                dbProperties.setProperty("url", dbUrl);
                dbProperties.setProperty("mysqlUsername", usr);
                dbProperties.setProperty("mysqlPassword", pwd);
                dbProperties.store(fos, "mysql database configure file, DO NOT DELETE IT!");
            } else {
                in = new FileInputStream(f);
                dbProperties.load(in);
            }

            driverClassName = driverclassName;
            url = dbUrl;
            mysqlUsername = usr;
            mysqlPassword = pwd;

        } catch (Exception e) {
            AlertBox.display("警告", e.getMessage());
            System.exit(1);
        }
        return dbProperties;
    }


    //获取连接
    public static Connection getConnection() {

        try {
            /**
             * 一、得到Connection
             * 二、得到Statement
             * 三、得到ResultSet
             * 四、rs.next()返回的是什么，我们就返回什么（true说说明有记录）
             */
            /**
             * 准备四大参数
             */
            //准备四大参数
//        driverClassName = "com.mysql.jdbc.Driver";
//        url = "jdbc:mysql://localhost:3306/studentmanagement";
//        mysqlUsername = "root";
//        mysqlPassword = "000000";
            //加载驱动类
//            Class.forName(driverClassName);

            //得到Connectioin
            Connection connection = DriverManager.getConnection(url, mysqlUsername, mysqlPassword);

            return connection;
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
        } catch (SQLException e) {
//            e.printStackTrace();
//            AlertBox.display("错误", e.getMessage(), AlertBox.MIDSIZE, false);
        }
        return null;
    }


    public static void testConnection(String dburl, String user, String pwd) throws Exception {
        try {
            Connection connection = DriverManager.getConnection(dburl,user,pwd);
            if (connection == null) {
                throw new Exception("数据库连接失败！！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("数据库连接失败！！");
        }
    }
}
