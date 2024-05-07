package ToDoList;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class ToDoListGUI extends Frame {
    private ArrayList<Task> tasks;
    private HashMap<String, Project> projects;

    private TextField taskDescriptionField, taskDateField, taskPriorityField, projectNameField, projectDateField,
            projectPriorityField;
    private TextArea outputArea;

    public ToDoListGUI() {
        tasks = new ArrayList<>();
        projects = new HashMap<>();

        Panel taskPanel = new Panel();
        taskPanel.setLayout(new GridLayout(4, 2));

        Label taskDescriptionLabel = new Label("Task Description:");
        taskDescriptionField = new TextField(20);

        Label taskDateLabel = new Label("Task Target Date (yyyy-mm-dd):");
        taskDateField = new TextField(10);

        Label taskPriorityLabel = new Label("Task Priority (low/mid/high):");
        taskPriorityField = new TextField(10);

        taskPanel.add(taskDescriptionLabel);
        taskPanel.add(taskDescriptionField);
        taskPanel.add(taskDateLabel);
        taskPanel.add(taskDateField);
        taskPanel.add(taskPriorityLabel);
        taskPanel.add(taskPriorityField);

        // Delete Task Button
        Button deleteTaskButton = new Button("Delete Task");
        deleteTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputTaskName = JOptionPane.showInputDialog(null, "Enter task name to delete:");
                if (inputTaskName != null && !inputTaskName.isEmpty()) {
                    boolean found = false;
                    for (int i = 0; i < tasks.size(); i++) {
                        if (tasks.get(i).getDescription().equals(inputTaskName)) {
                            tasks.remove(i);
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        updateOutput();
                    } else {
                        JOptionPane.showMessageDialog(null, "Task not found.");
                    }
                }
            }
        });
        add(deleteTaskButton);

        // Button to delete a project
        Button deleteProjectButton = new Button("Delete Project");
        deleteProjectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String projectName = JOptionPane.showInputDialog(null, "Enter project name to delete:");
                if (projectName != null && !projectName.isEmpty()) {
                    if (projects.containsKey(projectName)) {
                        projects.remove(projectName);
                        updateOutput();
                    } else {
                        JOptionPane.showMessageDialog(null, "Project not found.");
                    }
                }
            }
        });
        add(deleteProjectButton);

        // Button to delete a task from project
        // Delete Task From Project Button
        Button deleteTaskFromProjectButton = new Button("Delete Task From Project");
        deleteTaskFromProjectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String projectName = JOptionPane.showInputDialog(null, "Enter project name:");
                if (projectName != null && !projectName.isEmpty()) {
                    Project project = projects.get(projectName);
                    if (project != null) {
                        String inputTaskName = JOptionPane.showInputDialog(null,
                                "Enter task name to delete from project:");
                        if (inputTaskName != null && !inputTaskName.isEmpty()) {
                            boolean found = false;
                            ArrayList<Task> projectTasks = project.getTasks();
                            for (int i = 0; i < projectTasks.size(); i++) {
                                if (projectTasks.get(i).getDescription().equals(inputTaskName)) {
                                    projectTasks.remove(i);
                                    found = true;
                                    break;
                                }
                            }
                            if (found) {
                                updateOutput();
                            } else {
                                JOptionPane.showMessageDialog(null, "Task not found in the project.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Project not found.");
                    }
                }
            }
        });
        add(deleteTaskFromProjectButton);

        Button addTaskButton = new Button("Add Task");
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String description = taskDescriptionField.getText();
                LocalDate targetDate = LocalDate.parse(taskDateField.getText());
                String priority = taskPriorityField.getText();

                addTask(description, targetDate, priority);
                updateOutput();
            }
        });

        Panel projectPanel = new Panel();
        projectPanel.setLayout(new GridLayout(4, 2));

        Label projectNameLabel = new Label("Project Name:");
        projectNameField = new TextField(20);

        Label projectDateLabel = new Label("Project Target Date (yyyy-mm-dd):");
        projectDateField = new TextField(10);

        Label projectPriorityLabel = new Label("Project Priority (low/mid/high):");
        projectPriorityField = new TextField(10);

        projectPanel.add(projectNameLabel);
        projectPanel.add(projectNameField);
        projectPanel.add(projectDateLabel);
        projectPanel.add(projectDateField);
        projectPanel.add(projectPriorityLabel);
        projectPanel.add(projectPriorityField);

        Button addProjectButton = new Button("Add Project");
        addProjectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String projectName = projectNameField.getText();
                LocalDate targetDate = LocalDate.parse(projectDateField.getText());
                String priority = projectPriorityField.getText();

                addProject(projectName, targetDate, priority);
                updateOutput();
            }
        });

        Panel taskToProjectPanel = new Panel();
        taskToProjectPanel.setLayout(new GridLayout(2, 1));

        Label taskProjectNameLabel = new Label("Task Project Name:");
        TextField taskProjectNameField = new TextField(20);

        Button addTaskToProjectButton = new Button("Add Task To Project");
        addTaskToProjectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String projectName = taskProjectNameField.getText();
                String description = taskDescriptionField.getText();
                LocalDate targetDate = LocalDate.parse(taskDateField.getText());
                String priority = taskPriorityField.getText();

                addTaskToProject(projectName, description, targetDate, priority);
                updateOutput();
            }
        });

        taskToProjectPanel.add(taskProjectNameLabel);
        taskToProjectPanel.add(taskProjectNameField);

        setLayout(new FlowLayout());

        add(taskPanel);
        add(addTaskButton);

        add(projectPanel);
        add(addProjectButton);

        add(taskToProjectPanel);
        add(addTaskToProjectButton);
        
        Button markTaskCompletedButton = new Button("Mark Task as Completed");
        markTaskCompletedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputTaskName = JOptionPane.showInputDialog(null, "Enter task name to mark as completed:");
                if (inputTaskName != null && !inputTaskName.isEmpty()) {
                    boolean found = false;
                    for (Task task : tasks) {
                        if (task.getDescription().equals(inputTaskName)) {
                            task.markAsCompleted();
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        updateOutput();
                    } else {
                        JOptionPane.showMessageDialog(null, "Task not found.");
                    }
                }
            }
        });
        add(markTaskCompletedButton);

        // Button to mark a project as completed
        Button markProjectCompletedButton = new Button("Mark Project as Completed");
        markProjectCompletedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String projectName = JOptionPane.showInputDialog(null, "Enter project name to mark as completed:");
                if (projectName != null && !projectName.isEmpty()) {
                    if (projects.containsKey(projectName)) {
                        projects.get(projectName).markAsCompleted();
                        updateOutput();
                    } else {
                        JOptionPane.showMessageDialog(null, "Project not found.");
                    }
                }
            }
        });
        add(markProjectCompletedButton);

        // Button to mark a task within a project as completed
        Button markTaskInProjectCompletedButton = new Button("Mark Task in Project as Completed");
        markTaskInProjectCompletedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String projectName = JOptionPane.showInputDialog(null, "Enter project name:");
                if (projectName != null && !projectName.isEmpty()) {
                    Project project = projects.get(projectName);
                    if (project != null) {
                        String inputTaskName = JOptionPane.showInputDialog(null, "Enter task name to mark as completed in project:");
                        if (inputTaskName != null && !inputTaskName.isEmpty()) {
                            boolean found = false;
                            for (Task task : project.getTasks()) {
                                if (task.getDescription().equals(inputTaskName)) {
                                    task.markAsCompleted();
                                    found = true;
                                    break;
                                }
                            }
                            if (found) {
                                updateOutput();
                            } else {
                                JOptionPane.showMessageDialog(null, "Task not found in the project.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Project not found.");
                    }
                }
            }
        });
        add(markTaskInProjectCompletedButton);

        outputArea = new TextArea(20, 50);
        add(outputArea);

        setTitle("ToDo List");
        setSize(600, 400);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        
        
    }

    public void addTask(String description, LocalDate targetCompletionDate, String priorityLevel) {
        Task task = new Task(description, targetCompletionDate, priorityLevel);
        tasks.add(task);
    }

    public void addProject(String projectName, LocalDate targetCompletionDate, String priorityLevel) {
        Project project = new Project(projectName, targetCompletionDate, priorityLevel);
        projects.put(projectName, project);
    }

    public void addTaskToProject(String projectName, String description, LocalDate targetCompletionDate,
            String priorityLevel) {
        Project project = projects.get(projectName);
        if (project != null) {
            if (targetCompletionDate.isBefore(project.getTargetCompletionDate()) ||
                    targetCompletionDate.isEqual(project.getTargetCompletionDate())) {
                Task task = new Task(description, targetCompletionDate, priorityLevel);
                project.addTaskToProject(task);
            } else {
                System.out.println("Task's completion date exceeds project's target completion date.");
            }
        } else {
            System.out.println("Project not found.");
        }
    }

    public void updateOutput() {
        StringBuilder output = new StringBuilder();
        output.append("Tasks:\n");
        for (Task task : tasks) {
            output.append(task).append("\n");
        }
        output.append("\nProjects:\n");
        for (Project project : projects.values()) {
            output.append(project).append("\n");
        }
        outputArea.setText(output.toString());
    }

    public static void main(String[] args) {
        new ToDoListGUI();
    }
}
