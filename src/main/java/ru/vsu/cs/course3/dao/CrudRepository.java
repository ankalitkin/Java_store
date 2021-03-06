package ru.vsu.cs.course3.dao;

import java.util.List;

public interface CrudRepository<T> {
    T findById(int id);

    List<T> findAll();

    T insert(T entity);

    void update(T entity);

    boolean deleteById(int id);
}
