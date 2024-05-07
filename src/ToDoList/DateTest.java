package ToDoList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import java.time.LocalDate;
public class DateTest {

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Test
    public void Datetest() {
        ToDoListApp toDoList = new ToDoListApp();
        
        try {
            toDoList.addTask("creation d'application", LocalDate.now(), "High"); 
            toDoList.addProject("Project 1", LocalDate.now(), "High");
            toDoList.addTaskToProject("Project 1", new Task("creation d'application", LocalDate.parse(""), "Mid"));
        } catch (Exception e) {
            errorCollector.addError(e); 
        }  
    }
}
