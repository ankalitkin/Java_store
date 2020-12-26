package ru.vsu.cs.course3.controller;
import ru.vsu.cs.course3.provider.ServiceProvider;

import static ru.vsu.cs.course3.utils.ConsoleUtils.*;

public class MenuController {
    public void show() {
        print("--- Добро пожаловать! ---");
        there:
        while (true) {
            print("1. Перейти к товарам");
            print("2. Перейти к отделам");
            print("0. Выйти");
            print("");
            switch (readInt()){
                case 1:
                    ServiceProvider.get(ItemController.class).show();
                    break;
                case 2:
                    ServiceProvider.get(DepartmentController.class).show();
                    break;
                case 0:
                    break there;
            }
        }
    }
}
