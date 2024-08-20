package in.co.hsbc.bugtrackingsystem;
import in.co.hsbc.bugtrackingsystem.exception.UserAlreadyExistsException;
import in.co.hsbc.bugtrackingsystem.exception.UserNotFoundException;
import in.co.hsbc.bugtrackingsystem.repository.impl.UserDaoImpl;
import in.co.hsbc.bugtrackingsystem.entity.User;
import in.co.hsbc.bugtrackingsystem.util.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import in.co.hsbc.bugtrackingsystem.entity.Project;
import in.co.hsbc.bugtrackingsystem.entity.Bug;
import in.co.hsbc.bugtrackingsystem.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class UserDaoImplTest
{

    private UserDaoImpl userDao;
    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException {
        userDao = new UserDaoImpl();
        connection = DatabaseConnection.getConnection();
        clearUsersTable();
    }

    private void clearUsersTable() {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM users")) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddUser() throws UserAlreadyExistsException {
        User user = new User("U001", "John Doe", "john.doe@example.com", User.Role.TESTER, LocalDateTime.now());
        userDao.addUser(user);

        User retrievedUser = userDao.getUserById("U001");
        assertNotNull(retrievedUser);
        assertEquals("John Doe", retrievedUser.getName());
        assertEquals("john.doe@example.com", retrievedUser.getEmail());
    }


    @Test
    public void testDeleteUser() throws UserAlreadyExistsException, UserNotFoundException {
        User user = new User("U001", "John Doe", "john.doe@example.com", User.Role.TESTER, LocalDateTime.now());
        userDao.addUser(user);

        userDao.deleteUser("U001");

        User deletedUser = userDao.getUserById("U001");
        assertNull(deletedUser);
    }


    @Test
    public void testGetUserById() throws UserAlreadyExistsException {
        User user = new User("U001", "John Doe", "john.doe@example.com", User.Role.TESTER, LocalDateTime.now());
        userDao.addUser(user);

        User retrievedUser = userDao.getUserById("U001");
        assertNotNull(retrievedUser);
        assertEquals("U001", retrievedUser.getUserId());
        assertEquals("John Doe", retrievedUser.getName());
    }

    @Test
    public void testGetAllUsers() throws UserAlreadyExistsException {
        User user1 = new User("U001", "John Doe", "john.doe@example.com", User.Role.TESTER, LocalDateTime.now());
        User user2 = new User("U002", "Jane Smith", "jane.smith@example.com", User.Role.DEVELOPER, LocalDateTime.now());

        userDao.addUser(user1);
        userDao.addUser(user2);

        List<User> users = userDao.getAllUsers();
        assertEquals(2, users.size());
        assertEquals("John Doe", users.get(0).getName());
        assertEquals("Jane Smith", users.get(1).getName());
    }
}





