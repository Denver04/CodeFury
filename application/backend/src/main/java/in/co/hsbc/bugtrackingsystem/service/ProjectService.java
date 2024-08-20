package in.co.hsbc.bugtrackingsystem.service;

import in.co.hsbc.bugtrackingsystem.entity.Project;
import in.co.hsbc.bugtrackingsystem.exception.ProjectNotFoundException;

import java.util.List;

public interface ProjectService {
    void addProject(Project project);
    void updateProject(Project project);
    void deleteProject(String projectId) throws ProjectNotFoundException;
    Project getProjectById(String projectId) throws ProjectNotFoundException;
    List<Project> getAllProjects();
}
