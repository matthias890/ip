package bugsbunny.commands;

import java.io.IOException;

import bugsbunny.app.Ui;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.Task;
import bugsbunny.tasks.TaskList;
import bugsbunny.tasks.ToDo;

/**
 * Adds a {@link bugsbunny.tasks.ToDo} task to the list and saves the updated state.
 */
public class AddToDoCommand extends Command {
    private String taskName;

    /**
     * @param taskName Description of the todo.
     */
    public AddToDoCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new ToDo(this.taskName);
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
