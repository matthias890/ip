package bugsbunny.commands;

import java.util.ArrayList;

import bugsbunny.app.Ui;
import bugsbunny.exception.BugsBunnyException;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.Task;
import bugsbunny.tasks.TaskList;

/**
 * Command that searches task descriptions that match the given query strings (case-insensitive).
 * Multiple words are accepted, but they must appear in the exact order.
 * <p>
 * i.e. Query for "eat chicken" for the task "todo eat chicken for dinner" works, but the query "eat dinner" will fail.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * @param keyword Object of interest.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException {
        ArrayList<Task> matchingTasks = tasks.getMatchingTasks(this.keyword);

        String output;

        if (matchingTasks.isEmpty()) {
            output = "I couldn't find any tasks that have that keyword in their name/description Doc";
        } else {
            output = "Good news Doc! I found the matching tasks in you list:";

            for (int i = 0; i < matchingTasks.size(); i++) {
                output += String.format("\n %d. %s", i + 1, matchingTasks.get(i));
            }
        }

        return output;
    }
}
