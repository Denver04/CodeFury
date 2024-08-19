package in.co.hsbc.bugtrackingsystem.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    public User() {

    }

    public enum Role {
        PROJECT_MANAGER, DEVELOPER, TESTER
    }

    private String userId;
    private String name;
    private String email;
    private Role role;
    private LocalDateTime lastLogin;

    // Associations
    private List<Project> managedProjects = new ArrayList<>(); // Projects managed by the user
    private List<Project> assignedProjects = new ArrayList<>(); // Projects assigned to the user (as Developer or Tester)
    private List<Bug> reportedBugs = new ArrayList<>(); // Bugs reported by the user (Tester)
    private List<Bug> assignedBugs = new ArrayList<>(); // Bugs assigned to the user (Developer)

    public User(String userId, String name, String email, Role role, LocalDateTime lastLogin) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.lastLogin = lastLogin;
    }
    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", lastLogin=" + lastLogin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    // Getters and Setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public List<Project> getManagedProjects() {
        return managedProjects;
    }

    public void setManagedProjects(List<Project> managedProjects) {
        this.managedProjects = managedProjects;
    }

    public List<Project> getAssignedProjects() {
        return assignedProjects;
    }

    public void setAssignedProjects(List<Project> assignedProjects) {
        this.assignedProjects = assignedProjects;
    }

    public List<Bug> getReportedBugs() {
        return reportedBugs;
    }

    public void setReportedBugs(List<Bug> reportedBugs) {
        this.reportedBugs = reportedBugs;
    }

    public List<Bug> getAssignedBugs() {
        return assignedBugs;
    }

    public void setAssignedBugs(List<Bug> assignedBugs) {
        this.assignedBugs = assignedBugs;
    }
}
