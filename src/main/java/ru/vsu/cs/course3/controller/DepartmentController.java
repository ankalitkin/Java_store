package ru.vsu.cs.course3.controller;

import ru.vsu.cs.course3.domain.Department;
import ru.vsu.cs.course3.exception.ApplicationException;
import ru.vsu.cs.course3.provider.ServiceProvider;
import ru.vsu.cs.course3.service.DepartmentService;

import java.util.Comparator;
import java.util.List;

import static ru.vsu.cs.course3.utils.ConsoleUtils.print;
import static ru.vsu.cs.course3.utils.ConsoleUtils.printFormat;
import static ru.vsu.cs.course3.utils.ConsoleUtils.promptInt;
import static ru.vsu.cs.course3.utils.ConsoleUtils.promptLine;
import static ru.vsu.cs.course3.utils.ConsoleUtils.readInt;

public class DepartmentController {
    private final DepartmentService departmentService = ServiceProvider.get(DepartmentService.class);

    void show() {
        there:
        while (true) {
            try {
                print("1. Показать все отделы");
                print("2. Добавить отдел");
                print("3. Редактировать отдел");
                print("4. Удалить отдел");
                print("0. Назад");
                print("");
                switch (readInt()) {
                    case 1:
                        showDepartments(getAllDepartments());
                        break;
                    case 2:
                        addDepartment();
                        break;
                    case 3:
                        Department department = chooseDepartment();
                        if (department != null) {
                            editDepartment(department);
                        }
                        break;
                    case 4:
                        Department department2 = chooseDepartment();
                        if (department2 != null) {
                            if (departmentService.deleteById(department2.getId())) {
                                print("Отдел удалён успешно");
                            } else {
                                print("Отдел не может быть удалён пока содержит товары");
                            }
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

    private List<Department> getAllDepartments() {
        List<Department> allDepartments = departmentService.getAllDepartments();
        allDepartments.sort(Comparator.comparingInt(Department::getId));
        return allDepartments;
    }

    private void showDepartments(List<Department> allDepartments) {
        if (allDepartments.isEmpty()) {
            print("Не найдено ни одного отдела");
        }
        int count = 1;
        for (Department department : allDepartments) {
            printFormat("%d. %s. Часы работы: %s", count++, department.getName(), department.getWorkingHours());
        }
        print("");
    }

    private void addDepartment() {
        String name = promptLine("Введите название нового отдела: ");
        String workingHours = promptLine("Введите часы работы нового отдела: ");
        departmentService.add(name, workingHours);
        print("Новый отдел добавлен успешно!");
        print("");
    }

    Department chooseDepartment() {
        List<Department> allDepartments = departmentService.getAllDepartments();
        if (allDepartments.isEmpty()) {
            print("Не найдено ни одного отдела");
            print("");
            return null;
        }
        print("Выберите отдел из перечисленных ниже: ");
        showDepartments(allDepartments);
        int index = promptInt("Введите номер выбранного отдела: ") - 1;
        if (index < 0 || index >= allDepartments.size()) {
            print("Введён некорректный номер");
            print("");
            return null;
        }
        return allDepartments.get(index);
    }

    private void editDepartment(Department department) {
        department.setName(promptLine("Введите новое название отдела: "));
        department.setWorkingHours(promptLine("Введите новые часы работы: "));
        departmentService.update(department);
        print("Отдел изменён успешно!");
        print("");
    }

}
