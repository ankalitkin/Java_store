package ru.vsu.cs.course3;

import ru.vsu.cs.course3.controller.DepartmentController;
import ru.vsu.cs.course3.controller.ItemController;
import ru.vsu.cs.course3.controller.MenuController;
import ru.vsu.cs.course3.dao.DepartmentDao;
import ru.vsu.cs.course3.dao.DepartmentDaoImpl;
import ru.vsu.cs.course3.dao.ItemDao;
import ru.vsu.cs.course3.dao.ItemDaoImpl;
import ru.vsu.cs.course3.provider.ServiceProvider;
import ru.vsu.cs.course3.service.DepartmentService;
import ru.vsu.cs.course3.service.ItemService;
import ru.vsu.cs.course3.utils.Connector;

import static ru.vsu.cs.course3.utils.ConnectionDetails.DB_LOGIN;
import static ru.vsu.cs.course3.utils.ConnectionDetails.DB_PASSWORD;
import static ru.vsu.cs.course3.utils.ConnectionDetails.DB_URL;

public class Application {
    public static void main(String[] args) {
        ServiceProvider.register(Connector.class, new Connector(DB_URL, DB_LOGIN, DB_PASSWORD));
        ServiceProvider.register(DepartmentDao.class, new DepartmentDaoImpl());
        ServiceProvider.register(ItemDao.class, new ItemDaoImpl());
        ServiceProvider.register(DepartmentService.class, new DepartmentService());
        ServiceProvider.register(ItemService.class, new ItemService());
        ServiceProvider.register(DepartmentController.class, new DepartmentController());
        ServiceProvider.register(ItemController.class, new ItemController());

        new MenuController().show();
    }
}
