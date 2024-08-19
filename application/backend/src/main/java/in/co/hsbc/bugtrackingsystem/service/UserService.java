package in.co.hsbc.bugtrackingsystem.service;

import in.co.hsbc.bugtrackingsystem.exception.UserNotFoundException;

public interface UserService {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(String userId) throws UserNotFoundException;
    User getUserById(String userId) throws UserNotFoundException;
    List<User> getAllUsers();
}
