package in.co.hsbc.bugtrackingsystem.repository.impl;

import in.co.hsbc.bugtrackingsystem.repository.BugDao;
import in.co.hsbc.bugtrackingsystem.entity.Bug;
import in.co.hsbc.bugtrackingsystem.util.DatabaseConnection;
import in.co.hsbc.bugtrackingsystem.entity.User;
import in.co.hsbc.bugtrackingsystem.entity.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BugDaoImpl implements BugDao {

    @Override
    public void addBug(Bug bug) {
        String sql = "INSERT INTO bugs (bugId, title, description, severity, status, createdBy, assignedTo, createdOn, projectId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bug.getBugId());
            stmt.setString(2, bug.getTitle());
            stmt.setString(3, bug.getDescription());
            stmt.setString(4, bug.getSeverity().name());
            stmt.setString(5, bug.getStatus().name());
            stmt.setString(6, bug.getCreatedBy().getUserId());
            stmt.setString(7, bug.getAssignedTo().getUserId());
            stmt.setTimestamp(8, Timestamp.valueOf(bug.getCreatedOn()));
            stmt.setString(9, bug.getProject().getProjectId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBug(Bug bug) {
        String sql = "UPDATE bugs SET title = ?, description = ?, severity = ?, status = ?, assignedTo = ? WHERE bugId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bug.getTitle());
            stmt.setString(2, bug.getDescription());
            stmt.setString(3, bug.getSeverity().name());
            stmt.setString(4, bug.getStatus().name());
            stmt.setString(5, bug.getAssignedTo().getUserId());
            stmt.setString(6, bug.getBugId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBug(String bugId) {
        String sql = "DELETE FROM bugs WHERE bugId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bugId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bug getBugById(String bugId) {
        String sql = "SELECT * FROM bugs WHERE bugId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bugId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToBug(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Bug> getAllBugs() {
        List<Bug> bugs = new ArrayList<>();
        String sql = "SELECT * FROM bugs";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                bugs.add(mapResultSetToBug(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bugs;
    }

    @Override
    public List<Bug> getBugsByProjectId(String projectId) {
        List<Bug> bugs = new ArrayList<>();
        String sql = "SELECT * FROM bugs WHERE projectId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, projectId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Bug bug = mapResultSetToBug(rs);
                bugs.add(bug);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bugs;
    }

    private Bug mapResultSetToBug(ResultSet rs) throws SQLException {
        Bug bug = new Bug();
        bug.setBugId(rs.getString("bugId"));
        bug.setTitle(rs.getString("title"));
        bug.setDescription(rs.getString("description"));
        bug.setSeverity(Bug.Severity.valueOf(rs.getString("severity")));
        bug.setStatus(Bug.Status.valueOf(rs.getString("status")));
        bug.setCreatedOn(rs.getTimestamp("createdOn").toLocalDateTime());

        // Fetch related Project
        Project project = new Project();
        project.setProjectId(rs.getString("projectId"));
        bug.setProject(project);

        // Fetch createdBy User
        User createdBy = new User();
        createdBy.setUserId(rs.getString("createdBy"));
        bug.setCreatedBy(createdBy);

        // Fetch assignedTo User
        User assignedTo = new User();
        assignedTo.setUserId(rs.getString("assignedTo"));
        bug.setAssignedTo(assignedTo);

        return bug;
    }
}
