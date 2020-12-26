package ru.vsu.cs.course3.domain;

import java.math.BigDecimal;

public class Item {
    private int id;
    private String name;
    private BigDecimal cost;
    private int departmentId;

    public Item(String name, BigDecimal cost, int departmentId) {
        this.name = name;
        this.cost = cost;
        this.departmentId = departmentId;
    }

    public Item(Item item) {
        this.id = item.id;
        this.name = item.name;
        this.cost = item.cost;
        this.departmentId = item.getDepartmentId();
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
