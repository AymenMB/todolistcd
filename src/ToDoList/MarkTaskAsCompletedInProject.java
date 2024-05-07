package ToDoList;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class MarkTaskAsCompletedInProject {
    @Test
    public void testMarkTaskAsCompletedInProject() {
        ToDoListApp toDoList = new ToDoListApp();
        LocalDate projectTargetDate = LocalDate.now().plusDays(5);
        toDoList.addProject("Test Project", projectTargetDate, "medium");
        LocalDate taskTargetDate = LocalDate.now().plusDays(3);
        Task task = new Task("Test Task", taskTargetDate, "low");
        toDoList.addTaskToProject("Test Project", task);
        toDoList.markTaskAsCompletedInProject("Test Project", 0);
        assertTrue(toDoList.getProjects().get("Test Project").getTasks().get(0).isCompleted());
    }
}
