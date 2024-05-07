package ToDoList;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class TaskAsCompletedTest {

    @Test
    public void testMarkTaskAsCompleted() {
        ToDoListApp toDoList = new ToDoListApp();
        LocalDate targetDate = LocalDate.now().plusDays(3);
        toDoList.addTask("Test Task", targetDate, "high");
        toDoList.markTaskAsCompleted(0);
        assertTrue(toDoList.getTasks().get(0).isCompleted());
    }

    @Test
    public void testMarkProjectAsCompleted() {
        ToDoListApp toDoList = new ToDoListApp();
        LocalDate projectTargetDate = LocalDate.now().plusDays(5);
        toDoList.addProject("Test Project", projectTargetDate, "medium");
        toDoList.markProjectAsCompleted("Test Project");
        assertTrue(toDoList.getProjects().get("Test Project").isCompleted());
    }
}
