package in.co.hsbc.bugtrackingsystem;
import in.co.hsbc.bugtrackingsystem.repository.impl.ProjectDaoImpl;
import in.co.hsbc.bugtrackingsystem.util.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ProjectDaoImplTest {

    private ProjectDaoImpl projectDao;
    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException {
        projectDao = new ProjectDaoImpl();
        connection = DatabaseConnection.getConnection();
        clearProjectsTable();
    }

    private void clearProjectsTable() {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM projects")) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddProject() {
        Project project = new Project();
        project.setProjectId("P001");
        project.setProjectName("Bug Tracking System");
        project.setStartDate(LocalDate.of(2023, 1, 1));
        project.setStatus(Project.Status.IN_PROGRESS);

        projectDao.addProject(project);

        Project retrievedProject = projectDao.getProjectById("P001");
        assertNotNull(retrievedProject);
        assertEquals("Bug Tracking System", retrievedProject.getProjectName());
    }


    @Test
    public void testUpdateProject() {
        Project project = new Project();
        project.setProjectId("P001");
        project.setProjectName("Bug Tracking System");
        project.setStartDate(LocalDate.of(2023, 1, 1));
        project.setStatus(Project.Status.IN_PROGRESS);

        projectDao.addProject(project);

        project.setProjectName("Updated Project Name");
        project.setStatus(Project.Status.COMPLETED);
        projectDao.updateProject(project);

        Project updatedProject = projectDao.getProjectById("P001");
        assertEquals("Updated Project Name", updatedProject.getProjectName());
        assertEquals(Project.Status.COMPLETED, updatedProject.getStatus());
    }


    @Test
    public void testDeleteProject() {
        Project project = new Project();
        project.setProjectId("P001");
        project.setProjectName("Project to Delete");
        project.setStartDate(LocalDate.of(2023, 1, 1));
        project.setStatus(Project.Status.IN_PROGRESS);

        projectDao.addProject(project);

        projectDao.deleteProject("P001");

        Project deletedProject = projectDao.getProjectById("P001");
        assertNull(deletedProject);
    }


    @Test
    public void testGetProjectById() {
        Project project = new Project();
        project.setProjectId("P001");
        project.setProjectName("Bug Tracking System");
        project.setStartDate(LocalDate.of(2023, 1, 1));
        project.setStatus(Project.Status.IN_PROGRESS);

        projectDao.addProject(project);

        Project retrievedProject = projectDao.getProjectById("P001");
        assertNotNull(retrievedProject);
        assertEquals("P001", retrievedProject.getProjectId());
        assertEquals("Bug Tracking System", retrievedProject.getProjectName());
    }



    @Test
    public void testGetAllProjects() {
        Project project1 = new Project();
        project1.setProjectId("P001");
        project1.setProjectName("Project One");
        project1.setStartDate(LocalDate.of(2023, 1, 1));
        project1.setStatus(Project.Status.IN_PROGRESS);

        Project project2 = new Project();
        project2.setProjectId("P002");
        project2.setProjectName("Project Two");
        project2.setStartDate(LocalDate.of(2023, 2, 1));
        project2.setStatus(Project.Status.NOT_STARTED);

        projectDao.addProject(project1);
        projectDao.addProject(project2);

        List<Project> projects = projectDao.getAllProjects();
        assertEquals(2, projects.size());
        assertEquals("Project One", projects.get(0).getProjectName());
        assertEquals("Project Two", projects.get(1).getProjectName());
    }
}



