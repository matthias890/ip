import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>(100);
    }

    public void addTask(Task task) {
        this.list.add(task);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   "+ task.toString());
        System.out.println(String.format(" Now you have %d tasks in the list.", this.list.size()));
    }

    public void markTask(int index) {
        this.list.get(index).updateStatus(true);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + this.list.get(index).toString());
    }

    public void unmarkTask(int index) {
        this.list.get(index).updateStatus(false);
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + this.list.get(index).toString());
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
