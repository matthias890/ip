import java.io.IOException;
import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    private String taskName;
    private LocalDateTime by;

    public AddDeadlineCommand(String taskName, LocalDateTime by) {
        this.taskName = taskName;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Deadline(this.taskName, this.by);
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