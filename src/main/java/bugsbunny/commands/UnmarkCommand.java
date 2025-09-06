package bugsbunny.commands;

import java.io.IOException;

import bugsbunny.app.Ui;
import bugsbunny.exception.BugsBunnyException;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.TaskList;

/**
 * Unmarks a task and saves the updated state.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * @param index Task index to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException {
        if (this.index < 0 || this.index >= tasks.getNumberOfTasks()) {
            throw new BugsBunnyException("The task number is out of range");
        }

        tasks.unmarkTask(this.index);
        String output = "OK Doc, I've marked this task as not done yet:\n " + tasks.getTask(index);

        try {
            storage.save(tasks);
        } catch (IOException e) {
            output += ui.showSavingError();
        }
        return output;
    }
}
