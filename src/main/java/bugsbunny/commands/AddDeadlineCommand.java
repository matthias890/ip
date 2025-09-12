package bugsbunny.commands;

import java.io.IOException;
import java.time.LocalDateTime;

import bugsbunny.app.Ui;
import bugsbunny.exception.BugsBunnyException;
import bugsbunny.parsers.DateTimeParser;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.Deadline;
import bugsbunny.tasks.Task;
import bugsbunny.tasks.TaskList;

/**
 * Adds a {@link bugsbunny.tasks.Deadline} task to the list and saves the updated state.
 */
public class AddDeadlineCommand extends Command {
    private static final String DEADLINE_SEPARATOR = "/by";

    public AddDeadlineCommand(String args) {
        super(args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException {
        if (super.args.isBlank() || !super.args.contains(AddDeadlineCommand.DEADLINE_SEPARATOR)) {
            throw new BugsBunnyException("Usage: deadline <description> /by <date>");
        }
        String[] bySplit = super.args.trim().split(AddDeadlineCommand.DEADLINE_SEPARATOR, 2);
        String taskName = bySplit[0].trim();
        if (bySplit.length < 2 || taskName.isEmpty()) {
            throw new BugsBunnyException("Usage: deadline <description> /by <date>");
        }

        String by = bySplit[1].trim();

        // nothing after /by
        if (by.isEmpty()) {
            throw new BugsBunnyException("Usage: deadline <description> /by <date>");
        }

        LocalDateTime byDateTime;
        try {
            byDateTime = DateTimeParser.parseStringToDateTime(
                    by,
                    DateTimeParser.INPUT_TO_DATE_TIME);
        } catch (IllegalArgumentException e) {
            // Invalid Date time format
            throw new BugsBunnyException(e.getMessage());
        }
        Task t = new Deadline(taskName, byDateTime);
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
