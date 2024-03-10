import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    public static List<Task> readFile (String filePath) {
        File file = new File (filePath);
        List <Task> tasks = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        }
        return tasks;
    }

    public static void writeTasks (String filePath, List<Task> task) throws IOException {
        try (PrintWriter dataOutput = new PrintWriter(new FileWriter(filePath, true))) {
            for (Task currentTask : task) {
                String line = currentTask.getTaskName() + "," + currentTask.getPriority() + "," + currentTask.getDueDate().toString() + "," + currentTask.isCompleted();
                dataOutput.println(currentTask);

            }
        }
    }
    }




