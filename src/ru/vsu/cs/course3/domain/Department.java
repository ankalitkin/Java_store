package ru.vsu.cs.course3.domain;

public class Department {
    private int id;
    private String name;
    private String workingHours;

    public Department(String name, String workingHours) {
        this.name = name;
        this.workingHours = workingHours;
    }

    public Department(Department department) {
        this.id = department.id;
        this.name = department.name;
        this.workingHours = department.workingHours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }
}
