package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() { //нормально
        Util util = new Util();
        try (Statement statement = util.getConnection().createStatement()) {
            //строка запроса для Создание таблицы User(ов)
            String queri = "CREATE TABLE `mydbtest`.`User` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastName` VARCHAR(45) NOT NULL,\n" +
                    "  `age` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;";
            statement.executeUpdate(queri);
        } catch (SQLException throwables) {
            System.out.println("Что то не так либо таблица создана");
        }
    }
    //удалить таблицу пользователей
    public void dropUsersTable() { //нормально
        Util util = new Util();
        try (Statement statement = util.getConnection().createStatement();){
            statement.executeUpdate("DROP TABLE user;");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //добавить пользователей
    public void saveUser(String name, String lastName, byte age) { //нормально
        Util util = new Util();
        try (Statement statement = util.getConnection().createStatement()) {
            //строка запроса для Создание таблицы User(ов)
            statement.executeUpdate("INSERT INTO user (name, lastName, age) VALUES ('" + name +
                    "', '" + lastName +
                    "', " + age + ");");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //удалить  пользователей
    public void removeUserById(long id) { //нормально
        Util util = new Util();
        try (Statement statement = util.getConnection().createStatement();){
            statement.executeUpdate("DELETE FROM mydbtest.user WHERE id = "+ id +";");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    // показать всех юзеров
    public List<User> getAllUsers() { //нормально
        Util util = new Util();
        List<User> userList = new ArrayList<>();
        try (Statement statement = util.getConnection().createStatement();){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user;");
            while (resultSet.next()){
                User user = new User(resultSet.getString(2),
                        resultSet.getString(3),
                        (byte) resultSet.getInt(4));
//                user.setId((long) resultSet.getInt("id"));
//                user.setName(resultSet.getString("name"));
//                user.setLastName(resultSet.getString("lastName"));
//                user.setAge((byte) resultSet.getInt("age"));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }
    //очистить таблицу пользователей
    public void cleanUsersTable() {
        Util util = new Util();
        try (Statement statement = util.getConnection().createStatement();){
            statement.executeUpdate("DELETE FROM mydbtest.user;");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
