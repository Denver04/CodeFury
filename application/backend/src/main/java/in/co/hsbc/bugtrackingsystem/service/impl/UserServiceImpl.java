package in.co.hsbc.bugtrackingsystem.service.impl;

import in.co.hsbc.bugtrackingsystem.entity.User;
import in.co.hsbc.bugtrackingsystem.exception.UserAlreadyExistsException;
import in.co.hsbc.bugtrackingsystem.exception.UserNotFoundException;
import in.co.hsbc.bugtrackingsystem.repository.UserDao;
import in.co.hsbc.bugtrackingsystem.repository.impl.UserDaoImpl;
import in.co.hsbc.bugtrackingsystem.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public void addUser(User user) throws UserAlreadyExistsException {
        try {
            userDao.addUser(user);
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException("User Already Exists");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(User user) throws UserNotFoundException {
        try {
            userDao.updateUser(user);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("User does not exist");
        }
    }

    @Override
    public void deleteUser(String userId) throws UserNotFoundException{
        try{
            userDao.deleteUser(userId);
        } catch (Exception e) {
            throw new UserNotFoundException("User with id : "+userId +" Could not be deleted");
        }
    }

    @Override
    public User getUserById(String userId) throws UserNotFoundException {



           if(userDao.getUserById(userId)!=null){
               return userDao.getUserById(userId);
           }

           throw new UserNotFoundException("User with id " + userId + " NOT Found");



    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();

    }
}
