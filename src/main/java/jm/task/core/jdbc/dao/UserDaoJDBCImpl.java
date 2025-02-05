package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String SQL_CREATE_TABLE = """
                    CREATE TABLE IF NOT EXISTS `users` (
                      `id` BIGINT NOT NULL AUTO_INCREMENT,
                      `name` VARCHAR(45) NOT NULL,
                      `lastName` VARCHAR(45) NOT NULL,
                      `age` TINYINT(1) NOT NULL,
                      PRIMARY KEY (`id`))
                    ENGINE = InnoDB""";
    private static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS `jdbc_test`.`users`";
    private static final String SQL_SAVE_USER = "INSERT INTO `jdbc_test`.`users` (name, lastName, age) VALUES (?, ?, ?)";
    private static final String SQL_REMOVE_USER_BY_ID = "DELETE FROM `jdbc_test`.`users` WHERE id = ?";
    private static final String SQL_GET_ALL_USERS = "SELECT * FROM `jdbc_test`.`users`";
    private static final String SQL_CLEAN_USERS_TABLE = "TRUNCATE TABLE `jdbc_test`.`users`";

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.execute(SQL_CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement stmt = connection.createStatement()) {

            stmt.execute(SQL_DROP_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL_SAVE_USER)) {

            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setByte(3, age);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL_REMOVE_USER_BY_ID)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        User user;
        ResultSet resultSet;

        try (Connection connection = Util.getConnection();
             Statement stmt = connection.createStatement()) {

            resultSet = stmt.executeQuery(SQL_GET_ALL_USERS);
            while (resultSet.next()) {
                user = new User(resultSet.getString("name"), resultSet.getString("lastName"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement stmt = connection.createStatement()) {

            stmt.execute(SQL_CLEAN_USERS_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
