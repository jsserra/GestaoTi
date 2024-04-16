package com.jp.dao;

import java.util.List;

public interface GenericDao<T>{
    public List<T> getAll();
    public T findById(Integer id);
    public boolean persist(T object);
    public boolean merge(T object);
    public void rollback();
    public boolean remove(T object);

}
