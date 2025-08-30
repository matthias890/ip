public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
