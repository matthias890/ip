public class HelpCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BugsBunnyException {
        ui.showCommandGuide();
    }
}
