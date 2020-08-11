package com.tignioj.studentmanagement.entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class Admin extends RecursiveTreeObject<Admin> {
    private String uname;
    private String pwd;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Admin(String uname, String pwd) {
        this.uname = uname;
        this.pwd = pwd;
    }

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
