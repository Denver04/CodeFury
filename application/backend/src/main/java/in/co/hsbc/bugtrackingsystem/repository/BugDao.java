package in.co.hsbc.bugtrackingsystem.repository;
import in.co.hsbc.bugtrackingsystem.entity.Bug;

import java.util.List;

public interface BugDao {
        void addBug(Bug bug);
        void updateBug(Bug bug);
        void deleteBug(String bugId);
        Bug getBugById(String bugId);
        List<Bug> getAllBugs();
        List<Bug> getBugsByProjectId(String projectId);

}
