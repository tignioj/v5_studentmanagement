package com.tignioj.studentmanagement.backend.dao;

import java.util.List;

public interface BaseDao<T> {
    void delete(String id) throws Exception;

    void alter(T t) throws Exception;

    void add(T t) throws Exception;

    T get(String id) throws Exception;

    void delete(int id) throws Exception;
    List<T> getAll() throws Exception;
}
