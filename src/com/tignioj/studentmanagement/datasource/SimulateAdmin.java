package com.tignioj.studentmanagement.datasource;

import com.tignioj.studentmanagement.entities.Admin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SimulateAdmin {

    public static ObservableList<Admin> getAdmins() {
        ObservableList<Admin> admins = FXCollections.observableArrayList();
        for (int i = 0; i < 100; i++) {
            admins.add(new Admin("张三" + i, "pwd" + i));
        }
        return admins;
    }
}
