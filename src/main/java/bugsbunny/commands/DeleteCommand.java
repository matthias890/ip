package bugsbunny.commands;

import java.io.IOException;

import bugsbunny.app.Ui;
import bugsbunny.exception.BugsBunnyException;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.Task;
import bugsbunny.tasks.TaskList;

/**
 * Deletes a task from the list and saves the updated state.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * @param index Task index to delete.
     */
    public DeleteCommand(int index) {
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

        Task t = tasks.getTask(index);
        tasks.deleteTask(this.index);

        String output = String.format(
                "OK Doc, I've removed this task:\n"
                        + " %s\n"
                        + "Now you have %d tasks in the list.",
                t,
                tasks.getNumberOfTasks()
        );

        try {
            storage.save(tasks);
        } catch (IOException e) {
            output += ui.showSavingError();
        }

        return output;
    }
}
