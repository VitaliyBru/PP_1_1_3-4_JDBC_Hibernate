package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final String SQL_CREATE_TABLE = """
                    CREATE TABLE IF NOT EXISTS `jdbc_test`.`users` (
                      `id` BIGINT NOT NULL AUTO_INCREMENT,
                      `name` VARCHAR(45) NOT NULL,
                      `lastName` VARCHAR(45) NOT NULL,
                      `age` TINYINT(1) NOT NULL,
                      PRIMARY KEY (`id`))
                    ENGINE = InnoDB""";
    private static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS `jdbc_test`.`users`";
    private static final String SQL_CLEAN_USERS_TABLE = "TRUNCATE TABLE `jdbc_test`.`users`";

    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(SQL_CREATE_TABLE).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(SQL_DROP_TABLE).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            allUsers = session.createQuery("FROM User").getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(SQL_CLEAN_USERS_TABLE).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
