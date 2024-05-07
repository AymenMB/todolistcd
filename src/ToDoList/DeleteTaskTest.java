package ToDoList;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteTaskTest {

    @Test
    public void testRemoveTask() {
        ToDoListApp toDoList = new ToDoListApp();
        LocalDate targetDate = LocalDate.now().plusDays(3);
        toDoList.addTask("Test Task", targetDate, "high");
        toDoList.removeTask(0);
        assertEquals(0, toDoList.getTasks().size());
    }

    @Test
    public void testRemoveProject() {
        ToDoListApp toDoList = new ToDoListApp();
        LocalDate projectTargetDate = LocalDate.now().plusDays(5);
        toDoList.addProject("Test Project", projectTargetDate, "medium");
        toDoList.removeProject("Test Project");
        assertEquals(0, toDoList.getProjects().size());
    }
}
