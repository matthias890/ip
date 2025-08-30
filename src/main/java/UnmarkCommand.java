import java.io.IOException;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException {
        if (this.index < 0 || this.index >= tasks.getNumberOfTasks()) {
            throw new BugsBunnyException("The task number is out of range");
        }

        tasks.unmarkTask(this.index);
        System.out.println("OK Doc, I've marked this task as not done yet:");
        System.out.println(" " + tasks.getTask(index).toString());

        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showSavingError();
        }
    }
}
