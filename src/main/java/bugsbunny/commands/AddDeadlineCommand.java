package bugsbunny.commands;

import java.io.IOException;
import java.time.LocalDateTime;

import bugsbunny.app.Ui;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.Deadline;
import bugsbunny.tasks.Task;
import bugsbunny.tasks.TaskList;



/**
 * Adds a {@link bugsbunny.tasks.Deadline} task to the list and saves the updated state.
 */
public class AddDeadlineCommand extends Command {
    private String taskName;
    private LocalDateTime by;

    /**
     * @param taskName Description of the deadline.
     * @param by Deadline date/time.
     */
    public AddDeadlineCommand(String taskName, LocalDateTime by) {
        this.taskName = taskName;
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Deadline(this.taskName, this.by);
        tasks.addTask(t);
        System.out.println("OK Doc, I've added this task:");
        System.out.println(" " + t);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.getNumberOfTasks()));

        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showSavingError();
        }
    }
}
