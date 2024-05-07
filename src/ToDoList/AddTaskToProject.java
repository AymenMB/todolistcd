package ToDoList;


import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class AddTaskToProject {

    @Test
    public void testAddTaskToNonExistentProject() {
        ToDoListApp toDoList = new ToDoListApp();
        LocalDate targetDate = LocalDate.now().plusDays(3);
        Task task = new Task("Test Task", targetDate, "high");
        toDoList.addTaskToProject("Non-existent Project", task);
        assertEquals(0, toDoList.getProjects().size());
    }

    @Test
    public void testAddTaskToProjectWithExpiredDate() {
        ToDoListApp toDoList = new ToDoListApp();
        LocalDate projectTargetDate = LocalDate.now().plusDays(5);
        toDoList.addProject("Test Project", projectTargetDate, "medium");
        LocalDate taskTargetDate = LocalDate.now().minusDays(1); // Expired date
        Task task = new Task("Expired Task", taskTargetDate, "low");
        toDoList.addTaskToProject("Test Project", task);
        assertEquals(0, toDoList.getProjects().get("Test Project").getTasks().size());
    }
    
 
}
