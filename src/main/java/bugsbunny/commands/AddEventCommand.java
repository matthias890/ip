package bugsbunny.commands;

import java.io.IOException;
import java.time.LocalDateTime;

import bugsbunny.app.Ui;
import bugsbunny.exception.BugsBunnyException;
import bugsbunny.parsers.DateTimeParser;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.Event;
import bugsbunny.tasks.Task;
import bugsbunny.tasks.TaskList;


/**
 * Adds a {@link bugsbunny.tasks.Event} task to the list and saves the updated state.
 */
public class AddEventCommand extends Command {
    private static final String EVENT_FROM_SEPARATOR = "/from";
    private static final String EVENT_TO_SEPARATOR = "/to";

    public AddEventCommand(String args) {
        super(args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException {
        if (super.args.isBlank()
                || !super.args.contains(AddEventCommand.EVENT_FROM_SEPARATOR)
                || !super.args.contains(AddEventCommand.EVENT_TO_SEPARATOR)) {
            throw new BugsBunnyException("Usage: event <description> /from <start> /to <end>");
        }
        String[] fromSplit = super.args.split(AddEventCommand.EVENT_FROM_SEPARATOR, 2);
        String taskName = fromSplit[0].trim();

        // no taskName included
        if (fromSplit.length < 2 || taskName.isEmpty()) {
            throw new BugsBunnyException("Usage: event <description> /from <start> /to <end>");
        }

        String[] toSplit = fromSplit[1].trim().split(AddEventCommand.EVENT_TO_SEPARATOR, 2);
        String from = toSplit[0].trim();

        // nothing after /from
        if (toSplit.length < 2 || from.isEmpty()) {
            throw new BugsBunnyException("Usage: event <description> /from <start> /to <end>");
        }

        String to = toSplit[1].trim();

        // nothing after /to
        if (to.isEmpty()) {
            throw new BugsBunnyException("Usage: event <description> /from <start> /to <end>");
        }

        LocalDateTime fromDateTime;
        LocalDateTime toDateTime;

        try {
            fromDateTime =
                    DateTimeParser.parseStringToDateTime(
                            from,
                            DateTimeParser.INPUT_TO_DATE_TIME);
        } catch (IllegalArgumentException e) {
            throw new BugsBunnyException(e.getMessage());
        }

        try {
            toDateTime =
                    DateTimeParser.parseStringToDateTime(
                            to,
                            DateTimeParser.INPUT_TO_DATE_TIME);
        } catch (IllegalArgumentException e) {
            throw new BugsBunnyException(e.getMessage());
        }

        Task t = new Event(taskName, fromDateTime, toDateTime);
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
