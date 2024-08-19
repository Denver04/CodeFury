package in.co.hsbc.bugtrackingsystem.repository.impl;

import in.co.hsbc.bugtrackingsystem.repository.ProjectDao;
import in.co.hsbc.bugtrackingsystem.entity.Project;
import in.co.hsbc.bugtrackingsystem.util.DatabaseConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {

    @Override
    public void addProject(Project project) {
        String sql = "INSERT INTO projects (projectId, projectName, startDate, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, project.getProjectId());
            stmt.setString(2, project.getProjectName());
            stmt.setDate(3, Date.valueOf(project.getStartDate()));
            stmt.setString(4, project.getStatus().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProject(Project project) {
        String sql = "UPDATE projects SET projectName = ?, startDate = ?, status = ? WHERE projectId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, project.getProjectName());
            stmt.setDate(2, Date.valueOf(project.getStartDate()));
            stmt.setString(3, project.getStatus().name());
            stmt.setString(4, project.getProjectId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProject(String projectId) {
        String sql = "DELETE FROM projects WHERE projectId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, projectId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project getProjectById(String projectId) {
        String sql = "SELECT * FROM projects WHERE projectId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, projectId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Project(
                        rs.getString("projectId"),
                        rs.getString("projectName"),
                        rs.getDate("startDate").toLocalDate(),
                        Project.Status.valueOf(rs.getString("status"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM projects";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                projects.add(new Project(
                        rs.getString("projectId"),
                        rs.getString("projectName"),
                        rs.getDate("startDate").toLocalDate(),
                        Project.Status.valueOf(rs.getString("status"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }
}
