package bugsbunny.commands;

import bugsbunny.app.Ui;
import bugsbunny.exception.BugsBunnyException;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.TaskList;

/**
 * Prints the command guide from the UI.
 */
public class HelpCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException {
        return ui.showCommandGuide();
    }
}
