package in.co.hsbc.bugtrackingsystem.service.impl;

import in.co.hsbc.bugtrackingsystem.entity.Project;
import in.co.hsbc.bugtrackingsystem.exception.ProjectNotFoundException;
import in.co.hsbc.bugtrackingsystem.repository.ProjectDao;
import in.co.hsbc.bugtrackingsystem.repository.impl.ProjectDaoImpl;
import in.co.hsbc.bugtrackingsystem.service.ProjectService;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {

    ProjectDao projectDao = new ProjectDaoImpl();

    @Override
    public void addProject(Project project) {
        projectDao.addProject(project);
    }

    @Override
    public void updateProject(Project project) {
        projectDao.updateProject(project);
    }

    @Override
    public void deleteProject(String projectId) throws ProjectNotFoundException{
       try{ projectDao.deleteProject(projectId);}
       catch (Exception e){
           throw new ProjectNotFoundException("Project with id : "+projectId +" Could not be deleted");
       }
    }

    @Override
    public Project getProjectById(String projectId) throws ProjectNotFoundException {
        if(projectDao.getProjectById(projectId)==null){
            throw  new ProjectNotFoundException("Project with id : "+projectId +" Could not be found");
        }
        return projectDao.getProjectById(projectId);

    }

    @Override
    public List<Project> getAllProjects() {
        return projectDao.getAllProjects();

    }
}
