package dao;


import com.mysql.cj.xdevapi.SqlDataResult;
import model.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {
    private Connection connection;

    public UsersDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Users> getAllUsers() {
        List<Users> list = new ArrayList<>();
        Users user;
        try (PreparedStatement pr = connection.prepareStatement("select * from users")) {
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                user = new Users();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setPass(rs.getString(3));
                user.setEmail(rs.getString(4));
                list.add(user);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return list;
    }
    public boolean isExistUser(String name) {
        return getAllUsers()
                .stream()
                .map(Users::getName)
                .anyMatch(x -> x.equals(name));
    }
    public void addUser (Users user) {
        if (!isExistUser(user.getName())) {
            try(PreparedStatement pr = connection.prepareStatement("insert into users (name, password, email) values (?,?,?)")) {
                pr.setString(1, user.getName());
                pr.setString(2,user.getPass());
                pr.setString(3,user.getEmail());
                pr.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Users is exist");
        }
    }

    public void updateUser(Users user, String name, String password, String email) {
        if (isExistUser(user.getName())) {
            try (PreparedStatement pr = connection.prepareStatement("update users set name = ?,password = ?, email = ? where id = ?")) {
                pr.setString(1,name);
                pr.setString(2,password);
                pr.setString(3,email);
                pr.setLong(4, user.getId());
                pr.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("User don't exist!");
        }
    }

    public void deleteUser(Users user) {
        if (isExistUser(user.getName())) {
            try (PreparedStatement pr = connection.prepareStatement("delete from users where id = ?")) {
                pr.setLong(1, user.getId());
                pr.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else System.out.println("user don't delete, because user don't exist");
    }
    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment,  name VARCHAR(256) NOT NULL, password VARCHAR(256) NOT NULL, description TEXT(), primary key (id))");
        stmt.close();
    }
    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("drop table if exists users");
        stmt.close();
    }
    public Users getUserById(long id)  {
        Users user = new Users();
        try (PreparedStatement pr = connection.prepareStatement("select * from users where id=?")) {


            pr.setLong(1, id);
            ResultSet resultSet = pr.executeQuery();
                resultSet.next();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setPass(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));



        } catch (SQLException e) {
            e.printStackTrace();
        } return user;
    }
}
