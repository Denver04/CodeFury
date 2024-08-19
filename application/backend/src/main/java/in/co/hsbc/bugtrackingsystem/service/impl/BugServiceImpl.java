package in.co.hsbc.bugtrackingsystem.service.impl;

import in.co.hsbc.bugtrackingsystem.exception.BugNotFoundException;
import in.co.hsbc.bugtrackingsystem.exception.ProjectNotFoundException;
import in.co.hsbc.bugtrackingsystem.repository.BugDao;
import in.co.hsbc.bugtrackingsystem.repository.impl.BugDaoImpl;
import in.co.hsbc.bugtrackingsystem.service.BugService;

public class BugServiceImpl implements BugService {
    BugDao bugDao = new BugDaoImpl();

    void addBug(Bug bug){
        bugDao.addBug(bug);
    }
    void updateBug(Bug bug){
        bugDao.updateBug(bug);
    }
    void deleteBug(String bugId) throws BugNotFoundException{
        try{
            bugDao.deleteBug(bugId);
        }catch (Exception e){
            throw new BugNotFoundException("Bug with id : "+bugId + " could not be deleted");
        }

    }
    Bug getBugById(String bugId) throws BugNotFoundException {
        if(bugDao.getBugById(bugId)==null){
            throw  new BugNotFoundException("Bug with id : "+bugId+"Could not be found");
        }
        return bugDao.getBugById(bugId);
    }
    List<Bug> getAllBugs(){
        return bugDao.getAllBugs();
    }
    List<Bug> getBugsByProjectId(String projectId){
        return bugDao.getBugsByProjectId(projectId);
    }

}
