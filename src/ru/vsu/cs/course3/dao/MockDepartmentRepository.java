package ru.vsu.cs.course3.dao;

import ru.vsu.cs.course3.domain.Department;
import ru.vsu.cs.course3.provider.MockDatabaseProvider;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MockDepartmentRepository implements DepartmentDao {
    private Map<Integer, Department> getTable() {
        return MockDatabaseProvider.get(Department.class);
    }

    private int getNextId() {
        return MockDatabaseProvider.getNextId(Department.class);
    }

    @Override
    public Department findById(int id) {
        Department department = getTable().get(id);
        if(department == null)
            return null;
        return new Department(department);
    }

    @Override
    public List<Department> findAll() {
        return getTable().values().stream().map(Department::new).collect(Collectors.toList());
    }

    @Override
    public Department save(Department entity) {
        if(entity == null)
            throw new IllegalArgumentException();
        entity = new Department(entity);
        if(entity.getId() == 0)
            entity.setId(getNextId());
        getTable().put(entity.getId(), entity);
        return new Department(entity);
    }

    @Override
    public boolean deleteById(int id) {
        return getTable().remove(id) != null;
    }
}
