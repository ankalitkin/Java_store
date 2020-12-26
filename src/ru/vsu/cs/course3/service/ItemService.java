package ru.vsu.cs.course3.service;

import ru.vsu.cs.course3.dao.ItemDao;
import ru.vsu.cs.course3.domain.Item;
import ru.vsu.cs.course3.provider.ServiceProvider;

import java.math.BigDecimal;
import java.util.List;

public class ItemService {
    private ItemDao itemDao;

    public ItemService() {
        itemDao = ServiceProvider.get(ItemDao.class);
    }

    public List<Item> getAllItems() {
        return itemDao.findAll();
    }

    public List<Item> getAllItemsByDepartmentId(int departmentId) {
        return itemDao.findAllByDepartmentId(departmentId);
    }

    public Item getById(int id) {
        return itemDao.findById(id);
    }

    public Item add(String name, BigDecimal cost, int departmentId) {
        return itemDao.save(new Item(name, cost, departmentId));
    }

    public Item save(Item Item) {
        return itemDao.save(Item);
    }

    public boolean deleteById(int id) {
        return itemDao.deleteById(id);
    }
}
