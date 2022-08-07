package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getConnection();
    String createUT = "CREATE TABLE IF NOT EXISTS users";
    String dropUT = "DROP TABLE IF EXISTS users";
    String cleanUT = "TRUNCATE test.users";

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(createUT +"(id int (10) AUTO_INCREMENT,\n" +
                    "name varchar(20) NOT NULL,\n" +
                    "lastname varchar(50) NOT NULL,\n" +
                    "age int NOT NULL,\n" +
                    "PRIMARY KEY (id))").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(dropUT).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(session.get(User.class,id));
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            users = session.createCriteria(User.class).list();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery(cleanUT).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }
}
