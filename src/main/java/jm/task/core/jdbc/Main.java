package jm.task.core.jdbc;

import javafx.concurrent.Task;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;

import javax.jws.soap.SOAPBinding;
import javax.transaction.Synchronization;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь


         UserService userService = new UserServiceImpl();
        //Создание таблицы User(ов)
        userService.createUsersTable();

        // Добавление 4 User(ов) в таблицу с данными на свой выбор.
        // После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
        userService.saveUser("Ivan1", "Ivanov1", (byte) 31);
        System.out.println("User с именем – Ivan1 добавлен в базу данных");
        userService.saveUser("Ivan2", "Ivanov2", (byte) 32);
        System.out.println("User с именем – Ivan2 добавлен в базу данных");
        userService.saveUser("Ivan3", "Ivanov3", (byte) 33);
        System.out.println("User с именем – Ivan3 добавлен в базу данных");
        userService.saveUser("Ivan4", "Ivanov4", (byte) 34);
        System.out.println("User с именем – Ivan4 добавлен в базу данных");

        // Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        List<User> userList = userService.getAllUsers();
        for (User user : userList) System.out.println(user);

        // Очистка таблицы User(ов)
        userService.cleanUsersTable();

        // Удаление таблицы
        userService.dropUsersTable();

        //Hibernate
        UserDao userDao = new UserDaoHibernateImpl();

        //создание таблицы
        userDao.createUsersTable();

        // Добавление 4 User(ов) в таблицу с данными на свой выбор.
        // После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
        userDao.saveUser("Ivan1", "Ivanov1", (byte) 31);
        userDao.saveUser("Ivan2", "Ivanov2", (byte) 32);
        userDao.saveUser("Ivan3", "Ivanov3", (byte) 33);
        userDao.saveUser("Ivan4", "Ivanov4", (byte) 34);

        //Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        userDao.getAllUsers();

        // Очистка таблицы User(ов)
        userDao.cleanUsersTable();

        // Удаление таблицы
        userDao.dropUsersTable();
    }
}
