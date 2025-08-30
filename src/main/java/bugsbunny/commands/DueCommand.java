package bugsbunny.commands;

import bugsbunny.app.Ui;
import bugsbunny.parsers.DateTimeParser;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.Task;
import bugsbunny.tasks.TaskList;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Gets the list of tasks that are due by a specified date and time.
 */
public class DueCommand extends Command {
    private LocalDateTime dueDate;

    /**
     * @param dueDate Due date/time.
     */
    public DueCommand(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> dueTasks = tasks.getTasksDueBy(this.dueDate);

        if (dueTasks.isEmpty()) {
            System.out.println("You have no tasks that are due by "
                    + this.dueDate.format(DateTimeParser.DATE_TIME_STRING_FORMATTER));
        } else {
            System.out.println("Here are the tasks that are due by "
                    + this.dueDate.format(DateTimeParser.DATE_TIME_STRING_FORMATTER));
            for (int i = 0; i < dueTasks.size(); i++) {
                System.out.println(String.format(" %d. %s", i + 1, dueTasks.get(i).toString()));
            }
        }
    }
}