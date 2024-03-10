import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    public Scanner userInput;
    private String currentTasksFilePath;
    String filePath = "C:\\Users\\Student\\Desktop\\Final Project\\FinalProjectV3\\References\\CurrentTasks.txt";
    private List<Task> tasks = new ArrayList<>();
    //FileManager.readFile(filePath);

    public UserInterface() {
        this.userInput = new Scanner(System.in);
        this.filePath = filePath;
    }

    public Task getTaskUsingName(String taskName) {
        for (Task currentTask : tasks) {
            if (currentTask.getTaskName().toLowerCase().equals(taskName)) {
                return currentTask;
            }
        }
        return null;
    }

    public void run() {
        while (true) {
            System.out.println("Welcome! Please select from the menu below:");
            System.out.println("1. Current Tasks (provides the current outstanding and upcoming tasks");
            System.out.println("2. Add a Task");
            System.out.println("3. Make a Change to an Existing Task (allows you to change the due date, name, description, etc.)");
            System.out.println("4. Mark Task Complete");
            System.out.println("5. View Past Tasks (provides a list of tasks that have been marked as completed)");
            System.out.println("6. View Past-Due Tasks");
            System.out.println("7. View High Priority Tasks");
            System.out.println("8. Exit");
            System.out.println("Please enter the number of your selection");
            String userChoice = userInput.nextLine();

            try {
                int menuChoice = Integer.parseInt(userChoice);
                if (menuChoice == 1) {
                    displayAllCurrentTasks();
                } else if (menuChoice == 2) {
                    addTask();
                } else if (menuChoice == 3) {
                    updateTask();
                    //} else if (menuChoice == 4) {
                    //markTaskComplete();
                 /*else if (menuChoice == 5) {
                    viewCompletedTasks();
                } else if (menuChoice == 6) {
                    viewPastDueTasks();
                } else if (menuChoice == 7) {
                    viewHighPriorityTasks();
                */
                } else if (menuChoice == 8) {
                    System.out.println("Thank you!");
                } else {
                    System.out.println("That is an invalid choice. Please enter a number between 1 and 8.");
                }
            } catch (NumberFormatException e) {
                System.out.println("You chose an invalid number. Please try again.");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void displayAllCurrentTasks() throws FileNotFoundException {
        List<Task> tasks = FileManager.readFile(filePath);

        System.out.println();
    }

    private void addTask() throws IOException {
        System.out.println("Please enter the information for the new task:");

        System.out.println("Task Name: ");
        String taskName = userInput.nextLine();

        System.out.println("Please choose the priority of this task with 1 being highest and 3 being the lowest priority:");
        int priority = Integer.parseInt(userInput.nextLine());

        System.out.println("Please enter the due date for this task in this format -> YYYY-MM-DD");
        LocalDate dueDate = LocalDate.parse(userInput.nextLine());

        Task newlyAddedTask = new Task(taskName, priority, dueDate, false);
        tasks.add(newlyAddedTask);

        FileManager.writeTasks(filePath, tasks);
        System.out.println("Your task has been added to the list of current tasks. You can now view the list with your new task using menu option 1.");
    }

    private void updateTask() {
        List<Task> tasks = FileManager.readFile(filePath);
        System.out.println("Enter the task name that you would like to update:");
        String taskName = userInput.nextLine();
        Task taskToUpdate = null;

        //loop through the list of tasks from file.
        for (Task currentTask : tasks) {
            if (currentTask.getTaskName().equalsIgnoreCase(taskName)) {
                taskToUpdate = currentTask;
                //one name is found, break out of loop
                break;
            }
        }
        if (taskToUpdate == null) {
            System.out.println("Sorry, that task does not exist!");
            return;
        }

        System.out.println("Enter new name you'd like or press enter to keep the task name the same");
        String newName = userInput.nextLine();

        if (!newName.equals("")) {
            taskToUpdate.setTaskName(newName);
        }

        System.out.println("Enter the new priority of this task (1 for high priority and 3 for low priority) or press enter to keep it the same: ");
        String newPriority = userInput.nextLine();

        if (!newPriority.equals("")) {
            try {
                int newPriorityInt = Integer.parseInt(newPriority);
                taskToUpdate.setPriority(newPriorityInt);
            } catch (NumberFormatException e) {
                System.out.println("That is an invalid priority type.");
            }
        }
        System.out.println("Enter the new due date for this task or press enter to keep it the same: ");
        String newDueDate = userInput.nextLine();

        if (!newDueDate.equals("")) {
            try {
                LocalDate newDueDateParsed = LocalDate.parse(newDueDate);
                taskToUpdate.setDueDate(newDueDateParsed);
            } catch (Exception e) {
                System.out.println("Improper date format.");
            }
        }
        System.out.println("Your task has been updated!");

//need to write updatedTask to the file
        try {
            FileManager.writeTasks(filePath, tasks);
        } catch (IOException e) {
            System.out.println("The update couldn't be saved.");
        }
    }
}