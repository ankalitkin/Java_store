package ru.vsu.cs.course3.service;

import ru.vsu.cs.course3.dao.DepartmentDao;
import ru.vsu.cs.course3.domain.Department;
import ru.vsu.cs.course3.provider.ServiceProvider;

import java.util.List;

public class DepartmentService {
    private DepartmentDao departmentDao;

    public DepartmentService() {
        departmentDao = ServiceProvider.get(DepartmentDao.class);
    }

    public List<Department> getAllDepartments() {
        return departmentDao.findAll();
    }

    public Department getById(int id) {
        return departmentDao.findById(id);
    }

    public Department add(String name, String workingHours) {
        return departmentDao.save(new Department(name, workingHours));
    }

    public Department save(Department department) {
        return departmentDao.save(department);
    }

    public boolean deleteById(int id) {
        return departmentDao.deleteById(id);
    }
}
