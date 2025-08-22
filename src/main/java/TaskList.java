import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>(100);
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public void updateTaskStatus(int index, boolean bool) {
        this.list.get(index - 1).updateStatus(bool);
    }

    public void printTasks() {
        System.out.println(" Here are the tasks in your list: ");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(String.format(" %d. %s", i + 1, this.list.get(i).toString()));
        }
    }
}
