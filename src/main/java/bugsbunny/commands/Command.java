package bugsbunny.commands;

import bugsbunny.app.Ui;
import bugsbunny.exception.BugsBunnyException;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.TaskList;

/**
 * Abstract parent class for all other commands supported by the chatbot.
 * Subclasses implement {@link #execute} and may override {@link #isExit}.
 */
public abstract class Command {
    /**
     * Executes this command against the given task list and storage.
     *
     * @param tasks The collection of tasks.
     * @param ui The UI used for printing messages to the chatbot.
     * @param storage The storage used for saving and loading from the hard disk.
     * @return Output string of the chatbot's response
     * @throws BugsBunnyException if command validation fails.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException;

    /**
     * Checks if the command is an Exit Command.
     *
     * @return {@code true} if the command should terminate the app.
     */
    public boolean isExit() {
        return false;
    }
}
