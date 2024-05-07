package ToDoList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RemoveTaskFromEmptyListTest {
    @Test
    public void testRemoveTaskFromEmptyList() {
        ToDoListApp toDoList = new ToDoListApp();
        toDoList.removeTask(0);
        assertEquals(0, toDoList.getTasks().size());
    }

    @Test
    public void testRemoveProjectFromEmptyList() {
        ToDoListApp toDoList = new ToDoListApp();
        toDoList.removeProject("0");
        assertEquals(0, toDoList.getProjects().size());
    }
}
