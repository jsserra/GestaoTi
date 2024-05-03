package com.jp.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T> extends Serializable {
    public List<T> getAll();
    public T findById(Integer id);
    public void persist(T object) throws Exception;
    public void merge(T object) throws Exception;
    public void remove(T object) throws Exception;

}
