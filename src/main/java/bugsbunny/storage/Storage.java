package bugsbunny.storage;

import bugsbunny.tasks.Task;
import bugsbunny.tasks.TaskList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private Path filePath;
    private String filePathString;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
        this.filePathString = filePath;
    }

    public TaskList load() throws IOException {
        // Check if file exists
        if (Files.notExists(this.filePath)) {
            Path parent = this.filePath.getParent();

            if (parent != null) {
                // Creates directory if not present, else do nothing
                Files.createDirectories(parent);
            }

            Files.createFile(this.filePath);
            return new TaskList();
        }

        // file exists already
        Scanner scanner = new Scanner(this.filePath);
        ArrayList<Task> list = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.isBlank()) {
                continue;
            }

            try {
                Task task = Task.convertFromStorageFormat(line);
                list.add(task);
            } catch (Exception e) {
                System.out.println("Skipping bad line: " + e.getMessage());
            }
        }
        return new TaskList(list);
    }

    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(this.filePathString);
        ArrayList<Task> list = taskList.getList();

        for (Task task : list) {
            fw.write(task.convertToStorageFormat());
            fw.write(System.lineSeparator());
        }

        fw.close();
    }
}
