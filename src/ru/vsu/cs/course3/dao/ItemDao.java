package ru.vsu.cs.course3.dao;

import ru.vsu.cs.course3.domain.Department;
import ru.vsu.cs.course3.domain.Item;

import java.util.List;

public interface ItemDao extends CrudRepository<Item>{

    List<Item> findAllByDepartmentId(int departmentId);

    void deleteByDepartmentId(int departmentId);
}
