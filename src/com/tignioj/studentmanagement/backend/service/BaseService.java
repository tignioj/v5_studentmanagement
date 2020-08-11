package com.tignioj.studentmanagement.backend.service;

import com.tignioj.studentmanagement.backend.dao.BaseDao;

import java.util.List;

public interface BaseService<T> {
    BaseDao baseDao = null;
    void add(T t) throws Exception;
    void alter(T t) throws Exception;
    void delete(String id) throws Exception;
    void delete(int id) throws Exception;
    T get(String id) throws Exception;
    List<T> getAll() throws Exception;
}
