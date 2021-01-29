package ru.vsu.cs.course3.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Item {
    private int id;
    private String name;
    private BigDecimal cost;
    private int departmentId;

    public Item(Item item) {
        this(item.id, item.name, item.cost, item.departmentId);
    }

    public Item(String name, BigDecimal cost, int departmentId) {
        this.name = name;
        this.cost = cost;
        this.departmentId = departmentId;
    }
}
