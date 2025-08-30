package bugsbunny.app;

import bugsbunny.commands.Command;
import bugsbunny.parsers.Parser;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.TaskList;
import bugsbunny.exception.BugsBunnyException;

import java.io.IOException;

public class BugsBunny {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public BugsBunny(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        System.out.println();
        ui.showCommandGuide();
        ui.showLine();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BugsBunnyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new BugsBunny("data/tasks.txt").run();
    }
}
