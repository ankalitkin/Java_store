package ru.vsu.cs.course3.controller;

import ru.vsu.cs.course3.domain.Department;
import ru.vsu.cs.course3.domain.Item;
import ru.vsu.cs.course3.exception.ApplicationException;
import ru.vsu.cs.course3.provider.ServiceProvider;
import ru.vsu.cs.course3.service.DepartmentService;
import ru.vsu.cs.course3.service.ItemService;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static ru.vsu.cs.course3.utils.ConsoleUtils.print;
import static ru.vsu.cs.course3.utils.ConsoleUtils.printFormat;
import static ru.vsu.cs.course3.utils.ConsoleUtils.promptBigDecimal;
import static ru.vsu.cs.course3.utils.ConsoleUtils.promptInt;
import static ru.vsu.cs.course3.utils.ConsoleUtils.promptLine;
import static ru.vsu.cs.course3.utils.ConsoleUtils.readInt;

public class ItemController {
    private final ItemService itemService = ServiceProvider.get(ItemService.class);
    private final DepartmentService departmentService = ServiceProvider.get(DepartmentService.class);
    private final DepartmentController departmentController = ServiceProvider.get(DepartmentController.class);

    public void show() {
        there:
        while (true) {
            try {
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
                        if (department != null) {
                            showItems(getAllItemsByDepartmentId(department.getId()));
                        }
                        break;
                    case 3:
                        addItem();
                        break;
                    case 4:
                        Item item = chooseItem();
                        if (item != null) {
                            editItem(item);
                        }
                        break;
                    case 5:
                        Item item2 = chooseItem();
                        if (item2 != null) {
                            deleteItem(item2);
                        }
                        break;
                    default:
                        break there;
                }
            } catch (ApplicationException e) {
                e.printStackTrace();
                print(e.getMessage() != null ? e.getMessage() : "Произошла непредвиденная ошибка");
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
        if (allItems.isEmpty()) {
            print("Не найдено ни одного товара");
        }
        int count = 1;
        for (Item item : allItems) {
            printFormat("%d. %s. Стоимость: %s.", count++, item.getName(),
                    item.getCost().toString());
        }
        print("");
    }

    private void addItem() {
        if (departmentService.getAllDepartments().isEmpty()) {
            print("Невозможно добавить товар так как в системе нет ни одного отдела");
            print("");
            return;
        }
        String name = promptLine("Введите название нового товара: ");
        BigDecimal cost = promptBigDecimal("Введите стоимость нового товара: ");
        Department department = departmentController.chooseDepartment();
        if (department == null) {
            print("");
            return;
        }
        itemService.add(name, cost, department.getId());
        print("Новый отдел добавлен успешно!");
        print("");
    }

    private Item chooseItem() {
        List<Item> allItems = itemService.getAllItems();
        if (allItems.isEmpty()) {
            print("Не найдено ни одного товара");
            print("");
            return null;
        }
        print("Выберите товар из перечисленных ниже: ");
        showItems(allItems);
        int index = promptInt("Введите номер выбранного товара: ") - 1;
        if (index < 0 || index >= allItems.size()) {
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
        if (department == null) {
            print("");
            return;
        }
        item.setDepartmentId(department.getId());
        itemService.update(item);
        print("Товар изменён успешно!");
        print("");
    }

    private void deleteItem(Item item) {
        itemService.deleteById(item.getId());
    }

}
