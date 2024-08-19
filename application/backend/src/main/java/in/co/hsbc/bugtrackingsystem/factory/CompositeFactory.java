package in.co.hsbc.bugtrackingsystem.factory;

import in.co.hsbc.bugtrackingsystem.entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CompositeFactory {

    // Method to create a user with default lastLogin time as now
    public static User createUserWithCurrentLogin(String userId, String name, String email, User.Role role) {
        return EntityFactory.createUser(userId, name, email, role, LocalDateTime.now());
    }

    // Method to create a project with current date as the start date and default status as InProgress
    public static Project createNewProject(String projectId, String projectName, User projectManager, List<User> teamMembers) {
        return EntityFactory.createProject(projectId, projectName, LocalDate.now(), Project.Status.IN_PROGRESS, projectManager, teamMembers);
    }

    // Method to create a bug and assign it to a developer
    public static Bug createNewBug(String bugId, String title, String description, Bug.Severity severity, User createdBy, User assignedTo, Project project) {
        return EntityFactory.createBug(bugId, title, description, severity, Bug.Status.OPEN, LocalDateTime.now(), createdBy, assignedTo, project);
    }

    // Method to assign a user to a project
    public static void assignUserToProject(User user, Project project) {
        List<User> teamMembers = project.getTeamMembers();
        if (user.getRole() == User.Role.DEVELOPER || user.getRole() == User.Role.TESTER) {
            teamMembers.add(user);
            project.setTeamMembers(teamMembers);
            user.getAssignedProjects().add(project);
        }
    }

    // Method for a tester to report a bug
    public static Bug reportBug(User tester, Project project, String title, String description, Bug.Severity severity) {
        if (tester.getRole() != User.Role.TESTER) {
            throw new IllegalArgumentException("Only testers can report bugs.");
        }
        Bug bug = EntityFactory.createBug("bug-" + System.currentTimeMillis(), title, description, severity, Bug.Status.OPEN, LocalDateTime.now(), tester, null, project);
        tester.getReportedBugs().add(bug);
        project.getBugs().add(bug);
        return bug;
    }
}
