package in.co.hsbc.bugtrackingsystem.service.impl;

import in.co.hsbc.bugtrackingsystem.entity.User;
import in.co.hsbc.bugtrackingsystem.exception.UserNotFoundException;
import in.co.hsbc.bugtrackingsystem.repository.UserDao;
import in.co.hsbc.bugtrackingsystem.repository.impl.UserDaoImpl;
import in.co.hsbc.bugtrackingsystem.service.UserService;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
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
