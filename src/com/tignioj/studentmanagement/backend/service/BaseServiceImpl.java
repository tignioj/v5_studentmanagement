package com.tignioj.studentmanagement.backend.service;

import com.tignioj.studentmanagement.backend.dao.BaseDao;

import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {
    public BaseDao<T> baseDao;


    @Override
    public void add(T t) throws Exception {
        baseDao.add(t);
    }

    @Override
    public void alter(T t) throws Exception {
        baseDao.alter(t);
    }

    @Override
    public void delete(String id) throws Exception {
        baseDao.delete(id);
    }

    @Override
    public void delete(int id) throws Exception {
        baseDao.delete(id);
    }


    @Override
    public T get(String id) throws Exception {
        return (T) baseDao.get(id);
    }

    @Override
    public List<T> getAll() throws Exception {
        return baseDao.getAll();
    }
}
