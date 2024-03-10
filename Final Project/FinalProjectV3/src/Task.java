import java.time.LocalDate;

public class Task {
    //private String id;
    private String taskName;
    private int priority;
    private LocalDate dueDate;
    public boolean isCompleted;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Task(String name, int priority, LocalDate dueDate, boolean isCompleted) {
        // this.id = id;
        this.taskName = name;
        this.priority = priority;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }
    public String toString () {
        return "Task Name: " + taskName + ", " + "Priority: " + priority + "," + "Due Date: " + dueDate;
    }
}
