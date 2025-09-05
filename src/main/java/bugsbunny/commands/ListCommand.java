package bugsbunny.commands;

import java.util.ArrayList;

import bugsbunny.app.Ui;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.Task;
import bugsbunny.tasks.TaskList;

/**
 * Prints the current list of task to the chat.
 */
public class ListCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> list = tasks.getList();
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format(" %d. %s", i + 1, list.get(i).toString()));
        }
    }
}
