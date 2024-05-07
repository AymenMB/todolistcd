package ToDoList;

import org.junit.jupiter.api.Test;
import java.awt.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ErrorTest {

    private void simulateAWTEventQueueEmptyDateString(ToDoListApp toDoList) {
       
        EventQueue.invokeLater(() -> {
            toDoList.addTask("Sample Task", LocalDate.now(), "High"); 
            toDoList.addProject("Project 1", LocalDate.now(), "High"); 
            toDoList.addTaskToProject("Project 1", new Task("Sample Task", LocalDate.parse(""), "High"));
        });
    }

    @Test
    public void testAWTEventQueueEmptyDateString() {
        ToDoListApp toDoList = new ToDoListApp();

       
        assertThrows(java.time.format.DateTimeParseException.class, () -> simulateAWTEventQueueEmptyDateString(toDoList));
    }
}
