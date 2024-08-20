package in.co.hsbc.bugtrackingsystem.service;

import in.co.hsbc.bugtrackingsystem.entity.Bug;
import in.co.hsbc.bugtrackingsystem.exception.BugNotFoundException;

import java.util.List;

public interface BugService {
    void addBug(Bug bug);
    void updateBug(Bug bug);
    void deleteBug(String bugId) throws BugNotFoundException;
    Bug getBugById(String bugId) throws BugNotFoundException;
    List<Bug> getAllBugs();
    List<Bug> getBugsByProjectId(String projectId);
    void assignBug(String userId, String bugId);
}
