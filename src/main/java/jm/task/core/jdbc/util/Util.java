package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String HOST =   "jdbc:mysql://localhost:3306/mydbtest?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    //HibernateUtil
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    private Connection connection;

    public Util(){
        try {
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Configuration Configuration (){
        //Конфигурация ниже эквивалентна hibernate.cfg.xml
        Properties properties = new Properties();
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty(Environment.HBM2DDL_AUTO,"update");
        properties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.setProperty(Environment.USER, "root");
        properties.setProperty(Environment.PASS, "root");
        properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/mydbtest?useSSL=false&serverTimezone=UTC");

        //Теперь создайте объект конфигурации и добавьте к нему эти свойства.
        Configuration configuration = new Configuration();
        configuration.setProperties(properties);

        //Мы добавим User в объект Configuration для создания таблицы
        return configuration.addAnnotatedClass(User.class);
    }
}
