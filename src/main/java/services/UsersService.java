package services;

import dao.UsersDAO;
import model.Users;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UsersService {
    private static UsersService INSTANCE;
    private UsersService() {};
    public static UsersService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UsersService();
        }
        return INSTANCE;
    }

    private UsersDAO dao = getUsersDAO();
    public List<Users> getAllUsers() {
        return dao.getAllUsers();
    }

    public Users getUserById(long id)  {
        return dao.getUserById(id);
    }
    public void addUser (Users user) {
        dao.addUser(user);
    }
    public void update(Users user, String name, String password, String email) {
        dao.updateUser(user, name, password, email);
    }
    public void deleteUser(Users user) {
        dao.deleteUser(user);
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=root").       //password
                    append("&autoReconnect=true&useSSL=false&serverTimezone=UTC");       //SSL

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UsersDAO getUsersDAO() {
        return new UsersDAO(getMysqlConnection());
    }
}
