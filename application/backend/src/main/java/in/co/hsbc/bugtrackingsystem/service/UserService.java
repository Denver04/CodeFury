package in.co.hsbc.bugtrackingsystem.service;

import in.co.hsbc.bugtrackingsystem.entity.User;
import in.co.hsbc.bugtrackingsystem.exception.UserAlreadyExistsException;
import in.co.hsbc.bugtrackingsystem.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    void addUser(User user) throws UserAlreadyExistsException;
    void updateUser(User user) throws UserNotFoundException;
    void deleteUser(String userId) throws UserNotFoundException;
    User getUserById(String userId) throws UserNotFoundException;
    List<User> getAllUsers();
}
