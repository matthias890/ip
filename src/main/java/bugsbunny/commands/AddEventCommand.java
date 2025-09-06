package bugsbunny.commands;

import java.io.IOException;
import java.time.LocalDateTime;

import bugsbunny.app.Ui;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.Event;
import bugsbunny.tasks.Task;
import bugsbunny.tasks.TaskList;


/**
 * Adds a {@link bugsbunny.tasks.Event} task to the list and saves the updated state.
 */
public class AddEventCommand extends Command {
    private String taskName;
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * @param taskName Description of the event.
     * @param from Event start date/time.
     * @param to Event end date/time.
     */
    public AddEventCommand(String taskName, LocalDateTime from, LocalDateTime to) {
        this.taskName = taskName;
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Event(this.taskName, this.from, this.to);
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
