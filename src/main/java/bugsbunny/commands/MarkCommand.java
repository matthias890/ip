package bugsbunny.commands;

import bugsbunny.app.Ui;
import bugsbunny.exception.BugsBunnyException;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.TaskList;

import java.io.IOException;

/**
 * Marks a task as done and saves the updated state.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * @param index Task index to mark.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
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