package ru.vsu.cs.course3.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Department {
    private int id;
    private String name;
    private String workingHours;

    public Department(Department department) {
        this(department.id, department.name, department.workingHours);
    }

    public Department(String name, String workingHours) {
        this.name = name;
        this.workingHours = workingHours;
    }
}
