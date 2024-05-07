package ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class AddTaskTest {
    private ToDoListApp toDoList;

    @BeforeEach
    public void setUp() {
        toDoList = new ToDoListApp();
    }

    @Test
    public void testAddTask() {
        LocalDate targetDate = LocalDate.now().plusDays(3);
        toDoList.addTask("Test Task", targetDate, "high");
        ArrayList<Task> tasks = toDoList.getTasks();
        assertEquals(1, tasks.size());
        assertEquals("Test Task", tasks.get(0).getDescription());
        assertEquals(targetDate, tasks.get(0).getTargetCompletionDate());
        assertEquals("high", tasks.get(0).getPriorityLevel());
    }

    @Test
    public void testAddTaskToProject() {
        LocalDate projectTargetDate = LocalDate.now().plusDays(5);
        toDoList.addProject("Test Project", projectTargetDate, "medium");
        LocalDate taskTargetDate = LocalDate.now().plusDays(3);
        Task task = new Task("Test Task", taskTargetDate, "low");
        toDoList.addTaskToProject("Test Project", task);
        HashMap<String, Project> projects = toDoList.getProjects();
        assertEquals(1, projects.size());
        assertEquals(1, projects.get("Test Project").getTasks().size());
        assertEquals("Test Task", projects.get("Test Project").getTasks().get(0).getDescription());
        assertEquals(taskTargetDate, projects.get("Test Project").getTasks().get(0).getTargetCompletionDate());
        assertEquals("low", projects.get("Test Project").getTasks().get(0).getPriorityLevel());
    }
}
