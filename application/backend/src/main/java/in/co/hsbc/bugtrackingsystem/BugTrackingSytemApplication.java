
package in.co.hsbc.bugtrackingsystem;

import in.co.hsbc.bugtrackingsystem.entity.*;
import in.co.hsbc.bugtrackingsystem.exception.ProjectNotFoundException;
import in.co.hsbc.bugtrackingsystem.exception.UserNotFoundException;
import in.co.hsbc.bugtrackingsystem.factory.CompositeFactory;
import in.co.hsbc.bugtrackingsystem.service.impl.BugServiceImpl;
import in.co.hsbc.bugtrackingsystem.service.impl.ProjectServiceImpl;
import in.co.hsbc.bugtrackingsystem.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BugTrackingSystemApplication {
    public static void main(String[] args) throws UserNotFoundException, ProjectNotFoundException {
        Scanner scanner = new Scanner(System.in);
        UserServiceImpl userService = new UserServiceImpl();
        ProjectServiceImpl projectService = new ProjectServiceImpl();
        BugServiceImpl bugService = new BugServiceImpl();

        while (true) {
            System.out.println("Welcome to Bug Tracking System");
            System.out.println("===============================");
            System.out.println("1. Create User");
            System.out.println("2. Create Project");
            System.out.println("3. Assign User to Project");
            System.out.println("4. Report Bug");
            System.out.println("5. View All Users");
            System.out.println("6. View All Projects");
            System.out.println("7. View All Bugs in a Project");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter User ID: ");
                    String userId = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Role (1-Project Manager, 2-Developer, 3-Tester): ");
                    int roleChoice = scanner.nextInt();
                    User.Role role = roleChoice == 1 ? User.Role.PROJECT_MANAGER :
                            roleChoice == 2 ? User.Role.DEVELOPER :
                                    User.Role.TESTER;
                    User user = CompositeFactory.createUserWithCurrentLogin(userId, name, email, role);
                    userService.addUser(user);
                    System.out.println("User created successfully!\n");
                    break;

                case 2:
                    System.out.print("Enter Project ID: ");
                    String projectId = scanner.nextLine();
                    System.out.print("Enter Project Name: ");
                    String projectName = scanner.nextLine();
                    System.out.print("Enter Project Manager ID: ");
                    String managerId = scanner.nextLine();
                    User projectManager = userService.getUserById(managerId);

                    if (projectManager == null || projectManager.getRole() != User.Role.PROJECT_MANAGER) {
                        System.out.println("Invalid Project Manager ID.\n");
                        break;
                    }

                    List<User> teamMembers = new ArrayList<>();
                    String addMore;
                    do {
                        System.out.print("Enter Team Member ID (Developer/Tester): ");
                        String memberId = scanner.nextLine();
                        User teamMember = userService.getUserById(memberId);
                        if (teamMember != null && (teamMember.getRole() == User.Role.DEVELOPER || teamMember.getRole() == User.Role.TESTER)) {
                            teamMembers.add(teamMember);
                        } else {
                            System.out.println("Invalid Team Member ID.");
                        }
                        System.out.print("Add another team member? (y/n): ");
                        addMore = scanner.nextLine();
                    } while (addMore.equalsIgnoreCase("y"));

                    Project project = CompositeFactory.createNewProject(projectId, projectName, projectManager, teamMembers);
                    projectService.addProject(project);
                    System.out.println("Project created successfully!\n");
                    break;

                case 3:
                    System.out.print("Enter User ID to assign: ");
                    String assignUserId = scanner.nextLine();
                    System.out.print("Enter Project ID: ");
                    String assignProjectId = scanner.nextLine();
                    User assignUser = userService.getUserById(assignUserId);
                    Project assignProject = projectService.getProjectById(assignProjectId);
                    if (assignUser != null && assignProject != null) {
                        CompositeFactory.assignUserToProject(assignUser, assignProject);
                        System.out.println("User assigned to project successfully!\n");
                    } else {
                        System.out.println("Invalid User or Project ID.\n");
                    }
                    break;

                case 4:
                    System.out.print("Enter Tester ID: ");
                    String testerId = scanner.nextLine();
                    User tester = userService.getUserById(testerId);
                    if (tester == null || tester.getRole() != User.Role.TESTER) {
                        System.out.println("Invalid Tester ID.\n");
                        break;
                    }
                    System.out.print("Enter Project ID: ");
                    String bugProjectId = scanner.nextLine();
                    Project bugProject = projectService.getProjectById(bugProjectId);
                    if (bugProject == null) {
                        System.out.println("Invalid Project ID.\n");
                        break;
                    }
                    System.out.print("Enter Bug Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Bug Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Bug Severity (1-Low, 2-Medium, 3-High): ");
                    int severityChoice = scanner.nextInt();
                    Bug.Severity severity = severityChoice == 1 ? Bug.Severity.LOW :
                            severityChoice == 2 ? Bug.Severity.MEDIUM :
                                    Bug.Severity.HIGH;
                    Bug bug = CompositeFactory.reportBug(tester, bugProject, title, description, severity);
                    bugService.addBug(bug);
                    System.out.println("Bug reported successfully!\n");
                    break;

                case 5:
                    System.out.println("All Users:");
                    for (User u : userService.getAllUsers()) {
                        System.out.println(u);
                    }
                    System.out.println();
                    break;

                case 6:
                    System.out.println("All Projects:");
                    for (Project p : projectService.getAllProjects()) {
                        System.out.println(p);
                    }
                    System.out.println();
                    break;

                case 7:
                    System.out.print("Enter Project ID to view bugs: ");
                    String viewProjectId = scanner.nextLine();
                    List<Bug> bugs = bugService.getBugsByProjectId(viewProjectId);
                    if (bugs.isEmpty()) {
                        System.out.println("No bugs found for this project.\n");
                    } else {
                        System.out.println("Bugs in Project " + viewProjectId + ":");
                        for (Bug b : bugs) {
                            System.out.println(b);
                        }
                        System.out.println();
                    }
                    break;

                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option, please try again.\n");
            }
        }
    }
}