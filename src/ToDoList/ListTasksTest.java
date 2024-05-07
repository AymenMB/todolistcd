package ToDoList;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class ListTasksTest {
    @Test
    public void testListTasksWhenEmpty() {
        ToDoListApp toDoList = new ToDoListApp();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        toDoList.listTasks();
        assertEquals("No tasks in the to-do list.\n", outContent.toString());
    }

    @Test
    public void testListProjectsWhenEmpty() {
        ToDoListApp toDoList = new ToDoListApp();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        toDoList.listProjects();
        assertEquals("No projects in the to-do list.\n", outContent.toString());
    }
}
