package ToDoList;


import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class RemoveTaskFromProjectTest {

    @Test
    public void testRemoveTaskFromProject() {
        ToDoListApp toDoList = new ToDoListApp();
        LocalDate projectTargetDate = LocalDate.now().plusDays(5);
        toDoList.addProject("Test Project", projectTargetDate, "medium");
        LocalDate taskTargetDate = LocalDate.now().plusDays(3);
        Task task = new Task("Test Task", taskTargetDate, "low");
        toDoList.addTaskToProject("Test Project", task);
        toDoList.removeTaskFromProject("Test Project", 0);
        assertEquals(0, toDoList.getProjects().get("Test Project").getTasks().size());
    }
    @Test
    public void testRemoveTaskFromNonExistentProject() {
        ToDoListApp toDoList = new ToDoListApp();
        toDoList.removeTaskFromProject("Non-existent Project", 0);
        assertEquals(0, toDoList.getProjects().size());
    }
}
