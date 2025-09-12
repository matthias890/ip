package bugsbunny.commands;

import java.time.LocalDateTime;
import java.util.ArrayList;

import bugsbunny.app.Ui;
import bugsbunny.exception.BugsBunnyException;
import bugsbunny.parsers.DateTimeParser;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.Task;
import bugsbunny.tasks.TaskList;

/**
 * Gets the list of tasks that are due by a specified date and time.
 */
public class DueCommand extends Command {

    public DueCommand(String args) {
        super(args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException {
        if (super.args.isBlank()) {
            throw new BugsBunnyException("Usage: due <yyyy-mm-dd hhmm>");
        }
        LocalDateTime dueDate;
        try {
            dueDate = DateTimeParser.parseStringToDateTime(
                    super.args.trim(),
                    DateTimeParser.INPUT_TO_DATE_TIME);
        } catch (IllegalArgumentException e) {
            // Incorrect Date format error
            throw new BugsBunnyException(e.getMessage());
        }

        ArrayList<Task> dueTasks = tasks.getTasksDueBy(dueDate);

        String output;

        if (dueTasks.isEmpty()) {
            output = "You have no tasks that are due by "
                    + dueDate.format(DateTimeParser.DATE_TIME_STRING_FORMATTER);
        } else {
            output = "Here are the tasks that are due by "
                    + dueDate.format(DateTimeParser.DATE_TIME_STRING_FORMATTER);
            for (int i = 0; i < dueTasks.size(); i++) {
                output += String.format("\n %d. %s", i + 1, dueTasks.get(i));
            }
        }

        return output;
    }
}
