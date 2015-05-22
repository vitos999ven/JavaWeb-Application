package hibernate.DAO;

import hibernate.logic.User;
import java.sql.SQLException;
import java.util.List;



public interface UserDAO {
    public void addUser(User user) throws SQLException;
    public User getUser(String login) throws SQLException;
    public void updateUser(User user) throws SQLException;
    public List<User> getAllUsers() throws SQLException;
    public void deleteUser(String login) throws SQLException;
}
