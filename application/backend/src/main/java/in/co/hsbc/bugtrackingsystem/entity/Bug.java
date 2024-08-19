package in.co.hsbc.bugtrackingsystem.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Bug implements Comparable<Bug>{
    public Bug() {

    }

    public enum Severity {
        LOW, MEDIUM, HIGH, CRITICAL
    }

    public enum Status {
        OPEN, IN_PROGRESS, CLOSED
    }

    private String bugId;
    private String title;
    private String description;
    private Severity severity;
    private Status status;
    private LocalDateTime createdOn;

    // Associations
    private User createdBy; // The user who reported the bug
    private User assignedTo; // The user who is assigned to fix the bug
    private Project project; // The project to which the bug belongs

    public Bug(String bugId, String title, String description, Severity severity, Status status, LocalDateTime createdOn, User createdBy, User assignedTo, Project project) {
        this.bugId = bugId;
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.status = status;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
        this.project = project;
    }
    @Override
    public String toString() {
        return "Bug{" +
                "bugId='" + bugId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", severity=" + severity +
                ", status=" + status +
                ", createdOn=" + createdOn +
                ", createdBy=" + createdBy +
                ", assignedTo=" + assignedTo +
                ", project=" + project +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bug bug = (Bug) o;
        return Objects.equals(bugId, bug.bugId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bugId);
    }

    @Override
    public int compareTo(Bug other) {
        // Comparison based on severity first, then createdOn timestamp if severities are the same
        int severityComparison = this.severity.compareTo(other.severity);
        if (severityComparison != 0) {
            return severityComparison;
        }
        return this.createdOn.compareTo(other.createdOn);
    }

    // Getters and Setters

    public String getBugId() {
        return bugId;
    }

    public void setBugId(String bugId) {
        this.bugId = bugId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
