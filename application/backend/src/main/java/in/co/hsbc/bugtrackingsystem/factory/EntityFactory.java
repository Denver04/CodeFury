package in.co.hsbc.bugtrackingsystem.factory;

import in.co.hsbc.bugtrackingsystem.entity.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class EntityFactory {

    // Factory method to create a User
    public static User createUser(String userId, String name, String email, User.Role role, LocalDateTime lastLogin) {
        return new User(userId, name, email, role, lastLogin);
    }

    // Factory method to create a Project
    public static Project createProject(String projectId, String projectName, LocalDate startDate, Project.Status status, User projectManager, List<User> teamMembers) {
        Project project = new Project(projectId, projectName, startDate, status, projectManager);
        project.setTeamMembers(teamMembers);
        return project;
    }

    // Factory method to create a Bug
    public static Bug createBug(String bugId, String title, String description, Bug.Severity severity, Bug.Status status, LocalDateTime createdOn, User createdBy, User assignedTo, Project project) {
        return new Bug(bugId, title, description, severity, status, createdOn, createdBy, assignedTo, project);
    }
}
