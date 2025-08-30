package bugsbunny.commands;

import bugsbunny.app.Ui;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.Event;
import bugsbunny.tasks.Task;
import bugsbunny.tasks.TaskList;

import java.io.IOException;
import java.time.LocalDateTime;

public class AddEventCommand extends Command {
    private String taskName;
    private LocalDateTime from;
    private LocalDateTime to;

    public AddEventCommand(String taskName, LocalDateTime from, LocalDateTime to) {
        this.taskName = taskName;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Event(this.taskName, this.from, this.to);
        tasks.addTask(t);
        System.out.println("OK Doc, I've added this task:");
        System.out.println(" "+ t.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.getNumberOfTasks()));

        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showSavingError();
        }
    }
}
