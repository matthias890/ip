package bugsbunny.commands;

import java.io.IOException;

import bugsbunny.app.Ui;
import bugsbunny.exception.BugsBunnyException;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.Task;
import bugsbunny.tasks.TaskList;
import bugsbunny.tasks.ToDo;

/**
 * Adds a {@link bugsbunny.tasks.ToDo} task to the list and saves the updated state.
 */
public class AddToDoCommand extends Command {

    public AddToDoCommand(String args) {
        super(args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException {
        if (super.args.isBlank()) {
            throw new BugsBunnyException("Usage: todo <description>");
        }
        Task t = new ToDo(super.args.trim());
        tasks.addTask(t);

        String output = String.format(
                "OK Doc, I've added this task:\n"
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
