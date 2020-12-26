package ru.vsu.cs.course3.dao;

import ru.vsu.cs.course3.domain.Item;
import ru.vsu.cs.course3.provider.MockDatabaseProvider;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MockItemRepository implements ItemDao {
    private Map<Integer, Item> getTable() {
        return MockDatabaseProvider.get(Item.class);
    }

    private int getNextId() {
        return MockDatabaseProvider.getNextId(Item.class);
    }

    @Override
    public Item findById(int id) {
        Item Item = getTable().get(id);
        if (Item == null)
            return null;
        return new Item(Item);
    }

    @Override
    public List<Item> findAll() {
        return getTable().values().stream()
                .map(Item::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> findAllByDepartmentId(int id) {
        return getTable().values().stream()
                .filter(item -> item.getDepartmentId() == id)
                .map(Item::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByDepartmentId(int id) {
        List<Integer> keys = getTable().values().stream()
                .filter(item -> item.getDepartmentId() == id)
                .map(Item::getId)
                .collect(Collectors.toList());
        keys.forEach(this::deleteById);
    }

    @Override
    public Item save(Item entity) {
        if (entity == null)
            throw new IllegalArgumentException();
        entity = new Item(entity);
        if (entity.getId() == 0)
            entity.setId(getNextId());
        getTable().put(entity.getId(), entity);
        return new Item(entity);
    }

    @Override
    public boolean deleteById(int id) {
        return getTable().remove(id) != null;
    }
}
