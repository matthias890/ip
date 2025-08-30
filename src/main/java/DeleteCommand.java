import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException {
        if (this.index < 0 || this.index >= tasks.getNumberOfTasks()) {
            throw new BugsBunnyException("The task number is out of range");
        }

        Task t = tasks.getTask(index);
        tasks.deleteTask(this.index);
        System.out.println("OK Doc, I've removed this task:");
        System.out.println(" " + t.toString());
        System.out.println("Now you have " + tasks.getNumberOfTasks() + " tasks in the list.");

        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showSavingError();
        }
    }
}
