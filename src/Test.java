
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Task {
    private String description;
    private boolean completed;
    private LocalDate targetCompletionDate;
    private String priorityLevel;

    public Task(String description, LocalDate targetCompletionDate, String priorityLevel) {
        this.description = description;
        this.completed = false;
        this.targetCompletionDate = targetCompletionDate;
        this.priorityLevel = priorityLevel;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsCompleted() {
        completed = true;
    }

    public LocalDate getTargetCompletionDate() {
        return targetCompletionDate;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    @Override
    public String toString() {
        return (completed ? "[X] " : "[ ] ") + description + " | Target Completion Date: " + targetCompletionDate
                + " | Priority: " + priorityLevel;
    }
}

class Project {
    private String projectName;
    private ArrayList<Task> tasks;
    private boolean completed;
    private LocalDate targetCompletionDate;
    private String priorityLevel;

    public Project(String projectName, LocalDate targetCompletionDate, String priorityLevel) {
        this.projectName = projectName;
        this.completed = false;
        this.targetCompletionDate = targetCompletionDate;
        this.priorityLevel = priorityLevel;
        this.tasks = new ArrayList<>();
    }

    public String getProjectName() {
        return projectName;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsCompleted() {
        completed = true;
    }

    public LocalDate getTargetCompletionDate() {
        return targetCompletionDate;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public void addTaskToProject(Task task) {
        tasks.add(task);
    }

    @Override
    public String toString() {
        StringBuilder projectInfo = new StringBuilder();
        projectInfo.append(projectName).append(" | Target Completion Date: ").append(targetCompletionDate)
                .append(" | Priority: ").append(priorityLevel).append("\n");

        if (!tasks.isEmpty()) {
            for (Task task : tasks) {
                projectInfo.append("\t").append(task.toString()).append("\n");
            }
        } else {
            projectInfo.append("\tNo tasks in this project.\n");
        }
        return projectInfo.toString();
    }
}

public class Test {
    private ArrayList<Task> tasks;
    private HashMap<String, Project> projects;

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public HashMap<String, Project> getProjects() {
        return projects;
    }
    public Test() {
        tasks = new ArrayList<>();
        projects = new HashMap<>();
    }

    public void addTask(String description, LocalDate targetCompletionDate, String priorityLevel) {
        Task task = new Task(description, targetCompletionDate, priorityLevel);
        tasks.add(task);
    }

    public void addProject(String projectName, LocalDate targetCompletionDate, String priorityLevel) {
        Project project = new Project(projectName, targetCompletionDate, priorityLevel);
        projects.put(projectName, project);
    }

    public void addTaskToProject(String projectName, Task task) {
        Project project = projects.get(projectName);
        if (project != null) {
            if (task.getTargetCompletionDate().isBefore(project.getTargetCompletionDate()) ||
                    task.getTargetCompletionDate().isEqual(project.getTargetCompletionDate())) {
                project.addTaskToProject(task);
            } else {
                System.out.println("Task's completion date exceeds project's target completion date.");
            }
        } else {
            System.out.println("Project not found.");
        }
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void removeProject(String projectName) {
        projects.remove(projectName);
    }

    public void removeTaskFromProject(String projectName, int index) {
        Project project = projects.get(projectName);
        if (project != null && index >= 0 && index < project.getTasks().size()) {
            project.getTasks().remove(index);
        } else {
            System.out.println("Invalid project or task index.");
        }
    }

    public void markTaskAsCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsCompleted();
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void markProjectAsCompleted(String projectName) {
        Project project = projects.get(projectName);
        if (project != null) {
            project.markAsCompleted();
        } else {
            System.out.println("Project not found.");
        }
    }

    public void markTaskAsCompletedInProject(String projectName, int index) {
        Project project = projects.get(projectName);
        if (project != null && index >= 0 && index < project.getTasks().size()) {
            project.getTasks().get(index).markAsCompleted();
        } else {
            System.out.println("Invalid project or task index.");
        }
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the to-do list.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + ". " + tasks.get(i));
            }
        }
    }

    public void listProjects() {
        if (projects.isEmpty()) {
            System.out.println("No projects in the to-do list.");
        } else {
            for (Project project : projects.values()) {
                System.out.println(project.toString());
            }
        }
    }

    public void listAll() {
        System.out.println("Tasks:");
        listTasks();
        System.out.println("\nProjects:");
        listProjects();
    }

    public static void main(String[] args) {
        Test toDoList = new Test();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("To-Do List Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Add Task To Project");
            System.out.println("3. Add Project");
            System.out.println("4. Remove Task");
            System.out.println("5. Remove Project");
            System.out.println("6. Remove Task from Project");
            System.out.println("7. Mark Task as Completed");
            System.out.println("8. Mark Project as Completed");
            System.out.println("9. Mark Task as completed in project");
            System.out.println("10. List Tasks");
            System.out.println("11. List Projects");
            System.out.println("12. List All");
            System.out.println("13. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String taskDescription = scanner.nextLine();

                    System.out.print("Enter target completion date (yyyy-mm-dd): ");
                    String dateString = scanner.nextLine();
                    LocalDate targetDate = LocalDate.parse(dateString);

                    System.out.print("Enter priority level (low/mid/high): ");
                    String priority = scanner.nextLine();

                    toDoList.addTask(taskDescription, targetDate, priority);
                    break;
                case 2:
                    System.out.print("Enter project name: ");
                    String projectName = scanner.nextLine();

                    if (toDoList.projects.containsKey(projectName)) {
                        System.out.print("Enter task description: ");
                        String taskDesc = scanner.nextLine();

                        System.out.print("Enter target completion date (yyyy-mm-dd): ");
                        String dateStr = scanner.nextLine();
                        LocalDate date = LocalDate.parse(dateStr);

                        System.out.print("Enter priority level (low/mid/high): ");
                        String prio = scanner.nextLine();

                        toDoList.addTaskToProject(projectName, new Task(taskDesc, date, prio));
                    } else {
                        System.out.println("Project not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter project name: ");
                    String newProjectName = scanner.nextLine();

                    System.out.print("Enter target completion date (yyyy-mm-dd): ");
                    String projDateString = scanner.nextLine();
                    LocalDate projTargetDate = LocalDate.parse(projDateString);

                    System.out.print("Enter priority level (low/mid/high): ");
                    String projPriority = scanner.nextLine();

                    toDoList.addProject(newProjectName, projTargetDate, projPriority);
                    break;
                case 4:
                    System.out.print("Enter task index to remove: ");
                    int removeIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    toDoList.removeTask(removeIndex);
                    break;
                case 5:
                    System.out.print("Enter project name to remove: ");
                    String removeProjectName = scanner.nextLine();
                    toDoList.removeProject(removeProjectName);
                    break;
                case 6:
                    System.out.print("Enter project name: ");
                    String projectNameToRemoveFrom = scanner.nextLine();

                    System.out.print("Enter task index to remove from project: ");
                    int taskIndexToRemove = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    toDoList.removeTaskFromProject(projectNameToRemoveFrom, taskIndexToRemove);
                    break;
                case 7:
                    System.out.print("Enter task index to mark as completed: ");
                    int completeIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    toDoList.markTaskAsCompleted(completeIndex);
                    break;
                case 8:
                    System.out.print("Enter project name to mark as completed: ");
                    String completeProjectName = scanner.nextLine();
                    toDoList.markProjectAsCompleted(completeProjectName);
                    break;
                case 9:
                    System.out.print("Enter project name: ");
                    String projectNameToMarkCompleted = scanner.nextLine();

                    System.out.print("Enter task index to mark as completed in project: ");
                    int taskIndexInProject = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    toDoList.markTaskAsCompletedInProject(projectNameToMarkCompleted, taskIndexInProject);
                    break;
                case 10:
                    System.out.println("Tasks:");
                    toDoList.listTasks();
                    break;
                case 11:
                    System.out.println("Projects:");
                    toDoList.listProjects();
                    break;
                case 12:
                    System.out.println("All Tasks and Projects:");
                    toDoList.listAll();
                    break;
                case 13:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
