package com.tignioj.studentmanagement.backend.service.impl;

import com.tignioj.studentmanagement.backend.dao.impl.AdminDaoImpl;
import com.tignioj.studentmanagement.backend.service.BaseServiceImpl;
import com.tignioj.studentmanagement.entities.Admin;

public class AdminService extends BaseServiceImpl<Admin> {

    public AdminService() {
        this.baseDao = new AdminDaoImpl();
    }

    public Admin login(Admin admin) throws Exception {
        testNull(admin);


        Admin adminFromDb = get(admin.getUname());

        if (adminFromDb == null) {
            throw new Exception("用户不存在！");
        }

        if (!admin.getPwd().equals(adminFromDb.getPwd())) {
            throw new Exception("密码错误！");
        }
        return adminFromDb;
    }

    private void testNull(Admin admin) throws Exception {
        if (admin == null || admin.getUname() == null || admin.getPwd() == null) {
            throw new Exception("请输入用户名或密码");
        }
    }

    public Admin signIn(Admin admin) throws Exception {
        testNull(admin);

        Admin admin1 = get(admin.getUname());
        if (admin1 != null) {
            throw new Exception("用户已经存在！");
        }

        add(admin);
        return admin;
    }

    public void changePWD(Admin admin) throws Exception {
        testNull(admin);
        System.out.println(admin);
        Admin admin1 = get(admin.getUname());
        if (admin1 == null) {
            throw new Exception("用户不存在！");
        }
        if (admin.getPwd().equals(admin1.getPwd())) {
            throw new Exception("密码不能和原来的相同");
        }
        alter(admin);
    }
}
