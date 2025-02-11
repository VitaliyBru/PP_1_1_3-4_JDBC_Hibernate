package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userService = new UserDaoHibernateImpl();

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

        // Закрытие sessionFactory
        Util.getSessionFactory().close();
    }
}
