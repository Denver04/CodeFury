package in.co.hsbc.bugtrackingsystem;

import in.co.hsbc.bugtrackingsystem.entity.*;
import in.co.hsbc.bugtrackingsystem.factory.CompositeFactory;
import in.co.hsbc.bugtrackingsystem.service.impl.BugServiceImpl;
import in.co.hsbc.bugtrackingsystem.service.impl.ProjectServiceImpl;
import in.co.hsbc.bugtrackingsystem.service.impl.UserServiceImpl;
import in.co.hsbc.bugtrackingsystem.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserServiceImpl userService = new UserServiceImpl();
        ProjectServiceImpl projectService = new ProjectServiceImpl();
        BugServiceImpl bugService = new BugServiceImpl();
        View view = new View();

        while (true) {
            view.printHeader();
            view.printMenu();
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    view.printMessage("Enter User ID: ");
                    String userId = scanner.nextLine();
                    view.printMessage("Enter Name: ");
                    String name = scanner.nextLine();
                    view.printMessage("Enter Email: ");
                    String email = scanner.nextLine();
                    view.printMessage("Enter Role (1-Project Manager, 2-Developer, 3-Tester): ");
                    int roleChoice = scanner.nextInt();
                    User.Role role = roleChoice == 1 ? User.Role.PROJECT_MANAGER :
                            roleChoice == 2 ? User.Role.DEVELOPER :
                                    User.Role.TESTER;
                    User user = CompositeFactory.createUserWithCurrentLogin(userId, name, email, role);
                    userService.addUser(user);
                    view.printMessage("User created successfully!\n");
                    break;

                case 2:
                    view.printMessage("Enter Project ID: ");
                    String projectId = scanner.nextLine();
                    view.printMessage("Enter Project Name: ");
                    String projectName = scanner.nextLine();
                    view.printMessage("Enter Project Manager ID: ");
                    String managerId = scanner.nextLine();
                    User projectManager = userService.getUserById(managerId);

                    if (projectManager == null || projectManager.getRole() != User.Role.PROJECT_MANAGER) {
                        view.printMessage("Invalid Project Manager ID.\n");
                        break;
                    }

                    List<User> teamMembers = new ArrayList<>();
                    String addMore;
                    do {
                        view.printMessage("Enter Team Member ID (Developer/Tester): ");
                        String memberId = scanner.nextLine();
                        User teamMember = userService.getUserById(memberId);
                        if (teamMember != null && (teamMember.getRole() == User.Role.DEVELOPER || teamMember.getRole() == User.Role.TESTER)) {
                            teamMembers.add(teamMember);
                        } else {
                            view.printMessage("Invalid Team Member ID.");
                        }
                        view.printMessage("Add another team member? (y/n): ");
                        addMore = scanner.nextLine();
                    } while (addMore.equalsIgnoreCase("y"));

                    Project project = CompositeFactory.createNewProject(projectId, projectName, projectManager, teamMembers);
                    projectService.addProject(project);
                    view.printMessage("Project created successfully!\n");
                    break;

                case 3:
                    view.printMessage("Enter User ID to assign: ");
                    String assignUserId = scanner.nextLine();
                    view.printMessage("Enter Project ID: ");
                    String assignProjectId = scanner.nextLine();
                    User assignUser = userService.getUserById(assignUserId);
                    Project assignProject = projectService.getProjectById(assignProjectId);
                    if (assignUser != null && assignProject != null) {
                        CompositeFactory.assignUserToProject(assignUser, assignProject);
                        view.printMessage("User assigned to project successfully!\n");
                    } else {
                        view.printMessage("Invalid User or Project ID.\n");
                    }
                    break;

                case 4:
                    view.printMessage("Enter Tester ID: ");
                    String testerId = scanner.nextLine();
                    User tester = userService.getUserById(testerId);
                    if (tester == null || tester.getRole() != User.Role.TESTER) {
                        view.printMessage("Invalid Tester ID.\n");
                        break;
                    }
                    view.printMessage("Enter Project ID: ");
                    String bugProjectId = scanner.nextLine();
                    Project bugProject = projectService.getProjectById(bugProjectId);
                    if (bugProject == null) {
                        view.printMessage("Invalid Project ID.\n");
                        break;
                    }
                    view.printMessage("Enter Bug Title: ");
                    String title = scanner.nextLine();
                    view.printMessage("Enter Bug Description: ");
                    String description = scanner.nextLine();
                    view.printMessage("Enter Bug Severity (1-Low, 2-Medium, 3-High): ");
                    int severityChoice = scanner.nextInt();
                    Bug.Severity severity = severityChoice == 1 ? Bug.Severity.LOW :
                            severityChoice == 2 ? Bug.Severity.MEDIUM :
                                    Bug.Severity.HIGH;
                    Bug bug = CompositeFactory.reportBug(tester, bugProject, title, description, severity);
                    bugService.addBug(bug);
                    view.printMessage("Bug reported successfully!\n");
                    break;

                case 5:
                    view.printMessage("All Users:");
                    for (User u : userService.getAllUsers()) {
                        view.printMessage(u.toString());
                    }
                    view.printMessage("");
                    break;

                case 6:
                    view.printMessage("All Projects:");
                    for (Project p : projectService.getAllProjects()) {
                        view.printMessage(p.toString());
                    }
                    view.printMessage("");
                    break;

                case 7:
                    view.printMessage("Enter Project ID to view bugs: ");
                    String viewProjectId = scanner.nextLine();
                    List<Bug> bugs = bugService.getBugsByProjectId(viewProjectId);
                    if (bugs.isEmpty()) {
                        view.printMessage("No bugs found for this project.\n");
                    } else {
                        view.printMessage("Bugs in Project " + viewProjectId + ":");
                        for (Bug b : bugs) {
                            view.printMessage(b.toString());
                        }
                        view.printMessage("");
                    }
                    break;

                case 8:
                    view.printMessage("Exiting...Thank you! Visit Again");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    view.printMessage("Invalid option, please try again.\n");
            }
        }

    }
}