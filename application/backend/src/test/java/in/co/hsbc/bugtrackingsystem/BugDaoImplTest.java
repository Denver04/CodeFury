package in.co.hsbc.bugtrackingsystem;

import in.co.hsbc.bugtrackingsystem.entity.Project;
import in.co.hsbc.bugtrackingsystem.entity.Bug;
import in.co.hsbc.bugtrackingsystem.entity.User;

import in.co.hsbc.bugtrackingsystem.repository.impl.BugDaoImpl;
import in.co.hsbc.bugtrackingsystem.util.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@DisplayName("BugDao Test Cases")
public class BugDaoImplTest {

    private BugDaoImpl bugDao;
    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException {
        bugDao = new BugDaoImpl();
        connection = DatabaseConnection.getConnection();
        clearBugsTable();
    }

    private void clearBugsTable() {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM bugs")) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @DisplayName("Add bug test case")
    @Test
    public void testAddBug() {
        Bug bug = new Bug();
        bug.setBugId("B001");
        bug.setTitle("Null Pointer Exception");
        bug.setDescription("Occurs when accessing a null object");
        bug.setSeverity(Bug.Severity.HIGH);
        bug.setStatus(Bug.Status.OPEN);
        bug.setCreatedOn(LocalDateTime.now());

        User createdBy = new User();
        createdBy.setUserId("U001");
        bug.setCreatedBy(createdBy);

        User assignedTo = new User();
        assignedTo.setUserId("U002");
        bug.setAssignedTo(assignedTo);

        Project project = new Project();
        project.setProjectId("P001");
        bug.setProject(project);

        bugDao.addBug(bug);

        Bug retrievedBug = bugDao.getBugById("B001");
        assertNotNull(retrievedBug);
        assertEquals("Null Pointer Exception", retrievedBug.getTitle());
    }
    @DisplayName("Update bug test case")
    @Test
    public void testUpdateBug() {
        Bug bug = new Bug();
        bug.setBugId("B001");
        bug.setTitle("Null Pointer Exception");
        bug.setDescription("Occurs when accessing a null object");
        bug.setSeverity(Bug.Severity.HIGH);
        bug.setStatus(Bug.Status.OPEN);
        bug.setCreatedOn(LocalDateTime.now());

        User createdBy = new User();
        createdBy.setUserId("U001");
        bug.setCreatedBy(createdBy);

        User assignedTo = new User();
        assignedTo.setUserId("U002");
        bug.setAssignedTo(assignedTo);

        Project project = new Project();
        project.setProjectId("P001");
        bug.setProject(project);

        bugDao.addBug(bug);

        bug.setTitle("Updated Title");
        bug.setDescription("Updated Description");
        bugDao.updateBug(bug);

        Bug updatedBug = bugDao.getBugById("B001");
        assertEquals("Updated Title", updatedBug.getTitle());
        assertEquals("Updated Description", updatedBug.getDescription());
    }
    @DisplayName("Delete bug test case")
    @Test
    public void testDeleteBug() {
        Bug bug = new Bug();
        bug.setBugId("B001");
        bug.setTitle("Bug to Delete");
        bug.setDescription("This bug will be deleted");
        bug.setSeverity(Bug.Severity.MEDIUM);
        bug.setStatus(Bug.Status.OPEN);
        bug.setCreatedOn(LocalDateTime.now());

        User createdBy = new User();
        createdBy.setUserId("U001");
        bug.setCreatedBy(createdBy);

        User assignedTo = new User();
        assignedTo.setUserId("U002");
        bug.setAssignedTo(assignedTo);

        Project project = new Project();
        project.setProjectId("P001");
        bug.setProject(project);

        bugDao.addBug(bug);

        bugDao.deleteBug("B001");

        Bug deletedBug = bugDao.getBugById("B001");
        assertNull(deletedBug);
    }

    @DisplayName("Test bug by id bug test case")
    @Test
    public void testGetBugById() {
        Bug bug = new Bug();
        bug.setBugId("B001");
        bug.setTitle("Null Pointer Exception");
        bug.setDescription("Occurs when accessing a null object");
        bug.setSeverity(Bug.Severity.HIGH);
        bug.setStatus(Bug.Status.OPEN);
        bug.setCreatedOn(LocalDateTime.now());

        User createdBy = new User();
        createdBy.setUserId("U001");
        bug.setCreatedBy(createdBy);

        User assignedTo = new User();
        assignedTo.setUserId("U002");
        bug.setAssignedTo(assignedTo);

        Project project = new Project();
        project.setProjectId("P001");
        bug.setProject(project);

        bugDao.addBug(bug);

        Bug retrievedBug = bugDao.getBugById("B001");
        assertNotNull(retrievedBug);
        assertEquals("B001", retrievedBug.getBugId());
    }
    @DisplayName("testGetAllBugs")
    @Test
    public void testGetAllBugs() {
        Bug bug1 = new Bug();
        bug1.setBugId("B001");
        bug1.setTitle("Bug One");
        bug1.setDescription("First bug");
        bug1.setSeverity(Bug.Severity.LOW);
        bug1.setStatus(Bug.Status.OPEN);
        bug1.setCreatedOn(LocalDateTime.now());

        User createdBy1 = new User();
        createdBy1.setUserId("U001");
        bug1.setCreatedBy(createdBy1);

        User assignedTo1 = new User();
        assignedTo1.setUserId("U002");
        bug1.setAssignedTo(assignedTo1);

        Project project1 = new Project();
        project1.setProjectId("P001");
        bug1.setProject(project1);

        Bug bug2 = new Bug();
        bug2.setBugId("B002");
        bug2.setTitle("Bug Two");
        bug2.setDescription("Second bug");
        bug2.setSeverity(Bug.Severity.MEDIUM);
        bug2.setStatus(Bug.Status.OPEN);
        bug2.setCreatedOn(LocalDateTime.now());

        User createdBy2 = new User();
        createdBy2.setUserId("U001");
        bug2.setCreatedBy(createdBy2);

        User assignedTo2 = new User();
        assignedTo2.setUserId("U003");
        bug2.setAssignedTo(assignedTo2);

        Project project2 = new Project();
        project2.setProjectId("P002");
        bug2.setProject(project2);

        bugDao.addBug(bug1);
        bugDao.addBug(bug2);

        List<Bug> bugs = bugDao.getAllBugs();
        assertEquals(2, bugs.size());
    }
}





