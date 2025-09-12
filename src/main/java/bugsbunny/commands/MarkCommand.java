package bugsbunny.commands;

import java.io.IOException;

import bugsbunny.app.Ui;
import bugsbunny.exception.BugsBunnyException;
import bugsbunny.parsers.Parser;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.TaskList;

/**
 * Marks a task as done and saves the updated state.
 */
public class MarkCommand extends Command {

    public MarkCommand(String args) {
        super(args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException {
        if (super.args.isBlank()) {
            throw new BugsBunnyException("Usage: mark <task index>");
        }

        int index = Parser.parseInteger(super.args) - 1;
        if (index < 0 || index >= tasks.getNumberOfTasks()) {
            throw new BugsBunnyException("The task number is out of range");
        }

        tasks.markTask(index);
        String output = "OK Doc, I've marked this task as done:\n " + tasks.getTask(index);

        try {
            storage.save(tasks);
        } catch (IOException e) {
            output += ui.showSavingError();
        }
        return output;
    }
}
