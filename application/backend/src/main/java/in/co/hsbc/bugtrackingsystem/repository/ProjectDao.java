package in.co.hsbc.bugtrackingsystem.repository;
import in.co.hsbc.bugtrackingsystem.entity.Project;

import java.util.List;

public interface ProjectDao {
    void addProject(Project project);
    void updateProject(Project project);
    void deleteProject(String projectId);
    Project getProjectById(String projectId);
    List<Project> getAllProjects();

}
