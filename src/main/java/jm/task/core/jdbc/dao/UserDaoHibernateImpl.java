package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    //Создайте таблицу пользователей
    @Override
    public void createUsersTable() {
        Util util = new Util();
        //создается базаданных user
        try (SessionFactory factory = util.Configuration().buildSessionFactory();
             Session session = factory.openSession()){
            System.out.println("Таблица user создана");
            session.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //удалить таблицу пользователей
    @Override
    public void dropUsersTable() {
        Util util = new Util();
        //создается базаданных user
        try (SessionFactory factory = util.Configuration().buildSessionFactory();
             Session session = factory.openSession()){
            Transaction tx = session.beginTransaction();
            Query deleteQuery = session.createNativeQuery("DROP TABLE mydbtest.user;");
            deleteQuery.executeUpdate();
            System.out.println("таблица User удалена");
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //добавить пользователей
    @Override
    public void saveUser(String name, String lastName, byte age) {
        Util util = new Util();
        //создается базаданных user
        try (SessionFactory factory = util.Configuration().buildSessionFactory();
             Session session = factory.openSession()){
            Transaction tx = session.beginTransaction();
            User user = new User (name, lastName, age);
            session.persist(user);
            System.out.println("User с именем – "+ user.getName() + " добавлен в базу данных");
            tx.commit();
            session.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //удалить  пользователей
    @Override
    public void removeUserById(long id) {
        Util util = new Util();
        //создается базаданных user
        try (SessionFactory factory = util.Configuration().buildSessionFactory();
             Session session = factory.openSession()){
            Transaction tx = session.beginTransaction();
            Query deleteQuery = session.createNativeQuery("DELETE from user where id=" + id + ";");
            deleteQuery.executeUpdate();
            System.out.println("пользователей c id = " + id + " удален");
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // показать всех юзеров
    @Override
    public List<User> getAllUsers() {
        Util util = new Util();
        List<User> userList = new ArrayList<>();
        try (SessionFactory factory = util.Configuration().buildSessionFactory();
             Session session = factory.openSession()) {
            List<Object[]> users = session.createNativeQuery("SELECT * FROM user;").list();
        for (Object[] objects : users){
            User user1 = new User((String)objects[3], (String)objects[2], (byte)objects[1]);
            userList.add(user1);
            System.out.println(user1.toString());
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    //очистить таблицу пользователей
    @Override
    public void cleanUsersTable() {
        Util util = new Util();
        //создается базаданных user
        try (SessionFactory factory = util.Configuration().buildSessionFactory();
             Session session = factory.openSession()){
            Transaction tx = session.beginTransaction();
            Query deleteQuery = session.createNativeQuery("TRUNCATE TABLE user;");
            deleteQuery.executeUpdate();
            System.out.println("таблица User очищена");
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
