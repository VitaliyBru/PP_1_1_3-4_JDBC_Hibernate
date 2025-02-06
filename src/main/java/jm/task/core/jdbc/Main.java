package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        // Создание таблицы в БД
        userService.createUsersTable();

        // Добавление 4-х пользователей в БД
        userService.saveUser("Сергей","Шилов",(byte) 47);
        System.out.println("User с именем – Сергей добавлен в базу данных");
        userService.saveUser("Евгений","Холодняк",(byte) 22);
        System.out.println("User с именем – Евгений добавлен в базу данных");
        userService.saveUser("Ярослава","Порехина",(byte) 30);
        System.out.println("User с именем – Ярослава добавлен в базу данных");
        userService.saveUser("Роман","Курносов",(byte) 10);
        System.out.println("User с именем – Роман добавлен в базу данных\n");

        // Получение всех User из базы и вывод в консоль
        userService.getAllUsers().forEach(System.out::println);

        // Очистка таблицы User(ов)
        userService.cleanUsersTable();

        // Удаление таблицы
        userService.dropUsersTable();
    }
}
