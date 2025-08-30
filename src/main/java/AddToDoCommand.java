import java.io.IOException;

public class AddToDoCommand extends Command {
    private String taskName;

    public AddToDoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new ToDo(this.taskName);
        tasks.addTask(t);
        System.out.println("OK Doc, I've added this task:");
        System.out.println(" "+ t.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.getNumberOfTasks()));

        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showSavingError();
        }
    }
}