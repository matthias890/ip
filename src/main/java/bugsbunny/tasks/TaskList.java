package bugsbunny.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public void deleteTask(int index) {
        this.list.remove(index);
    }

    public void markTask(int index) {
        this.list.get(index).updateStatus(true);
    }

    public void unmarkTask(int index) {
        this.list.get(index).updateStatus(false);
    }

    public int getNumberOfTasks() {
        return this.list.size();
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

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
