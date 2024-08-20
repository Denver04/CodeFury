package in.co.hsbc.bugtrackingsystem.service.impl;

import in.co.hsbc.bugtrackingsystem.entity.Bug;
import in.co.hsbc.bugtrackingsystem.entity.User;
import in.co.hsbc.bugtrackingsystem.exception.BugNotFoundException;
import in.co.hsbc.bugtrackingsystem.repository.BugDao;
import in.co.hsbc.bugtrackingsystem.repository.UserDao;
import in.co.hsbc.bugtrackingsystem.repository.impl.BugDaoImpl;
import in.co.hsbc.bugtrackingsystem.repository.impl.UserDaoImpl;
import in.co.hsbc.bugtrackingsystem.service.BugService;

import java.util.List;

public class BugServiceImpl implements BugService {
    private BugDao bugDao = new BugDaoImpl();
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void addBug(Bug bug){
        bugDao.addBug(bug);
    }
    @Override
    public void updateBug(Bug bug){
        bugDao.updateBug(bug);
    }
    @Override
    public void deleteBug(String bugId) throws BugNotFoundException{
        try{
            bugDao.deleteBug(bugId);
        }catch (Exception e){
            throw new BugNotFoundException("Bug with id : "+bugId + " could not be deleted");
        }

    }
    @Override
   public Bug getBugById(String bugId) throws BugNotFoundException {
        if(bugDao.getBugById(bugId)==null){
            throw  new BugNotFoundException("Bug with id : "+bugId+"Could not be found");
        }
        return bugDao.getBugById(bugId);
    }
    @Override
    public List<Bug> getAllBugs(){
        return bugDao.getAllBugs();
    }
    @Override
    public List<Bug> getBugsByProjectId(String projectId){
        return bugDao.getBugsByProjectId(projectId);
    }

    @Override
    public void assignBug(String userId,String bugId){

        Bug bug = bugDao.getBugById(bugId);
        User user = userDao.getUserById(userId);
        if(user.getRole()!= User.Role.DEVELOPER){
            throw new IllegalArgumentException("User is not a developer");
        }
        bug.setAssignedTo(user);

        bugDao.addBug(bug);

    }

    public void closeBug(String bugId) throws BugNotFoundException{
        Bug bug = bugDao.getBugById(bugId);
        bug.setStatus(Bug.Status.CLOSED);
    }

}
