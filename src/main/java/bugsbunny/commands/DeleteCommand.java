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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException {
        if (this.index < 0 || this.index >= tasks.getNumberOfTasks()) {
            throw new BugsBunnyException("The task number is out of range");
        }

        Task t = tasks.getTask(index);
        tasks.deleteTask(this.index);
        System.out.println("OK Doc, I've removed this task:");
        System.out.println(" " + t);
        System.out.println("Now you have " + tasks.getNumberOfTasks() + " tasks in the list.");

        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showSavingError();
        }
    }
}
