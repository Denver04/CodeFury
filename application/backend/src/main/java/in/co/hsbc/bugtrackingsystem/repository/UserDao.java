package in.co.hsbc.bugtrackingsystem.repository;
import in.co.hsbc.bugtrackingsystem.entity.User;
import in.co.hsbc.bugtrackingsystem.exception.UserAlreadyExistsException;
import in.co.hsbc.bugtrackingsystem.exception.UserNotFoundException;

import java.util.List;

public interface UserDao {

        void addUser(User user)throws UserAlreadyExistsException;
        void updateUser(User user) throws UserNotFoundException;
        void deleteUser(String userId) throws UserNotFoundException;
        User getUserById(String userId);
        List<User> getAllUsers();

}
