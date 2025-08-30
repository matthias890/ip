import java.io.IOException;

public class MarkCommand extends Command{
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException {
        if (this.index < 0 || this.index >= tasks.getNumberOfTasks()) {
            throw new BugsBunnyException("The task number is out of range");
        }

        tasks.markTask(this.index);
        System.out.println("OK Doc, I've marked this task as done:");
        System.out.println(" " + tasks.getTask(index).toString());

        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showSavingError();
        }
    }
}