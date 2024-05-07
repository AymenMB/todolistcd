package ToDoList;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class RemoveInvalidTaskTest {

    @Test
    public void testRemoveInvalidTaskIndex() {
        ToDoListApp toDoList = new ToDoListApp();
        LocalDate targetDate = LocalDate.now().plusDays(3);
        toDoList.addTask("Test Task", targetDate, "high");
        toDoList.removeTask(5);
        assertEquals(1, toDoList.getTasks().size());
    }

    @Test
    public void testRemoveInvalidTaskFromProject() {
        ToDoListApp toDoList = new ToDoListApp();
        LocalDate projectTargetDate = LocalDate.now().plusDays(5);
        toDoList.addProject("Test Project", projectTargetDate, "medium");
        toDoList.removeTaskFromProject("Test Project", 5); 
        assertEquals(0, toDoList.getProjects().get("Test Project").getTasks().size());
    }
}
