package ru.vsu.cs.course3;

import ru.vsu.cs.course3.controller.DepartmentController;
import ru.vsu.cs.course3.controller.ItemController;
import ru.vsu.cs.course3.controller.MenuController;
import ru.vsu.cs.course3.dao.DepartmentDao;
import ru.vsu.cs.course3.dao.ItemDao;
import ru.vsu.cs.course3.dao.MockDepartmentRepository;
import ru.vsu.cs.course3.dao.MockItemRepository;
import ru.vsu.cs.course3.domain.Department;
import ru.vsu.cs.course3.domain.Item;
import ru.vsu.cs.course3.provider.MockDatabaseProvider;
import ru.vsu.cs.course3.provider.ServiceProvider;
import ru.vsu.cs.course3.service.DepartmentService;
import ru.vsu.cs.course3.service.ItemService;

import java.math.BigDecimal;

public class Application {
    public static void main(String[] args) {
        MockDatabaseProvider.addTable(Department.class);
        MockDatabaseProvider.addTable(Item.class);
        MockDepartmentRepository mdr = new MockDepartmentRepository();
        MockItemRepository mir = new MockItemRepository();
        ServiceProvider.register(DepartmentDao.class, mdr);
        ServiceProvider.register(ItemDao.class, mir);
        ServiceProvider.register(DepartmentService.class, new DepartmentService());
        ServiceProvider.register(ItemService.class, new ItemService());
        ServiceProvider.register(DepartmentController.class, new DepartmentController());
        ServiceProvider.register(ItemController.class, new ItemController());

        Department td = mdr.save(new Department("Отдел продажи столов", "8:00 - 17:00"));
        Department pd = mdr.save(new Department("Отдел продажи авторучек", "10:00 - 14:00"));
        Department dd = mdr.save(new Department("Отдел продажи пончиков", "9:00 - 22:00"));

        mir.save(new Item("Стол письменный", new BigDecimal(3500), td.getId()));
        mir.save(new Item("Стол обеденный", new BigDecimal(2900), td.getId()));
        mir.save(new Item("Ручка шариковая", new BigDecimal(10), pd.getId()));
        mir.save(new Item("Ручка гелевая", new BigDecimal(20), pd.getId()));
        mir.save(new Item("Пончик с вареньем", new BigDecimal(35), dd.getId()));
        mir.save(new Item("Пончик со сгущёнкой", new BigDecimal(35), dd.getId()));

        new MenuController().show();
    }
}
