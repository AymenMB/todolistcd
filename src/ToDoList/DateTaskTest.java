package ToDoList;


import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class DateTaskTest {
    @Test
    public void testAddTaskWithPastDate() {
        ToDoListApp toDoList = new ToDoListApp();
        LocalDate pastDate = LocalDate.now().minusDays(1);
        toDoList.addTask("Past Task", pastDate, "high");
        assertEquals(0, toDoList.getTasks().size());
    }

    @Test
    public void testAddProjectWithFutureDate() {
        ToDoListApp toDoList = new ToDoListApp();
        LocalDate futureDate = LocalDate.now().plusDays(10);
        toDoList.addProject("Future Project", futureDate, "low");
        assertEquals(1, toDoList.getProjects().size());
    }
}
