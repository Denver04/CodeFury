package in.co.hsbc.bugtrackingsystem.repository.impl;

import in.co.hsbc.bugtrackingsystem.repository.UserDao;
import in.co.hsbc.bugtrackingsystem.entity.User;
import in.co.hsbc.bugtrackingsystem.util.DatabaseConnection;
import in.co.hsbc.bugtrackingsystem.exception.UserAlreadyExistsException;
import in.co.hsbc.bugtrackingsystem.exception.UserNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public void addUser(User user) throws UserAlreadyExistsException
    {
        // Check if user already exists
        if (getUserById(user.getUserId()) != null) {
            throw new UserAlreadyExistsException("User with ID " + user.getUserId() + " already exists.");
        }

        String sql = "INSERT INTO users (userId, name, email, role, lastLogin) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUserId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole().name());
            stmt.setTimestamp(5, Timestamp.valueOf(user.getLastLogin()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateUser(User user) throws UserNotFoundException{
        // Check if user exists
        if (getUserById(user.getUserId()) == null) {
            throw new UserNotFoundException("User with ID " + user.getUserId() + " does not exist.");
        }

        String sql = "UPDATE users SET name = ?, email = ?, role = ?, lastLogin = ? WHERE userId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getRole().name());
            stmt.setTimestamp(4, Timestamp.valueOf(user.getLastLogin()));
            stmt.setString(5, user.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(String userId) throws UserNotFoundException {
        // Check if user exists
        if (getUserById(userId) == null) {
            throw new UserNotFoundException("User with ID " + userId + " does not exist.");
        }

        String sql = "DELETE FROM users WHERE userId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(String userId) {
        String sql = "SELECT * FROM users WHERE userId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("userId"),
                        rs.getString("name"),
                        rs.getString("email"),
                        User.Role.valueOf(rs.getString("role")),
                        rs.getTimestamp("lastLogin").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new User(
                        rs.getString("userId"),
                        rs.getString("name"),
                        rs.getString("email"),
                        User.Role.valueOf(rs.getString("role")),
                        rs.getTimestamp("lastLogin").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}