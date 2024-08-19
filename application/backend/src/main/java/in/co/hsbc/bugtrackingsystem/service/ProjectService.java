package in.co.hsbc.bugtrackingsystem.service;

import in.co.hsbc.bugtrackingsystem.exception.ProjectNotFoundException;

public interface ProjectService {
    void addProject(Project project);
    void updateProject(Project project);
    void deleteProject(String projectId) throws ProjectNotFoundException;
    Project getProjectById(String projectId) throws ProjectNotFoundException;
    List<Project> getAllProjects();
}
