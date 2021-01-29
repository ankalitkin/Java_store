package ru.vsu.cs.course3.service;

import ru.vsu.cs.course3.dao.DepartmentDao;
import ru.vsu.cs.course3.dao.ItemDao;
import ru.vsu.cs.course3.domain.Department;
import ru.vsu.cs.course3.provider.ServiceProvider;

import java.util.List;

public class DepartmentService {
    private final DepartmentDao departmentDao = ServiceProvider.get(DepartmentDao.class);
    private final ItemDao itemDao = ServiceProvider.get(ItemDao.class);

    public List<Department> getAllDepartments() {
        return departmentDao.findAll();
    }

    public Department getById(int id) {
        return departmentDao.findById(id);
    }

    public void add(String name, String workingHours) {
        departmentDao.insert(new Department(name, workingHours));
    }

    public void update(Department department) {
        departmentDao.update(department);
    }

    public boolean deleteById(int id) {
        return itemDao.findAllByDepartmentId(id).isEmpty() && departmentDao.deleteById(id);
    }
}
