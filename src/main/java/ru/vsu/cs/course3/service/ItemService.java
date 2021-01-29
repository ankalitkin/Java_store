package ru.vsu.cs.course3.service;

import ru.vsu.cs.course3.dao.ItemDao;
import ru.vsu.cs.course3.domain.Item;
import ru.vsu.cs.course3.provider.ServiceProvider;

import java.math.BigDecimal;
import java.util.List;

public class ItemService {
    private final ItemDao itemDao = ServiceProvider.get(ItemDao.class);

    public List<Item> getAllItems() {
        return itemDao.findAll();
    }

    public List<Item> getAllItemsByDepartmentId(int departmentId) {
        return itemDao.findAllByDepartmentId(departmentId);
    }

    public Item getById(int id) {
        return itemDao.findById(id);
    }

    public void add(String name, BigDecimal cost, int departmentId) {
        itemDao.insert(new Item(name, cost, departmentId));
    }

    public void update(Item item) {
        itemDao.update(item);
    }

    public boolean deleteById(int id) {
        return itemDao.deleteById(id);
    }
}
