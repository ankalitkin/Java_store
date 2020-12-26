package ru.vsu.cs.course3.controller;

import ru.vsu.cs.course3.domain.Department;
import ru.vsu.cs.course3.domain.Item;
import ru.vsu.cs.course3.provider.ServiceProvider;
import ru.vsu.cs.course3.service.DepartmentService;
import ru.vsu.cs.course3.service.ItemService;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static ru.vsu.cs.course3.utils.ConsoleUtils.*;
import static ru.vsu.cs.course3.utils.ConsoleUtils.promptLine;

public class ItemController {
    private ItemService itemService;
    private DepartmentService departmentService;
    private DepartmentController departmentController;

    public ItemController() {
        itemService = ServiceProvider.get(ItemService.class);
        departmentService = ServiceProvider.get(DepartmentService.class);
        departmentController = ServiceProvider.get(DepartmentController.class);
    }

    public void show() {
        there:
        while (true) {
            print("1. Показать все товары");
            print("2. Показать все товары в отделе");
            print("3. Добавить товар");
            print("4. Редактировать товар");
            print("5. Удалить товар");
            print("0. Назад");
            print("");
            switch (readInt()) {
                case 1:
                    showItems(getAllItems());
                    break;
                case 2:
                    Department department = departmentController.chooseDepartment();
                    if(department != null) {
                        showItems(getAllItemsByDepartmentId(department.getId()));
                    }
                    break;
                case 3:
                    addItem();
                    break;
                case 4:
                    Item item = chooseItem();
                    if(item != null) {
                        editItem(item);
                    }
                    break;
                case 5:
                    Item item2 = chooseItem();
                    if(item2 != null) {
                        deleteItem(item2);
                    }
                    break;
                case 0:
                    break there;
            }
        }
    }

    private List<Item> getAllItems() {
        List<Item> allItems = itemService.getAllItems();
        allItems.sort(Comparator.comparingInt(Item::getId));
        return allItems;
    }

    private List<Item> getAllItemsByDepartmentId(int departmentId) {
        List<Item> allItems = itemService.getAllItemsByDepartmentId(departmentId);
        allItems.sort(Comparator.comparingInt(Item::getId));
        return allItems;
    }

    private void showItems(List<Item> allItems) {
        if(allItems.size() == 0) {
            print("Не найдено ни одного товара");
        }
        int count = 1;
        for (Item item : allItems) {
            //String departmentName = departmentService.getById(item.getId()).getName();
            printFormat("%d. %s. Стоимость: %s.", count++, item.getName(),
                    item.getCost().toString());
        }
        print("");
    }

    private void addItem() {
        if(departmentService.getAllDepartments().size() == 0) {
            print("Невозможно добавить товар так как в системе нет ни одного отдела");
            print("");
            return;
        }
        String name = promptLine("Введите название нового товара: ");
        BigDecimal cost = promptBigDecimal("Введите стоимость нового товара: ");
        Department department = departmentController.chooseDepartment();
        if(department == null) {
            print("");
            return;
        }
        itemService.add(name, cost,department.getId());
        print("Новый отдел добавлен успешно!");
        print("");
    }

    private Item chooseItem() {
        List<Item> allItems = itemService.getAllItems();
        if(allItems.size() == 0) {
            print("Не найдено ни одного товара");
            print("");
            return null;
        }
        print("Выберите товар из перечисленных ниже: ");
        showItems(allItems);
        int index = promptInt("Введите номер выбранного товара: ") - 1;
        if(index < 0 || index >= allItems.size()) {
            print("Введён некорректный номер");
            print("");
            return null;
        }
        return allItems.get(index);
    }

    private void editItem(Item item) {
        item.setName(promptLine("Введите новое название товара: "));
        item.setCost(promptBigDecimal("Введите новую стоимость: "));
        Department department = departmentController.chooseDepartment();
        if(department == null) {
            print("");
            return;
        }
        item.setDepartmentId(department.getId());
        itemService.save(item);
        print("Товар изменён успешно!");
        print("");
    }

    private void deleteItem(Item item) {
        itemService.deleteById(item.getId());
    }

    private String getDepartmentNameByDepartmentId(int id) {
        return departmentService.getById(id).getName();
    }
}
