package bugsbunny.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A mutable collection of tasks with helpers for common operations.
 */
public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the end of the list.
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Removes the task at the given index (0-based).
     */
    public void deleteTask(int index) {
        this.list.remove(index);
    }

    /**
     * Marks the task at the given index (0-based).
     */
    public void markTask(int index) {
        this.list.get(index).updateStatus(true);
    }

    /**
     * Unmarks the task at the given index (0-based).
     */
    public void unmarkTask(int index) {
        this.list.get(index).updateStatus(false);
    }

    /**
     * @return Total number of tasks
     */
    public int getNumberOfTasks() {
        return this.list.size();
    }

    /**
     *  @return The task at the given index (0-based).
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * @return The underlying list (mutable).
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Filters tasks considered due by the provided moment.
     *
     * @param dateTime Cutoff (inclusive for deadlines, see {@link Deadline#isDueBy(LocalDateTime)}).
     * @return A new list containing tasks due by {@code dateTime}.
     */
    public ArrayList<Task> getTasksDueBy(LocalDateTime dateTime) {
        ArrayList<Task> dueTasks = new ArrayList<>();

        for (Task task : this.list) {
            if (task.isDueBy(dateTime)) {
                dueTasks.add(task);
            }
        }
        return dueTasks;
    }

    /**
     * Filters tasks based on whether the task contains the keyword in its description.
     *
     * @param keyword Object of interest.
     * @return A new list containing tasks that contains the {@code keyword}.
     */
    public ArrayList<Task> getMatchingTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : this.list) {
            if (task.hasKeyword(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
