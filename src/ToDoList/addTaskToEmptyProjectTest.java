package ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
public class addTaskToEmptyProjectTest {

    private ToDoListApp toDoList;

    @BeforeEach
    public void setUp() {
        toDoList = new ToDoListApp();
    }
    @Test
    public void addTaskToEmptyProject() {
        
        toDoList.addTaskToProject("ProjectA", new Task("", LocalDate.now(), ""));
              assertEquals(0, toDoList.getTasks().size()); 
        assertEquals(1, toDoList.getProjects().size()); 
        Project project = toDoList.getProjects().get("ProjectA");
        assertNotNull(project); 
        assertEquals(1, project.getTasks().size());
    }
    
    @Test
    public void addEmptyTaskToEmptyProjectTest() {
        
        toDoList.addTaskToProject("ProjectA", new Task("", LocalDate.now(), ""));
        
    
        assertEquals(0, toDoList.getTasks().size()); 
        assertEquals(1, toDoList.getProjects().size()); 
        Project project = toDoList.getProjects().get("ProjectA");
        assertNotNull(project); 
        assertEquals(1, project.getTasks().size()); 
    }
    
    @Test
    public void addTaskToProjectWithoutTask() {
        ToDoListApp toDoList = new ToDoListApp();
        toDoList.addProject("Project 1", LocalDate.now(), "High");

       
        assertThrows(NullPointerException.class, () -> toDoList.addTaskToProject(null, null));
    }
    
    @Test
    public void addTaskToProjectWithInvalidDate() {
        ToDoListApp toDoList = new ToDoListApp();
        toDoList.addProject("Project 1", LocalDate.now(), "High");

   
        assertThrows(java.time.format.DateTimeParseException.class, () ->
                toDoList.addTaskToProject("Project 1", new Task("Task 1", LocalDate.parse("invalid_date"), "High")));
    }
}
