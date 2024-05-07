package ToDoList;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class ListAllTest {

    @Test
    public void testListAllWhenEmpty() {
        ToDoListApp toDoList = new ToDoListApp();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        toDoList.listAll();
        assertEquals("Tasks:\nNo tasks in the to-do list.\n\nProjects:\nNo projects in the to-do list.\n", outContent.toString());
    }

    @Test
    public void testListAllWithTasksAndProjects() {
        ToDoListApp toDoList = new ToDoListApp();
        LocalDate targetDate = LocalDate.now().plusDays(3);
        toDoList.addTask("Test Task", targetDate, "high");
        LocalDate projectTargetDate = LocalDate.now().plusDays(5);
        toDoList.addProject("Test Project", projectTargetDate, "medium");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        toDoList.listAll();
        assertTrue(outContent.toString().contains("Tasks:\n[ ] Test Task | Target Completion Date"));
        assertTrue(outContent.toString().contains("Projects:\nTest Project | Target Completion Date"));
    }
}
